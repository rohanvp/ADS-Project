import java.util.*;

class MinHeapRide
{
    int rideNumber;
    int rideCost;
    int tripDuration;
    RbtRide newRbtRide;

    MinHeapRide(){}

    MinHeapRide(int rideNumber,int rideCost,int tripDuration)
    {
        this.rideNumber=rideNumber;
        this.rideCost=rideCost;
        this.tripDuration=tripDuration;
        this.newRbtRide=null;
    }
}

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

    RbtRide(){}

    RbtRide(int rideNumber,int rideCost,int tripDuration)
    {
        this.rideNumber=rideNumber;
        this.rideCost=rideCost;
        this.tripDuration=tripDuration;

    }
}

public class MinHeap {

    private static List<MinHeapRide> minHeap=new ArrayList<>();
    private static int heapSize=0;
    private static int currIndex=-1;


    // MIN HEAP IMPLEMENTATION
    private static void insertRide(int rideNumber,int rideCost,int tripDuration)
    {
        MinHeapRide newRide=new MinHeapRide(rideNumber, rideCost, tripDuration);
        heapSize++;
        currIndex=heapSize-1;
        minHeap.add(newRide);
        while(currIndex!=0 && minHeap.get((currIndex-1)/2).rideCost>=minHeap.get(currIndex).rideCost)
        {   
            int parent=(currIndex-1)/2;
            MinHeapRide temp=minHeap.get(parent);
            if(minHeap.get((currIndex-1)/2).rideCost==minHeap.get(currIndex).rideCost)
            {   
                if(minHeap.get((currIndex-1)/2).tripDuration>minHeap.get(currIndex).tripDuration)
                {
                    minHeap.set(parent,minHeap.get(currIndex));
                    minHeap.set(currIndex, temp);
                    currIndex=parent;
                }
            }
            else
            {
                minHeap.set(parent,minHeap.get(currIndex));
                minHeap.set(currIndex, temp);
                currIndex=parent;
            }
            

        }

    }

    private static void deleteRide(int rideNumber)
    {
        
    }

    private static void getNextRide()
    {   
        // THIS FUNCTION ALSO IMPLEMENTS DELETION
        if(minHeap.size()==0) System.out.println("No Active Rides");
        currIndex=--heapSize;
        minHeap.set(0,minHeap.get(currIndex));
        minHeapify(0);
    }


    private static void minHeapify(int ci)
    {   
        MinHeapRide smallest=minHeap.get(ci);
        int smallestIndex=0;
        if((ci*2+1)<heapSize && smallest.rideCost>=minHeap.get(ci*2+1).rideCost)
        {
            if(smallest.rideCost==minHeap.get(ci*2+1).rideCost)
            {   
                if(smallest.tripDuration>minHeap.get(ci*2+1).tripDuration)
                {
                    minHeap.set(ci,minHeap.get(ci*2+1));
                    minHeap.set(ci*2+1, smallest);
                    smallest=minHeap.get(ci*2+1);
                    smallestIndex=ci*2+1;
                }
            }
            else
            {
                minHeap.set(ci,minHeap.get(ci*2+1));
                minHeap.set(ci*2+1, smallest);
            }

        }
        if((ci*2+2)<heapSize)
        {
            if(smallest.rideCost==minHeap.get(ci*2+2).rideCost)
            {   
                if(smallest.tripDuration>minHeap.get(ci*2+2).tripDuration)
                {
                    minHeap.set(ci,minHeap.get(ci*2+2));
                    minHeap.set(ci*2+2, smallest);
                    smallest=minHeap.get(ci*2+2);
                    smallestIndex=ci*2+2;
                }
            }
            else
            {
                minHeap.set(ci,minHeap.get(ci*2+2));
                minHeap.set(ci*2+2, smallest);
            }
        }
        if(smallestIndex!=ci)
            minHeapify(smallestIndex);

        
    }
    // MIN HEAP IMPLEMENTATION

    private static void printElements()
    {
        for(int i=0;i<minHeap.size();i++)
        {
            System.out.println(minHeap.get(i).rideNumber+" "+minHeap.get(i).rideCost+" "+minHeap.get(i).tripDuration);
        }
    }


    public static void main(String[] args) {

        insertRide(25, 98, 46);
        insertRide(42,17,89);
        insertRide(9,76,31);
        insertRide(53,97,22); 
        insertRide(96,28,82);
        insertRide(73,28,56);
        // printElements();
        getNextRide();
        printElements();
        
    }


    
}

