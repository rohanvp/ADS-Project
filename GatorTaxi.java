import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.lang.reflect.Method;


////////////////////////////////////////MIN HEAP////////////////////////////////////////

// RIDE OBJECT OF MIN HEAP
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

    public static List<MinHeapRide> minHeapList=new ArrayList<>();
    public static int heapSize=0;
    public static int lastIndex=-1;
    public static int currIndex=-1;
    public static MinHeapRide[] minheapRideArray=new MinHeapRide[2000];

    // MIN HEAP INSERT NEW RIDE
    public static int insertRide(int rideNumber,int rideCost,int tripDuration)
    {   
        lastIndex++;
        minheapRideArray[lastIndex]=new MinHeapRide(rideNumber,rideCost,tripDuration);
        currIndex=lastIndex;

        // CHECKING IF MIN HEAP PROPERTIES ARE INTACT. SWAP ELEMENTS IF PROPERTY IS VIOLATED
        while(currIndex>0)
        {   
            // System.out.println(3);  
            // int parent=(currIndex-1)/2;
          
            insertMinHeapify(currIndex);
            
            // if(minheapRideArray[(currIndex-1)/2].rideCost==minheapRideArray[currIndex].rideCost)
            // {   
            //     // System.out.println(1);
            //     MinHeapRide temp=minheapRideArray[parent];
            //     if(minheapRideArray[parent].tripDuration>minheapRideArray[currIndex].tripDuration)
            //     {
            //         // minHeapList.set(parent,minheapRideArray[currIndex]);
            //         minheapRideArray[parent]=minheapRideArray[currIndex];
            //         minheapRideArray[parent].objectCurrIndex=parent;
            //         // minHeapList.set(currIndex, temp);
            //         minheapRideArray[currIndex]=temp;
            //         // if(currIndex!=-1)
            //             minheapRideArray[currIndex].objectCurrIndex=currIndex;
            //         currIndex=parent;
            //     }
            //     else
            //     {
            //         currIndex--;
            //     }
            // }
            // else if(minheapRideArray[(currIndex-1)/2].rideCost>minheapRideArray[currIndex].rideCost)
            // {   
            //     // System.out.println(2);
            //     // minHeapList.set(parent,minheapRideArray[currIndex]);
            //     // minheapRideArray[parent]=minheapRideArray[currIndex];
            //     // minheapRideArray[parent].objectCurrIndex=parent;
            //     // // minHeapList.set(currIndex, temp);
            //     // minheapRideArray[currIndex]=temp;
            //     // // if(currIndex!=-1)
            //     //         minheapRideArray[currIndex].objectCurrIndex=currIndex;
            //     // currIndex=parent;
            //     // MinHeapRide temp=minheapRideArray[parent];
            //     // int tempIndex=minheapRideArray[parent].objectCurrIndex;
            //     // minheapRideArray[parent]=minheapRideArray[currIndex];
            //     // minheapRideArray[parent].objectCurrIndex=tempIndex;
            //     // tempIndex=minheapRideArray[currIndex].objectCurrIndex;
            //     // minheapRideArray[currIndex]=temp;
            //     // minheapRideArray[currIndex].objectCurrIndex=tempIndex;
            //     swapRides(parent, currIndex);

            // }
            // else
            // {   
            //     // System.out.println(4);
            //     currIndex--;
            // }
            // System.out.println(currIndex);
            currIndex--;
            
        }
        // MinHeapRide newRide=minheapRideArray[currIndex];
        // newRide.objectCurrIndex=currIndex;
        
        // System.out.println(currIndex);
        return currIndex;

    }

    public static void tempDeleteRide(int rideNumber)
    {   
        if(rideNumber==9)
            System.out.println(9);
        if(lastIndex==-1)
            return;
        int del=0;
        for(int i=0;i<=lastIndex;i++)
        {
            if(minheapRideArray[i].rideNumber==rideNumber)
            {   
                del=i;
                break;
            }
        }

        MinHeapRide temp1=minheapRideArray[del];
        MinHeapRide temp2=minheapRideArray[lastIndex];
        int tempIndex=-1;

        // temp1.rbtPtr.minheapPtr=null;
        tempIndex=minheapRideArray[del].objectCurrIndex;
        minheapRideArray[del]=minheapRideArray[lastIndex];
        minheapRideArray[del].objectCurrIndex=tempIndex;
        lastIndex--;
        minHeapify(del);
    }

    public static void insertMinHeapify(int curr)
    {   
        
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

    public static MinHeapRide minHeapSearch(int rideNumber)
    {
        for(int i=0;i<=lastIndex;i++)
        {
            if(minheapRideArray[i].rideNumber==rideNumber) return minheapRideArray[i];
        }

        return null;
    }

    public static void deleteRide(MinHeapRide node)
    {   
        
        if(lastIndex<=-1)//chaned 0 to -1 
        {
            gatorTaxi.sb.append("("+"0,"+"0,"+"0)"+"\n");
            return;
        }
        if(lastIndex==0)
        {
            // minHeapList.remove(0);
            minheapRideArray[0]=null;
            node.rbtPtr.minheapPtr=null;
            lastIndex--;
            return;
        }
        
        // System.out.println(node.objectCurrIndex);
        minheapRideArray[node.objectCurrIndex]=minheapRideArray[lastIndex];
        // minHeapList.set(node.objectCurrIndex,minHeapList.get(minHeapList.size()-1));
        minheapRideArray[lastIndex]=null;
        lastIndex--;
        // minHeapList.remove(minHeapList.size()-1);

        if(lastIndex!=-1)   
        {
            minHeapify(node.objectCurrIndex);
            // System.out.println(4);
            // heapSize-=1;
        }
        return;
    }

    public static MinHeapRide getNextRide()
    {   
        // THIS FUNCTION ALSO IMPLEMENTS DELETION
        if(lastIndex==-1) 
        {
            // System.out.println("No Active Rides");
            gatorTaxi.sb.append("No Active Rides"+"\n");
            return null;
        }
        if(lastIndex==0)
        {   
            MinHeapRide nextRideNumber=minheapRideArray[0];
            minheapRideArray[lastIndex]=null;
            lastIndex--;

            // minHeapList.remove(0);
            // heapSize--;
            return nextRideNumber;
        }
        MinHeapRide nextRideNumber=minheapRideArray[0];
        
        // System.out.println(minHeapList);
        // minHeapList.set(0,minHeapList.get(minHeapList.size()-1));
        minheapRideArray[0]=minheapRideArray[lastIndex];
        minheapRideArray[0].objectCurrIndex=0;
        // minHeapList.get(0).objectCurrIndex=0;
        minheapRideArray[lastIndex]=null;
        lastIndex--;
        // int s=minHeapList.size();
        // minHeapList.remove(s-1);
        // System.out.println(minHeapList);
        // System.out.println(nextRideNumber);
        // for(int i=0;i<minHeapList.size();i++)
        // {
        //     System.out.println(minHeapList.get(i).rideCost);
        // }
        
        if(nextRideNumber!=null)
        {
            // heapSize-=1;
            minHeapify(0);
            return nextRideNumber;
        }
        else 
        {   
            return null;

        }
        
    }


    public static void minHeapify(int ci)
    {   
        if((ci*2)+1>lastIndex && (ci*2)+2>lastIndex)
        {
            return;
        }
        MinHeapRide smallest=minheapRideArray[ci];
        // int smallestIndex=ci;
        int swapIndex=ci;

        if((ci*2)+2<=lastIndex)
        {
            swapIndex=minheapRideArray[(ci*2)+1].rideCost<minheapRideArray[(ci*2)+2].rideCost?(ci*2+1):(ci*2+2);
        }
        else
        {
            swapIndex=(ci*2+1);
        }
        
        if((ci*2+1)<=lastIndex || (ci*2+2)<=lastIndex)
        {
            if(ci*2+2<=lastIndex)
            {
                if(minheapRideArray[ci].rideCost>=minheapRideArray[(ci*2)+2].rideCost)
                {
                    swapRides(ci,swapIndex);
                    minHeapify(swapIndex);
                }
            }
            else
            {
                if(minheapRideArray[ci].rideCost>=minheapRideArray[(ci*2)+1].rideCost)
                {
                    swapRides(ci,swapIndex);
                    minHeapify(swapIndex);
                }
            }

            
        }
        



        // if((ci*2+1)<=lastIndex && smallest.rideCost>=minheapRideArray[ci*2+1].rideCost)
        // {
        //     if(smallest.rideCost==minheapRideArray[ci*2+1].rideCost)
        //     {   
        //         if(smallest.tripDuration>minheapRideArray[ci*2+1].tripDuration)
        //         {
        //             // minHeapList.set(ci,minHeapList.get(ci*2+1));
        //             minheapRideArray[ci]=minheapRideArray[ci*2+1];
        //             // if(ci!=-1)
        //                 minheapRideArray[ci].objectCurrIndex=ci;
        //             // minHeapList.get(ci).objectCurrIndex=ci;
        //             // minHeapList.set(ci*2+1, smallest);
        //             minheapRideArray[ci*2+1]=smallest;
        //             // if(ci!=-1)
        //                 minheapRideArray[ci*2+1].objectCurrIndex=ci*2+1;
        //             // minHeapList.get(ci*2+1).objectCurrIndex=ci*2+1;
        //             smallest=minheapRideArray[ci*2+1];
        //             // smallest=minHeapList.get(ci*2+1);
        //             // smallestIndex=(ci*2)+1;
        //         }
        //     }
        //     else
        //     {
        //         // minHeapList.set(ci,minHeapList.get(ci*2+1));
        //         minheapRideArray[ci]=minheapRideArray[ci*2+1];
        //         // if(ci!=-1)
        //             minheapRideArray[ci].objectCurrIndex=ci;
        //         // minHeapList.get(ci).objectCurrIndex=ci;
        //         minheapRideArray[ci*2+1]=smallest;
        //         // minHeapList.set(ci*2+1, smallest);
        //         // if(ci!=-1)
        //             minheapRideArray[ci*2+1].objectCurrIndex=ci*2+1;
        //         // minHeapList.get(ci*2+1).objectCurrIndex=ci*2+1;
        //     }
        //     minHeapify(ci*2+1);

        // }
        // if((ci*2+2)<=lastIndex && smallest.rideCost>=minheapRideArray[ci*2+2].rideCost)
        // {   
        //     if(smallest.rideCost==(minheapRideArray[ci*2+2].rideCost))
        //     {   
        //         if(smallest.tripDuration>minheapRideArray[ci*2+2].tripDuration)
        //         {
        //             // minHeapList.set(ci,minHeapList.get((ci*2+2)));
        //             minheapRideArray[ci]=minheapRideArray[ci*2+2];
        //             // minHeapList.get(ci).objectCurrIndex=ci;
        //             // if(ci!=-1)
        //                 minheapRideArray[ci].objectCurrIndex=ci;
        //             minheapRideArray[ci*2+2]=smallest;
        //             // minHeapList.set((ci*2+2), smallest);
        //             // minHeapList.get((ci*2+2)).objectCurrIndex=(ci*2+2);
        //             // if(ci!=-1)
        //                 minheapRideArray[ci*2+2].objectCurrIndex=(ci*2+2);
        //             smallest=minheapRideArray[(ci*2+2)];
        //             // smallestIndex=(ci*2+2);
        //         }
        //     }
        //     else
        //     {   
        //         if(ci<=lastIndex)
        //         {   
        //             // minHeapList.set(ci,minHeapList.get((ci*2+2)));
        //             // minHeapList.get(ci).objectCurrIndex=ci;
        //             // minHeapList.set((ci*2+2), smallest);
        //             // minHeapList.get((ci*2+2)).objectCurrIndex=(ci*2+2);

        //             minheapRideArray[ci]=minheapRideArray[(ci*2)+2];
        //             // if(ci!=-1)
        //                 minheapRideArray[ci].objectCurrIndex=ci;
        //             minheapRideArray[(ci*2)+2]=smallest;
        //             minheapRideArray[ci*2+2].objectCurrIndex=ci*2+2;

        //         }
                
        //     }
        //     minHeapify(ci*2+2);
        // }
        
        // if(smallestIndex!=ci)
        //     minHeapify(smallestIndex);

        
    }

    public static void swapRides(int x,int y)
    {
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

    // MIN HEAP IMPLEMENTATION

    public static void printElementsMinHeap()
    {
        for(int i=0;i<minHeapList.size();i++)
        {
            System.out.println(minHeapList.get(i).rideNumber+" "+minHeapList.get(i).rideCost+" "+minHeapList.get(i).tripDuration);
        }
    }

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
        if(RbtRideTempPtr==null)
        {    
            gatorTaxi.sb.append("Duplicate RideNumber");
            try
            {
                Files.write(Paths.get("output.txt"), gatorTaxi.sb.toString().getBytes());
                try
                {
                    Files.write(Paths.get("output.txt"), gatorTaxi.sb.toString().getBytes());

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

    // public static RbtRide searchRbtRide(int rideNumber)
    // {
    //     RbtRide curr=root;
    //     while(curr!=null)
    //     {
    //         if(curr.rideNumber==rideNumber)
    //         {

    //         }
    //     }
    // }

    private static RbtRide bstInsert(int rideNumber,int rideCost,int tripDuration)
    {   
        // INSERT THE NEW NODE AS A RED NODE BY DEFAULT
        RbtRide node=new RbtRide(rideNumber,rideCost,tripDuration);
        node.left=null;
        node.right=null;
        node.parent=null;
        node.color=1;

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
        RbtRide d=null;
        while(p.parent.color==1)
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

            if(p==root) 
            {   
                root.color=0;
                break;
            }
        }  

    }

    

    private static RbtRide bstDelete(int rideNumber)
    {
        // RbtRide y=null;
        RbtRide curr=root;

        // SEARCHING FOR THE REQUIRED NODE
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

        // QUIT IF NODE IS NOT FOUND
        if(curr==null) return null;

        // RETURN NODE IF THE PRESENT NODE IS AT ROOT AND THE ONLY NODE
        if(curr.left==null && curr.right==null && curr.parent==null) return curr;

        // BEGIN DELETE

        RbtRide z=curr;
        RbtRide x,y;

        y=z;
        int yOg=y.color;
        if(z.left==null)
        {
            x=z.right;
            
            switchNodes(z,z.right);
        }
        else if(z.right==null)
        {
            x=z.left;
            
            switchNodes(z,z.left);

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
            
            if(y.parent==z) 
            {
                if(x!=null)
                    x.parent=y;
                else
                {
                    switchNodes(z,y);
                }

            }

            
            
            else if(x==null && y!=null && yOg==0)
            {   
                
                z.rideCost=y.rideCost;
                z.rideNumber=y.rideNumber;
                z.tripDuration=y.tripDuration;
                z.color=y.color;
                z.minheapPtr.rbtPtr=z;
                if(y==y.parent.left) y.parent.left=null;
                else y.parent.right=null;
                if(z.parent==null) 
                {
                    
                    root=z;
                    
                }
                return z;
            }
            else
            {   
                RbtRide u=z,v=y.right;
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
			deleteBalance(x);
		}

        return curr;
        
    }

    private static void deleteBalance(RbtRide x)
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

    private static void switchNodes(RbtRide p,RbtRide c)
    {
        if (p.parent == null) {
			root = c;
		} else if (p == p.parent.left){
			p.parent.left = c;
		} else {    
			p.parent.right = c;

		}
        if(p!=null && c!=null)
		    c.parent = p.parent;
    }

    private static void rotateRR(RbtRide pp)
    {       
        System.out.println(7);
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
        System.out.println(6);
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
        else return curr;
    }

    public static void printElements(RbtRide curr)
    {
        if(curr==null) return;
        printElements(curr.left);
        System.out.println(curr.rideNumber+" "+curr.rideCost+" "+curr.tripDuration);
        // System.out.println(curr.minheapPtr.rideNumber+" "+curr.minheapPtr.rideCost+" "+curr.minheapPtr.tripDuration);
        // System.out.println(curr.color);
        // if(curr.parent!=null)
        //     System.out.println(curr.parent.rideNumber+" "+curr.parent.rideCost+" "+curr.parent.tripDuration);
        printElements(curr.right);
    }
    
}



////////////////////////////////////////RED BLACK TREE////////////////////////////////////////



////////////////////////////////////////GATOR TAXI////////////////////////////////////////


public class gatorTaxi {    

    HashMap<Integer,MinHeapRide> minHeapMap=new HashMap<>();
    HashMap<Integer,RbtRide> rbtMap=new HashMap<>();

    // STRING BUILDER FUNCTION TO STORE THE STRING WE WANT TO PRINT
    static StringBuilder sb=new StringBuilder();

    // THIS METHOD STORES THE RIDE VALUES IN sb IN RANGE BETWEEN ride1 and ride2
    private static RbtRide rangedPrint(RbtRide ride, int ride1, int ride2) {
         
        if (ride == null) {
            return null;
        }
 
        if (ride1 < ride.rideNumber) 
        {
            rangedPrint(ride.left, ride1, ride2);
        }
  
        if (ride1 <= ride.rideNumber && ride2 >= ride.rideNumber) 
        {
            
            sb.append("("+ride.rideNumber+","+ride.rideCost+","+ride.tripDuration+"),");
        }
 
        rangedPrint(ride.right, ride1, ride2);

        return ride;

    }

    // PRINT FUNCTION FOR TESTING PURPOSE
    private static void printElements()
    {   
        System.out.println("PRINT MINHEAP ELEMENTS");
        for(int i=0;i<=MinHeap.lastIndex;i++)
        {
            System.out.println(MinHeap.minheapRideArray[i].rideNumber+" "+MinHeap.minheapRideArray[i].rideCost+" "+MinHeap.minheapRideArray[i].tripDuration);
            // System.out.println(MinHeap.minHeapList.get(i).rbtPtr.rideNumber+" "+MinHeap.minHeapList.get(i).rbtPtr.rideCost+" "+MinHeap.minHeapList.get(i).rbtPtr.tripDuration);
        }
        System.out.println();
        System.out.println("PRINT RBT ELEMENTS");
        Rbt.printElements(Rbt.root);
    }

    // PRINT RIDE NUMBER. SIMPLE SEARCH IN RBT.
    private static void Print(int rideNumber) {


        RbtRide curr_location=Rbt.searchRide(rideNumber);
        if(curr_location==null) 
        {
            sb.append("(0,0,0)\n");

        }
        else sb.append("("+curr_location.rideNumber+" "+curr_location.rideCost+" "+curr_location.tripDuration+")\n");
        
    }

    // PRINT RIDE NUMBER BETWEEN SPECIFIED RANGE
    private static void Print(int rideNumber1,int rideNumber2) {

        RbtRide temp1=rangedPrint(Rbt.root,rideNumber1,rideNumber2);
        if(temp1==null)
        {
            sb.append("(0,0,0)\n");
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
        if(newRbtRidePtr.rideNumber==-1)
        {   
            // System.out.println("Duplicate RideNumber");
            sb.append("Duplicate RideNumber");
            try
            {
                Files.write(Paths.get("output.txt"), sb.toString().getBytes());
                try
                {
                    Files.write(Paths.get("output.txt"), sb.toString().getBytes());

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
        else
        {
            int minHeapIndex=MinHeap.insertRide(rideNumber, rideCost, tripDuration);
            MinHeap.minheapRideArray[minHeapIndex].rbtPtr=newRbtRidePtr;
            newRbtRidePtr.minheapPtr=MinHeap.minheapRideArray[minHeapIndex];
        }


        // MinHeapRide newMinHeapRidePtr=MinHeap.insertRide(rideNumber, rideCost, tripDuration);

        ////////////////////////////////////////////////////////////////////////
        // MinHeap.insertRide(rideNumber, rideCost, tripDuration);
        ////////////////////////////////////////////////////////////////////////
        

    }

    // FETCH NEXT RIDE FROM MINHEAP IN O(1) TIME. FETCH THE SAME RIDE IN RBT IN O(1) USING POINTER. DELETE RIDE AFTER SUCCESSFULL FETCH.
    private static void GetNextRide() {
        
        ////////////////////////////////////////////////////////////////////////
        // MinHeap.tempDeleteRide(MinHeap.minheapRideArray[0].rideNumber);
        ////////////////////////////////////////////////////////////////////////

        MinHeapRide minHeapRide=MinHeap.getNextRide();
        if(minHeapRide!=null)
        {   
            sb.append("("+minHeapRide.rideNumber+","+minHeapRide.rideCost+","+minHeapRide.tripDuration+")"+"\n");
            // System.out.println(minHeapRide.rideNumber+" "+minHeapRide.rideCost+" "+minHeapRide.tripDuration);
            Rbt.deleteRide(minHeapRide.rideNumber);
            
        }
        else
        {
            Rbt.root=null;
        }


        
    }

    // SEARCH RIDE IN RBT IN O(1) TIME. DELETE IT IN RBT. USE POINTER TO FETCH RIDE FROM MINHEAP. ARBITRARY DELETE IN MINHEAP.
    private static void CancelRide(int rideNumber) {
        

        RbtRide node=Rbt.deleteRide(rideNumber);
        if(node!=null)
        {
            // MinHeap.deleteRide(node.minheapPtr);
            MinHeap.tempDeleteRide(rideNumber);

        }

        ////////////////////////////////////////////////////////////////////////
        // MinHeap.tempDeleteRide(rideNumber);
        ////////////////////////////////////////////////////////////////////////
        
    }

    // UPDATE RIDE ACCORDING TO GIVEN CONDITIONS. FETCH IN RBT IN O(logn). USE POINTER TO REACH MINHEAP LOCATION IN O(1).
    private static void UpdateTrip(int rideNumber,int newTripDuration) {
        // a) if the new_tripDuration <= existing tripDuration, there would be no action needed.
        // b) if the existing_tripDuration < new_tripDuration <= 2*(existing tripDuration), the driver will
        //    cancel the existing ride and a new ride request would be created with a penalty of 10 on existing rideCost . 
        // We update the entry in the data structure with (rideNumber, rideCost+10, new_tripDuration)
        // c) if the new_tripDuration > 2*(existing tripDuration), the ride would be automatically declined and the ride would be removed from the data structure.
        // System.out.println(rideNumber+" "+newTripDuration);
        RbtRide rbtRideLocation=Rbt.searchRide(rideNumber);
        if(rbtRideLocation==null) return;

        ////////////////////////////////////////////////////////////////////////
        MinHeapRide minheapRideLocation=MinHeap.minHeapSearch(rideNumber);
        ////////////////////////////////////////////////////////////////////////

        if(minheapRideLocation==null) return;
        int existingTripDuration=minheapRideLocation.tripDuration;
        if(newTripDuration<=existingTripDuration) 
        {   
            rbtRideLocation.tripDuration=newTripDuration;
            minheapRideLocation.tripDuration=newTripDuration;
        }
        else if(existingTripDuration<newTripDuration && newTripDuration<=(2*existingTripDuration))
        {   
            
            CancelRide(rideNumber);
            Insert(rideNumber, minheapRideLocation.rideCost+10, newTripDuration);
        }
        else if(newTripDuration>(2*existingTripDuration))
        {   
            
            if(rbtRideLocation!=null)
                CancelRide(rbtRideLocation.rideNumber);

            // CancelRide(minheapRideLocation.rideNumber);
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
            System.out.println("EXCEPTION HAS OCCURED");
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


        // AUTOMATED TASK
        // String fileName=args[0];
        // List<String> methodList=new ArrayList<>();
        // methodList=processInputFile(methodList,fileName);
        
        // for(String str:methodList)
        // {   
        //     String functionName=str.substring(0, str.indexOf("("));
        //     String functionParameters=str.substring(str.indexOf("(")+1,str.indexOf(")"));
        //     String[] parameterList=functionParameters.split(",");
        //     int paramLen=parameterList.length;
        //     int param1=-1,param2=-1,param3=-1;
        //     if(paramLen==1) 
        //     {   
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
        //         Insert(param1, param2, param3);
        //     }
        //     else if(functionName.equals("GetNextRide"))
        //     {
        //         GetNextRide();
        //     }
        //     else if(functionName.equals("Print"))
        //     {
        //         if(paramLen==1) Print(param1);
        //         else Print(param1, param2);

        //     }
        //     else if(functionName.equals("UpdateTrip"))
        //     {
        //         UpdateTrip(param1, param2);
        //     }
        //     else if(functionName.equals("CancelRide"))
        //     {
        //         CancelRide(param1);
        //     }

        // }

        


        // MANUAL INSERT TEST CASE 1
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
        // UpdateTrip(9, 88);
        // // // for(int i=0;i<MinHeap.minHeapList.size();i++)
        // // // {
        // // //     System.out.println(MinHeap.minHeapList.get(i).rideNumber);
        // // // }
        // // // printElements();
        // GetNextRide();
        // Print(9);
        // Insert(20,49,59); 
        // Insert(62,7,10);
        // CancelRide(20);
        // Insert(25, 49, 46);
        // UpdateTrip(62, 15);
        // GetNextRide();
        // Print(1, 100);
        // printElements();
        // Insert(53, 28, 19);
        // Print(1, 100);
        // Print(1);
        // Print(-1);
        
        try
        {   
            Files.write(Paths.get("output.txt"), sb.toString().getBytes());

        }
        catch(Exception e)
        {
            System.out.println("Exception");
        }
        



        // MANUAL INSERT TEST CASE 2
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
        // printElements();
        // //PROGRAM TERMINATES HERE
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
            Files.write(Paths.get("output.txt"), sb.toString().getBytes());

        }
        catch(Exception e)
        {
            System.out.println("Exception");
        }


    }
}



////////////////////////////////////////GATOR TAXI////////////////////////////////////////