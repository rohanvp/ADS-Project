import java.nio.file.Path;
import java.util.*;
import java.io.*;
import java.lang.reflect.Method;


////////////////////////////////////////MIN HEAP////////////////////////////////////////
class MinHeapRide
{
    int rideNumber;
    int rideCost;
    int tripDuration;
    RbtRide rbtPtr;

    MinHeapRide(){}

    MinHeapRide(int rideNumber,int rideCost,int tripDuration)
    {
        this.rideNumber=rideNumber;
        this.rideCost=rideCost;
        this.tripDuration=tripDuration;
        this.rbtPtr=null;
    }
}

class MinHeap {

    public static List<MinHeapRide> minHeapList=new ArrayList<>();
    public static int heapSize=0;
    public static int currIndex=-1;


    // MIN HEAP IMPLEMENTATION
    public static MinHeapRide insertRide(int rideNumber,int rideCost,int tripDuration)
    {
        MinHeapRide newRide=new MinHeapRide(rideNumber, rideCost, tripDuration);
        heapSize++;
        currIndex=heapSize-1;
        minHeapList.add(newRide);
        while(currIndex!=0 && minHeapList.get((currIndex-1)/2).rideCost>=minHeapList.get(currIndex).rideCost)
        {   
            int parent=(currIndex-1)/2;
            MinHeapRide temp=minHeapList.get(parent);
            if(minHeapList.get((currIndex-1)/2).rideCost==minHeapList.get(currIndex).rideCost)
            {   
                if(minHeapList.get((currIndex-1)/2).tripDuration>minHeapList.get(currIndex).tripDuration)
                {
                    minHeapList.set(parent,minHeapList.get(currIndex));
                    minHeapList.set(currIndex, temp);
                    currIndex=parent;
                }
            }
            else
            {
                minHeapList.set(parent,minHeapList.get(currIndex));
                minHeapList.set(currIndex, temp);
                currIndex=parent;
            }
            

        }

        return newRide;
        


    }

    public static void deleteRide(MinHeapRide node)
    {   
        
    }

    public static MinHeapRide getNextRide()
    {   
        // THIS FUNCTION ALSO IMPLEMENTS DELETION
        if(minHeapList.size()==0) 
        {
            System.out.println("No Active Rides");
            return null;
        }
        MinHeapRide nextRideNumber=minHeapList.get(0);
        currIndex=heapSize-1;
        heapSize-=1;
        minHeapList.set(0,minHeapList.get(currIndex));
        minHeapList.remove(currIndex);
        if(heapSize!=0)
            minHeapify(0);
        return nextRideNumber;
    }


    public static void minHeapify(int ci)
    {   
        MinHeapRide smallest=minHeapList.get(ci);
        int smallestIndex=0;
        if((ci*2+1)<heapSize && smallest.rideCost>=minHeapList.get(ci*2+1).rideCost)
        {
            if(smallest.rideCost==minHeapList.get(ci*2+1).rideCost)
            {   
                if(smallest.tripDuration>minHeapList.get(ci*2+1).tripDuration)
                {
                    minHeapList.set(ci,minHeapList.get(ci*2+1));
                    minHeapList.set(ci*2+1, smallest);
                    smallest=minHeapList.get(ci*2+1);
                    smallestIndex=ci*2+1;
                }
            }
            else
            {
                minHeapList.set(ci,minHeapList.get(ci*2+1));
                minHeapList.set(ci*2+1, smallest);
            }

        }
        if((ci*2+2)<heapSize)
        {
            if(smallest.rideCost==minHeapList.get(ci*2+2).rideCost)
            {   
                if(smallest.tripDuration>minHeapList.get(ci*2+2).tripDuration)
                {
                    minHeapList.set(ci,minHeapList.get(ci*2+2));
                    minHeapList.set(ci*2+2, smallest);
                    smallest=minHeapList.get(ci*2+2);
                    smallestIndex=ci*2+2;
                }
            }
            else
            {
                minHeapList.set(ci,minHeapList.get(ci*2+2));
                minHeapList.set(ci*2+2, smallest);
            }
        }
        if(smallestIndex!=ci)
            minHeapify(smallestIndex);

        
    }
    // MIN HEAP IMPLEMENTATION

    public static void printElementsMinHeap()
    {
        for(int i=0;i<minHeapList.size();i++)
        {
            System.out.println(minHeapList.get(i).rideNumber+" "+minHeapList.get(i).rideCost+" "+minHeapList.get(i).tripDuration);
        }
    }


    // public static void main(String[] args) {

    //     insertRide(25, 98, 46);
    //     insertRide(42,17,89);
    //     insertRide(9,76,31);
    //     // printElements();
    //     getNextRide();
    //     printElements();
        
    // }
}


////////////////////////////////////////MIN HEAP////////////////////////////////////////











////////////////////////////////////////RED BLACK TREE////////////////////////////////////////
class RbtRide
{
    int rideNumber;
    int rideCost;
    int tripDuration;
    // COLOR 0->BLACK,1->RED
    int color;
    RbtRide parent;
    RbtRide left;
    RbtRide right;
    MinHeapRide minheapPtr=null;

    RbtRide(){}

    RbtRide(int rideNumber,int rideCost,int tripDuration)
    {
        this.rideNumber=rideNumber;
        this.rideCost=rideCost;
        this.tripDuration=tripDuration;
        this.minheapPtr=null;

    }
}

class Rbt {

    public static RbtRide root=null;


    public static RbtRide insertRide(int rideNumber,int rideCost,int tripDuration)
    {   
        
        RbtRide RbtRideTempPtr=bstInsert(rideNumber,rideCost,tripDuration);
        // System.out.println(0);
        root.color=0;
        return RbtRideTempPtr;
    }

    public static void deleteRide(int rideNumber)
    {
        bstDelete(rideNumber);
    }

    private static RbtRide bstInsert(int rideNumber,int rideCost,int tripDuration)
    {   
        // INSERT THE NEW NODE AS A RED NODE BY DEFAULT
        RbtRide node=new RbtRide(rideNumber,rideCost,tripDuration);
        node.left=null;
        node.right=null;
        node.parent=null;
        node.color=1;

        RbtRide y=null;
        RbtRide x=root;

        while(x!=null)
        {   
            y=x;
            if(x.rideNumber>node.rideNumber)
            {
                x=x.left;
            }
            else
            {
                x=x.right;
            }
            
        }

        node.parent=y;

        if(y==null) 
        {
            root=node;
        }
        else if(node.rideNumber>y.rideNumber)
        {
            y.right=node;
        }
        else
        {
            y.left=node;
        }

        if(node.parent==null)
        {
            node.color=0;
            return node;
        }
        if(node.parent.parent==null) return node;
        
        insertColorBalance(node);

        root.color=0;

        return node;
        
    }

    private static void insertColorBalance(RbtRide x)
    {
        RbtRide d=null;
        while(x.parent.color==1)
        {
            if(x.parent==x.parent.parent.right)
            {   
                d=x.parent.parent.left;
                if(d==null) 
                {   
                    // x=x.parent.parent;//
                    // rotateRR(x);
                    // x.parent.color=0;
                    // x.parent.left.color=1;
                    // return;
                    if(x==x.parent.left)
                    {   
                        x=x.parent;
                        rotateLL(x);
                    }
                    x.parent.color=0;
                    x.parent.parent.color=1;
                    rotateRR(x.parent.parent);
                    return;
                }
                if(d.color==1)
                {   

                    d.color=0;
                    x.parent.color=0;
                    x.parent.parent.color=1;
                    x=x.parent.parent;
                }
                else
                {
                    if(x==x.parent.left)
                    {   
                        x=x.parent;
                        rotateLL(x);
                    }
                    x.parent.color=0;
                    x.parent.parent.color=1;
                    rotateRR(x.parent.parent);
                }
            }
            else
            {   
                d=x.parent.parent.right;
                if(d==null) 
                {   
                    System.out.println(x.rideNumber);
                    rotateLL(x.parent.parent);
                    System.out.println(x.parent.rideNumber);
                    x.parent.color=0;
                    x.parent.right.color=1;
                    return;
                }
                if(d.color==1)
                {
                    d.color=0;
                    x.parent.color=0;
                    x.parent.parent.color=1;
                    x=x.parent.parent;
                }
                else
                {
                    if(x==x.parent.right)
                    {
                        x=x.parent;
                        rotateRR(x);
                    }
                    x.parent.color=0;
                    x.parent.parent.color=1;
                    rotateLL(x.parent.parent);
                }
            }

            if(x==root) break;
        }  

    }

    private static void bstDelete(int rideNumber)
    {
        // RbtRide y=null;
        RbtRide curr=root;

        while(curr!=null)
        {   
            // y=curr;
            if(curr.rideNumber==rideNumber) break;
            if(curr.rideNumber>rideNumber)
            {
                curr=curr.left;
            }
            else
            {
                curr=curr.right;
            }
            
        }
        if(curr==null) return;

        if(curr.left==null && curr.right==null && curr.parent==null) return;

        RbtRide z=curr;
        RbtRide x,y;

        y=z;
        int yOg=y.color;
        if(z.left==null)
        {
            x=z.right;

            RbtRide u=z,v=z.right;
            
            switchNodes(u,v);
        }
        else if(z.right==null)
        {
            x=z.left;
            RbtRide u=z,v=z.left;
            
            switchNodes(u,v);

        }
        else
        {   
            RbtRide nodeReplacedBy=z.right;
            while(nodeReplacedBy.left!=null)
            {
                nodeReplacedBy=nodeReplacedBy.left;
            }
            y=nodeReplacedBy;
            yOg=y.color;
            x=y.right;
            
            if(y!=null && y.parent!=null && x!=null && x.parent!=null)
                if(y.parent==z) x.parent=y;
            else
            {
                RbtRide u=z,v=y;
                switchNodes(u,v);

                if(y.right!=null)
                {
                    y.right = z.right;
				    y.right.parent = y;
                }

            }

            switchNodes(z,y);
            y.left = z.left;
			y.left.parent = y;
			y.color = z.color;

        }

        if (yOg == 0){
			deleteColorBalance(x);
		}
        
    }

    private static void deleteColorBalance(RbtRide x)
    {   
        
        RbtRide s;
        if(x==null) return;//RVP
		while (x != root && x.color == 0) {
			if (x == x.parent.left) {
				s = x.parent.right;
				if (s.color == 1) {
					// case 3.1
					s.color = 0;
					x.parent.color = 1;
					rotateRR(x.parent);
					s = x.parent.right;
				}

				if (s.left.color == 0 && s.right.color == 0) {
					// case 3.2
					s.color = 1;
					x = x.parent;
				} else {
					if (s.right.color == 0) {
						// case 3.3
						s.left.color = 0;
						s.color = 1;
						rotateLL(s);
						s = x.parent.right;
					} 

					// case 3.4
					s.color = x.parent.color;
					x.parent.color = 0;
					s.right.color = 0;
					rotateRR(x.parent);
					x = root;
				}
			} else {
				s = x.parent.left;
				if (s.color == 1) {
					// case 3.1
					s.color = 0;
					x.parent.color = 1;
					rotateLL(x.parent);
					s = x.parent.left;
				}

				if (s.right.color == 0 && s.right.color == 0) {
					// case 3.2
					s.color = 1;
					x = x.parent;
				} else {
					if (s.left.color == 0) {
						// case 3.3
						s.right.color = 0;
						s.color = 1;
						rotateLL(s);
						s = x.parent.left;
					} 

					// case 3.4
					s.color = x.parent.color;
					x.parent.color = 0;
					s.left.color = 0;
					rotateLL(x.parent);
					x = root;
				}
			} 
		}
		x.color = 0;


    }

    private static void switchNodes(RbtRide u,RbtRide v)
    {
        if (u.parent == null) {
			root = v;
		} else if (u == u.parent.left){
			u.parent.left = v;
		} else {
			u.parent.right = v;
		}
        if(u!=null && v!=null)
		    v.parent = u.parent;
    }

    private static void rotateRR(RbtRide y)
    {   
        RbtRide x=y.right;
        y.right=x.left;
        if(y.left!=null) x.left.parent=y;
        x.parent=y.parent;
        if(y.parent==null) root=x;
        else if(y==y.parent.left) y.parent.left=x;
        else y.parent.right=x;
        x.left=y;
        y.parent=x;
    }
    private static void rotateLL(RbtRide y)
    {
        RbtRide x=y.left;
        y.left=x.right;
        if(y.right!=null) x.right.parent=y;
        x.parent=y.parent;
        if(y.parent==null) root=x;
        else if(y==y.parent.right) y.parent.right=x;
        else y.parent.left=x;
        x.right=y;
        y.parent=x;

    }

    public static RbtRide searchRide(int rideNumber)
    {
        RbtRide curr=root;

        while(curr!=null)
        {   
            // y=curr;
            if(curr.rideNumber==rideNumber) break;
            if(curr.rideNumber>rideNumber)
            {
                curr=curr.left;
            }
            else
            {
                curr=curr.right;
            }
            
        }
        if(curr==null) return null;
        return curr;
    }

    public static void printElements(RbtRide curr)
    {
        if(curr==null) return;
        printElements(curr.left);
        System.out.println(curr.rideNumber+" "+curr.rideCost+" "+curr.tripDuration);
        System.out.println(curr.minheapPtr.rideNumber+" "+curr.minheapPtr.rideCost+" "+curr.minheapPtr.tripDuration);
        // System.out.println(curr.color);
        // if(curr.parent!=null)
        //     System.out.println(curr.parent.rideNumber+" "+curr.parent.rideCost+" "+curr.parent.tripDuration);
        printElements(curr.right);
    }


    // public static void main(String[] args) {

    //     insertRide(9,76,31);
    //     insertRide(25, 98, 46);
    //     insertRide(42,17,89);
    //     insertRide(53,97,22); 
    //     insertRide(96,28,82);
    //     insertRide(73,28,56);
    //     insertRide(20,49,59);
    //     insertRide(62,7,10);
    //     bstDelete(73);
    //     printElements(root);
        
        
    // }
    
}



////////////////////////////////////////RED BLACK TREE////////////////////////////////////////



////////////////////////////////////////GATOR TAXI////////////////////////////////////////


public class GatorTaxi {    

    private static void printElements()
    {   
        System.out.println("PRINT MINHEAP ELEMENTS");
        for(int i=0;i<MinHeap.minHeapList.size();i++)
        {
            System.out.println(MinHeap.minHeapList.get(i).rideNumber+" "+MinHeap.minHeapList.get(i).rideCost+" "+MinHeap.minHeapList.get(i).tripDuration);
            // System.out.println(MinHeap.minHeapList.get(i).rbtPtr.rideNumber+" "+MinHeap.minHeapList.get(i).rbtPtr.rideCost+" "+MinHeap.minHeapList.get(i).rbtPtr.tripDuration);
        }
        System.out.println();
        System.out.println("PRINT RBT ELEMENTS");
        Rbt.printElements(Rbt.root);
    }


    private static void printRideNumber(int rideNumber) {

        // prints the triplet (rideNumber, rideCost, tripDuration).
        // for(int i=0;i<heapSize;i++)
        // {
        //     if(minHeap.get(i).rideNumber==rideNumber) System.out.println(minHeap.get(i));
        // }
        RbtRide curr_location=Rbt.searchRide(rideNumber);
        if(curr_location==null) System.out.println("No Ride Found");
        else System.out.println(curr_location.rideNumber+" "+curr_location.rideCost+" "+curr_location.tripDuration);
        
        
    }
    private static void printRideNumber(int rideNumber1,int rideNumber2) {

        // prints all triplets (rx, rideCost, tripDuration) for which rideNumber1 <= rx <= rideNumber2.
        // System.out.println("ride no:"+rideNumber1+" "+rideNumber2);

        
    }

    private static void insertData(int rideNumber,int rideCost,int tripDuration) {

        MinHeapRide newMinHeapRidePtr=MinHeap.insertRide(rideNumber, rideCost, tripDuration);
        RbtRide newRbtRidePtr=Rbt.insertRide(rideNumber, rideCost, tripDuration);
        newMinHeapRidePtr.rbtPtr=newRbtRidePtr;
        newRbtRidePtr.minheapPtr=newMinHeapRidePtr;
        
        // if(rideNumber==9)
        // {
        //     // System.out.println(newMinHeapRidePtr.rbtPtr.parent.right.parent.left.rideNumber);
        //     // System.out.println(newRbtRidePtr.minheapPtr.rideNumber);
            
        // }

    }

    private static void getNextRide() {
        
        //  When this function is invoked, the ride with the lowest rideCost (ties are broken by selecting the ride with the lowest tripDuration) is output.
        //  This ride is then deleted from the data structure.
        MinHeapRide minHeapRide=MinHeap.getNextRide();
        if(minHeapRide!=null)
        {
            System.out.println(minHeapRide.rideNumber+" "+minHeapRide.rideCost+" "+minHeapRide.tripDuration);
            Rbt.deleteRide(minHeapRide.rideNumber);
        }
        
    }

    private static void cancelRide(int rideNumber) {
        // deletes the triplet (rideNumber, rideCost, tripDuration) from the data
        // structures, can be ignored if an entry for rideNumber doesn’t exist.
        Rbt.deleteRide(rideNumber);
        
    }

    private static void updateTrip(int rideNumber,int newTripDuration) {
        // a) if the new_tripDuration <= existing tripDuration, there would be no action needed.
        // b) if the existing_tripDuration < new_tripDuration <= 2*(existing tripDuration), the driver will
        //    cancel the existing ride and a new ride request would be created with a penalty of 10 on existing rideCost . 
        // We update the entry in the data structure with (rideNumber, rideCost+10, new_tripDuration)
        // c) if the new_tripDuration > 2*(existing tripDuration), the ride would be automatically declined and the ride would be removed from the data structure.
        // System.out.println(rideNumber+" "+newTripDuration);
        RbtRide rbtRideLocation=Rbt.searchRide(rideNumber);
        if(rbtRideLocation==null) return;
        MinHeapRide minheapRideLocation=rbtRideLocation.minheapPtr;

        int existingTripDuration=rbtRideLocation.tripDuration;
        if(newTripDuration<=existingTripDuration) rbtRideLocation.tripDuration=newTripDuration;
        else if(existingTripDuration<newTripDuration && newTripDuration<=(2*existingTripDuration))
        {
            cancelRide(rideNumber);
            insertData(rideNumber, rbtRideLocation.rideCost+10, newTripDuration);
        }
        else if(newTripDuration>(2*existingTripDuration))
        {   
            if(rbtRideLocation!=null)
                cancelRide(rbtRideLocation.rideNumber);
        }

        
    }


    private static void pointerTester()
    {

    }


    private static List<String> processInputFile(List<String> methodList)
    {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              methodList.add(myReader.nextLine());
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

        // REMOVING SPACES IN THE FILE
        for(int i=0;i<methodList.size();i++)
        {
            methodList.set(i,methodList.get(i).replaceAll("\\s*",""));
        }
        // System.out.println(methodList);

        return methodList;
    }



    public static void main(String[] args) {


        // AUTOMATED TASK
        // String fileName=args[0];
        // List<String> methodList=new ArrayList<>();
        // methodList=processInputFile(methodList);
        // for(String str:methodList)
        // {   
        //     String functionName=str.substring(0, str.indexOf("("));
        //     String functionParameters=str.substring(str.indexOf("(")+1,str.indexOf(")"));
        //     String[] parameterList=functionParameters.split(",");
        //     int paramLen=parameterList.length;
        //     int param1=-1,param2=-1,param3=-1;
        //     if(paramLen==1) 
        //     {   
        //         // System.out.println(1); 
                
        //         try
        //         {
        //             param1=Integer.parseInt(parameterList[0]);

        //         }
        //         catch(Exception e)
        //         {

        //         }

        //     }
        //     else if(paramLen==2) 
        //     {   
        //         // System.out.print(2);
        //         param1=Integer.parseInt(parameterList[0]);
        //         param2=Integer.parseInt(parameterList[1]);
        //     }
        //     else if(paramLen==3) 
        //     {   
        //         // System.out.println(3); 
        //         param1=Integer.parseInt(parameterList[0]);
        //         param2=Integer.parseInt(parameterList[1]);
        //         param3=Integer.parseInt(parameterList[2]);
        //     }





        //     if(functionName.equals("Insert"))
        //     {
        //         insertData(param1, param2, param3);
        //     }
        //     else if(functionName.equals("GetNextRide"))
        //     {
        //         getNextRide();
        //     }
        //     else if(functionName.equals("Print"))
        //     {
        //         if(paramLen==1) printRideNumber(param1);
        //         else printRideNumber(param1, param2);

        //     }
        //     else if(functionName.equals("UpdateTrip"))
        //     {
        //         updateTrip(param1, param2);
        //     }

        // }



        // MANUAL INSERT
        insertData(25,98,46);
        getNextRide();
        getNextRide();
        insertData(42,17,89);
        insertData(9,76,31);
        insertData(53,97,22);
        getNextRide();
        insertData(68, 40, 51);
        getNextRide();
        // printRideNumber(1, 100);
        updateTrip(53, 15);
        insertData(96,28,82) ;
        insertData(73,28,56) ;
        updateTrip(9, 88);
        getNextRide();
        printRideNumber(9);
        insertData(20,49,59); 
        insertData(62,7,10);
        cancelRide(20);
        insertData(25, 49, 46);
        updateTrip(62, 15);
        getNextRide();
        // printRideNumber(1, 100);
        insertData(53, 28, 19);
        // printRideNumber(1, 100);
        printRideNumber(1);
        printRideNumber(-1);
        // printElements();
        


    }
}



////////////////////////////////////////GATOR TAXI////////////////////////////////////////