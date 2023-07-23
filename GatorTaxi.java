import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;


////////////////////////////////////////MIN HEAP////////////////////////////////////////

// RIDE NODE IN MIN HEAP
class MinHeapRide
{   
    int rideNumber;
    int rideCost;
    int tripDuration;
    RbtRide rbtPtr;
    int objectCurrIndex;

    MinHeapRide(){}

    MinHeapRide(int rideNumber,int rideCost,int tripDuration)
    {
        this.rideNumber=rideNumber;
        this.rideCost=rideCost;
        this.tripDuration=tripDuration;
        this.rbtPtr=null;
        this.objectCurrIndex=-1;
    }
}

// MIN HEAP CLASS
class MinHeap {

    public static int lastIndex=-1;
    public static int currIndex=-1;
    public static MinHeapRide[] minheapRideArray=new MinHeapRide[2000];

    // MIN HEAP INSERT NEW RIDE.
    public static int insertRide(int rideNumber,int rideCost,int tripDuration)
    {   
        lastIndex++;
        minheapRideArray[lastIndex]=new MinHeapRide(rideNumber,rideCost,tripDuration);
        MinHeapRide temp=minheapRideArray[lastIndex];
        currIndex=lastIndex;
        minheapRideArray[currIndex].objectCurrIndex=currIndex;

        // CHECKING IF MIN HEAP PROPERTIES ARE INTACT AND HEAPIFY.
          
        insertMinHeapify(currIndex);
   
        return temp.objectCurrIndex;

    }

    public static void insertMinHeapify(int curr)
    {   
        // THIS METHOD HEAPIFIES THE MINHEAP FROM BOTTOM TO TOP SINCE WE INSERT THE NEW RIDE AT THE END.
        int parent=(curr-1)/2;

        if(minheapRideArray[parent].rideCost>=minheapRideArray[curr].rideCost)
        {
            if(minheapRideArray[parent].rideCost==minheapRideArray[curr].rideCost)
            {
                if(minheapRideArray[parent].tripDuration>minheapRideArray[curr].tripDuration)
                {
                    swapRides(parent,curr);
                }
            }
            else
            {
                swapRides(parent, curr);
            }
        }

        if(parent>0)
        {
            insertMinHeapify(parent);
        }
    }

    public static void deleteRide(MinHeapRide node)
    {   
        // DELETES THE RIDE FROM MINHEAP. REPLACES FIRST ELEMENT WITH THE LAST ELEMENT AND HEAPIFIES FROM TOP TO BOTTOM.
        if(lastIndex<=-1)
        {
            gatorTaxi.sb.append("(0,"+"0,"+"0)"+"\n");
            return;
        }
        if(lastIndex==0)
        {
            minheapRideArray[0]=null;
            node.rbtPtr.minheapPtr=null;
            lastIndex--;
            return;
        }

        minheapRideArray[node.objectCurrIndex]=minheapRideArray[lastIndex];   
        minheapRideArray[lastIndex]=null;
        lastIndex--;

        if(lastIndex!=-1)   
        {
            minHeapify(node.objectCurrIndex);
            
        }
        return;
    }

    public static MinHeapRide getNextRide()
    {   
        // RETURNS AND  DELETES THE NODE AT THE TOP OF THE MINHEAP. FETCHING RIDE TAKES O(1) TIME.
        if(lastIndex==-1) 
        {
            
            gatorTaxi.sb.append("No active ride requests"+"\n");
            return null;
        }
        if(lastIndex==0)
        {   
            MinHeapRide nextRideNumber=minheapRideArray[0];
            minheapRideArray[lastIndex]=null;
            lastIndex--;
            return nextRideNumber;
        }
        MinHeapRide nextRideNumber=minheapRideArray[0];
        minheapRideArray[0]=minheapRideArray[lastIndex];
        minheapRideArray[0].objectCurrIndex=0;
        minheapRideArray[lastIndex]=null;
        lastIndex--;
        
        if(nextRideNumber!=null)
        {
            minHeapify(0);
            return nextRideNumber;
        }
        else 
        {   
            return null;

        }
        
    }


    public static void minHeapify(int currentIndex)
    {   
        // METHOD TO MINHEAPIFY FROM TOP TO BOTTOM.
        if((currentIndex*2)+1>lastIndex && (currentIndex*2)+2>lastIndex)
        {
            return;
        }

        int nextIndex=currentIndex;

        if((currentIndex*2)+2<=lastIndex)
        {
            nextIndex=minheapRideArray[(currentIndex*2)+1].rideCost<minheapRideArray[(currentIndex*2)+2].rideCost?(currentIndex*2+1):(currentIndex*2+2);
        }
        else
        {
            nextIndex=(currentIndex*2+1);
        }

        if((currentIndex*2+1)<=lastIndex || (currentIndex*2+2)<=lastIndex)
        {   
            if(currentIndex*2+2<=lastIndex)
            {   
                if(minheapRideArray[currentIndex].rideCost>=minheapRideArray[(currentIndex*2)+1].rideCost || minheapRideArray[currentIndex].rideCost>=minheapRideArray[(currentIndex*2)+2].rideCost)
                {   

                    swapRides(currentIndex,nextIndex);
                    minHeapify(nextIndex);
                }
            }
            else
            {
                if(minheapRideArray[currentIndex].rideCost>=minheapRideArray[(currentIndex*2)+1].rideCost)
                {
                    swapRides(currentIndex,nextIndex);
                    minHeapify(nextIndex);
                }
            }

            
        }
        
        
    }

    public static void swapRides(int x,int y)
    {   
        // SWAP TWO RIDES IN THE MINHEAP.
        if(minheapRideArray[x].rideCost>=minheapRideArray[y].rideCost)
        {
            if(minheapRideArray[x].rideCost==minheapRideArray[y].rideCost)
            {
                if(minheapRideArray[x].tripDuration>minheapRideArray[y].tripDuration)
                {
                    MinHeapRide temp=minheapRideArray[x];
                    minheapRideArray[x]=minheapRideArray[y];
                    minheapRideArray[y]=temp;
                    minheapRideArray[x].objectCurrIndex=x;
                    minheapRideArray[y].objectCurrIndex=y;
                }
                else
                {
                    // NO ACTION NEEDED
                }
            }
            else
            {
                MinHeapRide temp=minheapRideArray[x];
                minheapRideArray[x]=minheapRideArray[y];
                minheapRideArray[y]=temp;
                minheapRideArray[x].objectCurrIndex=x;
                minheapRideArray[y].objectCurrIndex=y;
            }
        }

    }

}


////////////////////////////////////////MIN HEAP////////////////////////////////////////






////////////////////////////////////////RED BLACK TREE///////////////////////////////////
class RbtRide
{   
    // NODE IN THE RED BLACK TREE
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
        if(RbtRideTempPtr==null)
        {    
            gatorTaxi.sb.append("Duplicate RideNumber");
            try
            {
                try
                {
                    Files.write(Paths.get("output_file.txt"), gatorTaxi.sb.toString().getBytes());

                }
                catch(Exception e)
                {
                    System.out.println("Exception");
                }
                System.exit(0);
            }
            catch(Exception e)
            {
                System.out.println("Exception");
            }
        }
        root.color=0;
        return RbtRideTempPtr;
    }

    public static RbtRide deleteRide(int rideNumber)
    {
        RbtRide foundRide=bstDelete(rideNumber);
        return foundRide;
    }

    private static RbtRide bstInsert(int rideNumber,int rideCost,int tripDuration)
    {   
        // INSERT THE NEW NODE AS A RED NODE BY DEFAULT.
        RbtRide node=new RbtRide(rideNumber,rideCost,tripDuration);
        node.left=null;
        node.right=null;
        node.parent=null;
        node.color=1;

        // pp->PARENT POINTER,p->CURRENT POINTER
        RbtRide pp=null;
        RbtRide p=root;

        while(p!=null)
        {   
            pp=p;
            if(p.rideNumber==rideNumber)
            {
                return null;
            }
            if(p.rideNumber>node.rideNumber)
            {
                p=p.left;
            }
            else
            {
                p=p.right;
            }
            
        }

        node.parent=pp;

        if(pp==null) 
        {
            root=node;
        }
        else if(node.rideNumber>pp.rideNumber)
        {
            pp.right=node;
        }
        else
        {
            pp.left=node;
        }

        if(node.parent==null)
        {
            node.color=0;
            return node;
        }
        if(node.parent.parent==null) return node;
        
        insertBalance(node);

        root.color=0;

        return node;
        
    }

    private static void insertBalance(RbtRide p)
    {   
        // BALANCE COLORS AND ROTATIONS AFTER NORMAL BST INSERT.
        // p IS CURRENT NODE. d is 2ND CHILD OF GRANDPARENT.

        RbtRide d=null;
        while(p.parent.color==1)
        {   
            if(p.parent.parent!=null)
            {
                if(p.parent==p.parent.parent.right)
                {   
                    d=p.parent.parent.left;
                    if(d==null) 
                    {   
                        rotateRR(p.parent.parent);
                        p.parent.color=0;
                        p.parent.left.color=1;
                        return;
                    }
                    if(d.color==1)
                    {   

                        p.parent.color=0;
                        d.color=0;
                        p.parent.parent.color=1;
                        p=p.parent.parent;
                    }
                    else
                    {
                        if(p==p.parent.left)
                        {   
                            p=p.parent;
                            rotateLL(p);
                        }
                        p.parent.color=1;
                        p.parent.parent.color=1;
                        rotateRR(p.parent.parent);
                    }
                }
                else
                {   
                    d=p.parent.parent.right;
                    if(d==null) 
                    {
                        rotateLL(p.parent.parent);
                        p.parent.color=0;
                        p.parent.right.color=1;
                        return;
                    }
                    if(d.color==1)
                    {
                        p.parent.color=0;
                        d.color=0;
                        p.parent.parent.color=1;
                        p=p.parent.parent;
                    }
                    else
                    {
                        if(p==p.parent.right)
                        {
                            p=p.parent;
                            rotateRR(p);
                        }
                        p.parent.color=1;
                        p.parent.parent.color=1;
                        rotateLL(p.parent.parent);
                    }
                }
            }

            if(p==root) 
            {   
                root.color=0;
                break;
            }
        }  

    }

    

    private static RbtRide bstDelete(int rideNumber)
    {
        // NORMAL BST DELETE. DELETED NODE IS REPLACED BY LOWEST NODE IN CURRENT NODE'S RIGHT SUBTREE.
        RbtRide curr=root;
        while(curr!=null)
        {   
            if(curr.rideNumber==rideNumber) break;
            if(curr.rideNumber>rideNumber)
            {   
                // System.out.println(curr.left);
                curr=curr.left;
            }
            else
            {   
                curr=curr.right;
            }
            
        }
        
        // QUIT IF NODE IS NOT FOUND
        if(curr==null) return null;

        // RETURN NODE IF THE PRESENT NODE IS AT ROOT AND THE ONLY NODE
        if(curr.left==null && curr.right==null && curr.parent==null) 
        {   
            root=null;
            return curr;
        }
        // BEGIN DELETE

        RbtRide y=curr;
        RbtRide x,z;

        z=y;
        int prevColor=z.color;
        if(y.left==null)
        {
            x=y.right;
            switchNodes(y,y.right);
        }
        else if(y.right==null)
        {
            x=y.left;
            switchNodes(y,y.left);

        }
        else
        {   
            RbtRide nodeReplacedBy=y.right; 
            while(nodeReplacedBy.left!=null)
            {
                nodeReplacedBy=nodeReplacedBy.left;
            }
            z=nodeReplacedBy;
            prevColor=z.color;
            x=z.right;
            
            if(z.parent==y) 
            {
                if(x!=null)
                    x.parent=z;
                else
                {
                    switchNodes(y,z);
                }

            }

            else if(x==null && z!=null && prevColor==0)
            {   
                y.rideCost=z.rideCost;
                y.rideNumber=z.rideNumber;
                y.tripDuration=z.tripDuration;
                y.color=z.color;

                if(z==z.parent.left) z.parent.left=null;
                else z.parent.right=null;
                MinHeap.deleteRide(y.minheapPtr);
                MinHeap.minHeapify(0);

                y.minheapPtr=z.minheapPtr;

                if(y.parent==null) 
                {
                    
                    root=y;
                    
                }
                return null;
            }
            else
            {   
                switchNodes(y,z.right);

                if(z.right!=null)
                {
                    z.right = y.right;
				    z.right.parent = z;
                
                }

            }

            switchNodes(y,z);
            z.left = y.left;
			z.left.parent = z;
			z.color = y.color;

        }

        if (prevColor == 0)
        {
			deleteBalance(x);
		}

        return curr;
        
    }

    private static void deleteBalance(RbtRide v)
    {   
        // BALANCE COLORS AND ROTATION.
        RbtRide y;
        if(v==null) return;
		while (v != root && v.color == 0) 
        {   
			if (v == v.parent.left)
             {
				y = v.parent.right;
				if (y.color == 1) 
                {
					y.color = 0;
					v.parent.color = 1;
					rotateRR(v.parent);
					y = v.parent.right;
				}

				if (y.left.color == 0 && y.right.color == 0) 
                {

					y.color = 1;
					v = v.parent;
				} else {
					if (y.right.color == 0) {
						
						y.left.color = 0;
						y.color = 1;
						rotateLL(y);
						y = v.parent.right;
					} 

					y.color = v.parent.color;
					v.parent.color = 0;
					y.right.color = 0;
					rotateRR(v.parent);
					v = root;
				}
			} else {
				y = v.parent.left;
				if (y.color == 1) {
					
					y.color = 0;
					v.parent.color = 1;
					rotateLL(v.parent);
					y = v.parent.left;
				}

				if (y.right.color == 0 && y.right.color == 0) {
					
					y.color = 1;
					v = v.parent;
				} else {
					if (y.left.color == 0) {
						
						y.right.color = 0;
						y.color = 1;
						rotateLL(y);
						y = v.parent.left;
					} 

					
					y.color = v.parent.color;
					v.parent.color = 0;
					y.left.color = 0;
					rotateLL(v.parent);
					v = root;
				}
			} 
		}
		v.color = 0;

    }

    private static void switchNodes(RbtRide pp,RbtRide p)
    {
        if (pp.parent == null) {
			root = p;
		} else if (pp == pp.parent.left){
			pp.parent.left = p;
		} else {    
			pp.parent.right = p;

		}
        if(pp!=null && p!=null)
		    p.parent = pp.parent;
    }

    private static void rotateRR(RbtRide pp)
    {       
        // RR ROTATION AT NODE pp
        RbtRide p=pp.right;
        pp.right=p.left;
        if(pp.left!=null) 
        {
            if(p.left!=null)
                p.left.parent=pp;
        }
        p.parent=pp.parent;
        if(pp.parent==null) root=p;
        else if(pp==pp.parent.left) pp.parent.left=p;
        else pp.parent.right=p;
        p.left=pp;
        pp.parent=p;
    }
    private static void rotateLL(RbtRide pp)
    {   
        // LL ROTATION AT NODE pp
        RbtRide p=pp.left;
        pp.left=p.right;
        if(pp.right!=null) 
        {
            if(p.right!=null)   
                p.right.parent=pp;
        }
        p.parent=pp.parent;
        if(pp.parent==null) root=p;
        else if(pp==pp.parent.right) pp.parent.right=p;
        else pp.parent.left=p;
        p.right=pp;
        pp.parent=p;

    }

    public static RbtRide searchRide(int rideNumber)
    {   
        // SEARCH BST FOR RIDE WITH rideNumber. RETURN ITS LOCATION IF FOUND, NULL IF NOT FOUND.
        RbtRide curr=root;

        while(curr!=null)
        {   
            
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
        else return curr;
    }

    // PRINTING WHILE TESTING
    // public static void printElements(RbtRide curr)
    // {
    //     if(curr==null) return;
    //     printElements(curr.left);
    //     System.out.println(curr.rideNumber+" "+curr.rideCost+" "+curr.tripDuration);
    //     printElements(curr.right);
    // }
    
}



////////////////////////////////////////RED BLACK TREE////////////////////////////////////



////////////////////////////////////////GATOR TAXI////////////////////////////////////////


public class gatorTaxi {    

    // MAIN CLASS OF THE PROGRAM. ALL METHODS OF OTHER CLASSES ARE CALLED FROM THIS FILE.

    // STRING BUILDER FUNCTION TO STORE THE STRING WE WANT TO PRINT
    static StringBuilder sb=new StringBuilder();
    static int rangePrinterFlag=0;

    // THIS METHOD STORES THE RIDE VALUES IN sb IN RANGE BETWEEN ride1 and ride2
    private static RbtRide rangedPrint(RbtRide curr, int ride1, int ride2) 
    {
         
        if (curr == null) {
            return null;
        }
 
        if (ride1<curr.rideNumber) 
        {
            rangedPrint(curr.left, ride1, ride2);
        }
  
        if (ride1 <= curr.rideNumber && ride2 >= curr.rideNumber) 
        {
            rangePrinterFlag=1;
            sb.append("("+curr.rideNumber+","+curr.rideCost+","+curr.tripDuration+"),");
        }
 
        rangedPrint(curr.right, ride1, ride2);

        return curr;

    }

    // PRINT FUNCTION FOR TESTING PURPOSE
    private static void printElements()
    {   
        
        for(int i=0;i<=MinHeap.lastIndex;i++)
        {
            System.out.println(MinHeap.minheapRideArray[i].rideNumber+" "+MinHeap.minheapRideArray[i].rideCost+" "+MinHeap.minheapRideArray[i].tripDuration);
            System.out.println(MinHeap.minheapRideArray[i].rbtPtr.rideNumber+" "+MinHeap.minheapRideArray[i].rbtPtr.rideCost+" "+MinHeap.minheapRideArray[i].rbtPtr.tripDuration);
        
        }
        System.out.println();
        
    }

    // PRINT RIDE NUMBER. SIMPLE SEARCH IN RBT. SEARCH TAKES O(logn).
    private static void Print(int rideNumber) {


        RbtRide curr_location=Rbt.searchRide(rideNumber);
        if(curr_location==null) 
        {
            sb.append("(0,0,0)\n");

        }
        else sb.append("("+curr_location.rideNumber+","+curr_location.rideCost+","+curr_location.tripDuration+")\n");
        
    }

    // PRINT RIDE NUMBER BETWEEN SPECIFIED RANGE
    private static void Print(int rideNumber1,int rideNumber2) {

        rangePrinterFlag=0;

        RbtRide temp1=rangedPrint(Rbt.root,rideNumber1,rideNumber2);
        if(temp1==null || rangePrinterFlag==0)
        {
            sb.append("(0,0,0)\n");
            return;
        }
        String temp=sb.toString();
        char c=temp.charAt(temp.length()-1);
        if(c==',')
        {   
            sb.setLength(0);
            sb.append(temp.substring(0, temp.length()-1));
        }
        sb.append("\n");
        
    }

    // INSERT SPECIFIED DATA INTO RBT AND MINHEAP SERIALLY.
    private static void Insert(int rideNumber,int rideCost,int tripDuration) {

        RbtRide newRbtRidePtr=Rbt.insertRide(rideNumber, rideCost, tripDuration);

        if(newRbtRidePtr!=null)
        {
            int minHeapIndex=MinHeap.insertRide(rideNumber, rideCost, tripDuration);
            MinHeap.minheapRideArray[minHeapIndex].rbtPtr=newRbtRidePtr;
            newRbtRidePtr.minheapPtr=MinHeap.minheapRideArray[minHeapIndex];

        }

        if(newRbtRidePtr.rideNumber==-1)
        {   
            System.out.println("Duplicate RideNumber");
            sb.append("Duplicate RideNumber");
            try
            {
                
                try
                {
                    Files.write(Paths.get("output_file.txt"), sb.toString().getBytes());

                }
                catch(Exception e)
                {
                    System.out.println("Exception");
                }
                System.exit(0);
            }
            catch(Exception e)
            {
                System.out.println("Exception");
            }
            
        }
       
    }

    // FETCH NEXT RIDE FROM MINHEAP IN O(1) TIME. FETCH THE SAME RIDE IN RBT IN O(1) USING POINTER. DELETE RIDE AFTER SUCCESSFULL FETCH.
    private static void GetNextRide() {
        
        MinHeapRide minHeapRide=MinHeap.getNextRide();
        
        if(minHeapRide!=null)
        {   
            sb.append("("+minHeapRide.rideNumber+","+minHeapRide.rideCost+","+minHeapRide.tripDuration+")"+"\n");
            Rbt.deleteRide(minHeapRide.rideNumber);
            
        }
        else
        {
            Rbt.root=null;
        }


        
    }

    // SEARCH RIDE IN RBT IN O(logn) TIME. DELETE IT IN RBT. USE POINTER TO FETCH RIDE FROM MINHEAP. ARBITRARY DELETE IN MINHEAP.
    private static void CancelRide(int rideNumber) {
        

        RbtRide node=Rbt.deleteRide(rideNumber);
        if(node!=null)
        {
            MinHeap.deleteRide(node.minheapPtr);
            MinHeap.minHeapify(0);

        }

        
    }

    // UPDATE RIDE ACCORDING TO GIVEN CONDITIONS. FETCH IN RBT IN O(logn). USE POINTER TO REACH MINHEAP LOCATION IN O(1).
    private static void UpdateTrip(int rideNumber,int newTripDuration) {
        RbtRide rbtRideLocation=Rbt.searchRide(rideNumber);
        if(rbtRideLocation==null) return;
        
        MinHeapRide minheapRideLocation=rbtRideLocation.minheapPtr;
        if(minheapRideLocation==null) 
        {
            return;
        }
        int existingTripDuration=rbtRideLocation.tripDuration;
        if(newTripDuration<=existingTripDuration) 
        {   
            
            rbtRideLocation.tripDuration=newTripDuration;
            minheapRideLocation.tripDuration=newTripDuration;
        }
        else if(existingTripDuration<newTripDuration && newTripDuration<=(2*existingTripDuration))
        {   
            rbtRideLocation.rideCost=rbtRideLocation.rideCost+10;
            rbtRideLocation.tripDuration=newTripDuration;
            minheapRideLocation.rideCost=minheapRideLocation.rideCost+10;
            minheapRideLocation.tripDuration=newTripDuration;
            MinHeap.minHeapify(minheapRideLocation.objectCurrIndex);
            
        }
        else if(newTripDuration>(2*existingTripDuration))
        {   
            
            if(rbtRideLocation!=null)
                CancelRide(rbtRideLocation.rideNumber);
        }
  
    }


    // METHOD TO PROCESS INPUT FILE. CREATE A LIST OF METHOD CALLS. EACH ELEMENT OF THIS LIST IS A STRING.
    private static List<String> processInputFile(List<String> methodList,String fileName)
    {
        try 
        {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) 
            {
              methodList.add(myReader.nextLine());
            }
             myReader.close();
        } 

        catch (FileNotFoundException e) 
        {
            System.out.println("EXCEPTION");
            e.printStackTrace();
        }

        // REMOVING SPACES IN THE FILE IF ANY
        for(int i=0;i<methodList.size();i++)
        {
            methodList.set(i,methodList.get(i).replaceAll("\\s*",""));
        }
        

        return methodList;
    }



    public static void main(String[] args) {


        // PROCESS FILE AND CALLS.
        String fileName=args[0];
        List<String> methodList=new ArrayList<>();
        methodList=processInputFile(methodList,fileName);
        
        for(String str:methodList)
        {   
            String functionName=str.substring(0, str.indexOf("("));
            String functionParameters=str.substring(str.indexOf("(")+1,str.indexOf(")"));
            String[] parameterList=functionParameters.split(",");
            int paramLen=parameterList.length;
            int param1=-1,param2=-1,param3=-1;
            if(paramLen==1) 
            {   
                try
                {
                    if(parameterList[0].equals(""))
                    {

                    }
                    else
                    {
                        param1=Integer.parseInt(parameterList[0]);

                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }

            }
            else if(paramLen==2) 
            {   
                param1=Integer.parseInt(parameterList[0]);
                param2=Integer.parseInt(parameterList[1]);
            }
            else if(paramLen==3) 
            {   
                param1=Integer.parseInt(parameterList[0]);
                param2=Integer.parseInt(parameterList[1]);
                param3=Integer.parseInt(parameterList[2]);
            }

            // CALL APPROPRIATE METHODS AS REQUIRED.
            if(functionName.equals("Insert"))
            {
                Insert(param1, param2, param3);
            }
            else if(functionName.equals("GetNextRide"))
            {
                GetNextRide();
            }
            else if(functionName.equals("Print"))
            {
                if(paramLen==1) Print(param1);
                else Print(param1, param2);

            }
            else if(functionName.equals("UpdateTrip"))
            {
                UpdateTrip(param1, param2);
            }
            else if(functionName.equals("CancelRide"))
            {
                CancelRide(param1);
            }

        }

        


        // TEST CASE 1
        // Insert(25,98,46);
        // GetNextRide();
        // GetNextRide();
        // Insert(42,17,89);
        // Insert(9,76,31);
        // Insert(53,97,22);
        // GetNextRide(); 
        // Insert(68, 40, 51);
        // GetNextRide();
        // Print(1,100);
        // UpdateTrip(53, 15);
        // Insert(96,28,82) ;
        // Insert(73,28,56) ;
        // // printElements();
        // UpdateTrip(9, 88);
        // // printElements();
        // GetNextRide();
        // Print(9);
        // Insert(20,49,59); 
        // Insert(62,7,10);
        // CancelRide(20);
        // Insert(25, 49, 46);
        // UpdateTrip(62, 15);
        // GetNextRide();
        // Print(1, 100);
        // // printElements();
        // Insert(53, 28, 19);
        // Print(1, 100);
        // Print(1);
        // Print(-1);
        



        // TEST CASE 2
        // Insert(5,50,120);
        // Insert(4,30,60);
        // Insert(7,40,90);
        // Insert(3,20,40);
        // Insert(1,10,20);
        // Print(2);
        // Insert(6,35,70);
        // Insert(8,45,100);
        // Print(3);
        // Print(1,6);
        // UpdateTrip(6,75);
        // Insert(10,60,150);
        // GetNextRide();
        // CancelRide(5);
        // UpdateTrip(3,22);
        // Insert(9,55,110);
        // GetNextRide();
        // UpdateTrip(6,95);
        // Print(6);
        // Print(5,9);
        // GetNextRide();
        // CancelRide(7);
        // Print(7);
        // Insert(11,70,170);
        // GetNextRide();
        // Insert(12,80,200);
        // Print(12);
        // UpdateTrip(11,210);
        // GetNextRide();
        // CancelRide(14);
        // UpdateTrip(12,190);
        // Insert(13,70,220);
        // GetNextRide();
        // Insert(14,100,40);
        // UpdateTrip(14,100);
        // CancelRide(12);
        // Print(11,14);
        // GetNextRide();
        // Insert(15,20,35);
        // Print(14);
        // Print(10,16);
        // GetNextRide();
        // UpdateTrip(13,30);
        // Print(13);
        // GetNextRide();
        // Print(12);
        // CancelRide(19);
        // Insert(16,60,45);
        // Insert(17,70,25);
        // UpdateTrip(16,60);
        // GetNextRide();
        // Print(11);
        // Print(16,18);
        // Insert(18,65,130);
        // Insert(12,40,30);
        // Insert(8,60,97);
        // UpdateTrip(16,82);
        // Insert(20,16,75);
        // UpdateTrip(18,300);
        // Print(23);
        // Print(12,21);
        // CancelRide(12);
        // GetNextRide();
        // CancelRide(25);
        // Print(20,26);
        // GetNextRide();
        // UpdateTrip(16,124);
        // Insert(7,125,54);
        // GetNextRide();
        // Print(16);
        // Insert(22,80,85);
        // Insert(15,90,85);
        // UpdateTrip(22,195);
        // GetNextRide();
        // Insert(23,49,46);
        // Insert(1,56,85);
        // UpdateTrip(16,300);
        // GetNextRide();
        // Print(1,30);
        // CancelRide(1);
        // GetNextRide();
        // GetNextRide();
        // Insert(24,21,46);
        // Insert(17,12,37);
        // GetNextRide();
        // Print(16);
        // Insert(24,80,85);
        // Insert(15,90,85);
        // CancelRide(28);
        // UpdateTrip(23,450);
        // GetNextRide();
        // Print(24);
        // Print(22,26);
        // CancelRide(29);
        // Print(28);
        
        try
        {
            Files.write(Paths.get("output_file.txt"), sb.toString().getBytes());

        }
        catch(Exception e)
        {
            System.out.println("EXCEPTION");
        }


    }
}



////////////////////////////////////////GATOR TAXI////////////////////////////////////////