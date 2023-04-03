import java.util.*;

class Ride
{
    Ride left;
    Ride right;
    int rideNumber;
    int rideCost;
    int tripDuration;

    Ride(){}

    Ride(int rideNumber,int rideCost,int tripDuration)
    {
        this.rideNumber=rideNumber;
        this.rideCost=rideCost;
        this.tripDuration=tripDuration;
    }
}

public class MinHeap {

    private static List<Ride> minHeap=new ArrayList<>();
    private static int heapSize=0;
    private static int currIndex=-1;


    private static void insertRide(int rideNumber,int rideCost,int tripDuration)
    {
        Ride newRide=new Ride(rideNumber, rideCost, tripDuration);
        heapSize++;
        currIndex=heapSize-1;
        minHeap.add(newRide);
        while(currIndex!=0 && minHeap.get((currIndex-1)/2).rideCost>=minHeap.get(currIndex).rideCost)
        {   
            int parent=(currIndex-1)/2;
            Ride temp=minHeap.get(parent);
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
        insertRide(73,28,25);
        printElements();
        
    }


    
}
