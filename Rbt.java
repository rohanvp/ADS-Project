import java.util.*;

class RbtRide
{
    int rideNumber;
    int rideCost;
    int tripDuration;
    // COLOR 0->BLACK,1->RED
    int color;
    RbtRide gpp;
    RbtRide p;
    RbtRide left;
    RbtRide right;

    RbtRide(){}

    RbtRide(int rideNumber,int rideCost,int tripDuration)
    {
        this.rideNumber=rideNumber;
        this.rideCost=rideCost;
        this.tripDuration=tripDuration;
        this.color=1;
        this.gpp=null;
        this.p=null;
        this.right=null;
        this.left=null;
    }
}


public class Rbt {

    private static List<RbtRide> rbtree=new ArrayList<>();
    private static int heapSize=0;
    private static RbtRide root;


    private static void insertRide(int rideNumber,int rideCost,int tripDuration)
    {
        if(heapSize==0)
        {
            RbtRide newRbtRide=new RbtRide(rideNumber,rideCost,tripDuration);
            newRbtRide.color=0;
            heapSize++;
            root=newRbtRide;
            
        }
        else
        {
            RbtRide newRbtRide=new RbtRide(rideNumber,rideCost,tripDuration);
            binarySearchTreeInsert(newRbtRide);
        }   
    }

    private static void binarySearchTreeInsert(RbtRide element)
    {
        RbtRide curr=root;
        while(curr!=null)
        {
            if(element.rideNumber>curr.rideNumber)
            {
                if(curr.right==null)
                {
                    curr.right=element;
                    return;
                }
                else
                {
                    curr=curr.right;
                }
            }
            else
            {
                if(curr.left==null)
                {
                    curr.left=element;
                    return;
                }
                else
                {
                    curr=curr.left;
                }
            }
        }
        return;
    }

    private static void insertDataHelper()
    {

    }

    private static void printElements(RbtRide curr)
    {
        if(curr==null) return;
        printElements(curr.left);
        System.out.println(curr.rideNumber+" "+curr.rideCost+" "+curr.tripDuration);
        printElements(curr.right);
    }


    public static void main(String[] args) {

        insertRide(25, 98, 46);
        insertRide(42,17,89);
        insertRide(9,76,31);
        insertRide(53,97,22); 
        insertRide(96,28,82);
        insertRide(73,28,56);
        printElements(root);
        
    }
    
}
