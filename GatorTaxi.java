import java.nio.file.Path;
import java.util.*;
import java.io.*;
import java.lang.reflect.Method;

class Ride
{
    Ride left;
    Ride right;
    int rideNumber;
    int rideCost;
    int tripDuration;

    Ride(int rideNumber,int rideCost,int tripDuration)
    {
        this.rideNumber=rideNumber;
        this.rideCost=rideCost;
        this.tripDuration=tripDuration;
    }
}

// class MinHeap
// {   
//     MinHeap(){}

//     public static void hello(int x)
//     {    
//         System.out.println(x);
//     }
// }


public class GatorTaxi {

    // private int rideNumber;
    // private int rideCost;
    // private int tripDuration;

    static List<Ride> minHeap=new ArrayList<>();
    static int heapSize=0;
    

    private static void printRideNumber(int rideNumber) {

        // prints the triplet (rideNumber, rideCost, tripDuration).
        for(int i=0;i<heapSize;i++)
        {
            if(minHeap.get(i).rideNumber==rideNumber) System.out.println(minHeap.get(i));
        }
        
        
    }
    private static void printRideNumber(int rideNumber1,int rideNumber2) {

        // prints all triplets (rx, rideCost, tripDuration) for which rideNumber1 <= rx <= rideNumber2.
        // System.out.println("ride no:"+rideNumber1+" "+rideNumber2);
        
    }

    private static void insertData(int rideNumber,int rideCost,int tripDuration) {
        
        // where rideNumber differs from existing ride numbers.
        Ride newRide=new Ride(rideNumber, rideCost, tripDuration);
        minHeap.add(newRide);
        heapSize++;


    }

    private static void getNextRide() {
        
        //  When this function is invoked, the ride with the lowest rideCost (ties are broken by selecting the ride with the lowest tripDuration) is output.
        //  This ride is then deleted from the data structure.
        // System.out.println("getNextRide");
    }

    private static void cancelRide() {
        // deletes the triplet (rideNumber, rideCost, tripDuration) from the data
        // structures, can be ignored if an entry for rideNumber doesn’t exist.
        // System.out.println("cancelRide");
        
    }

    private static void updateTrip(int rideNumber,int newTripDuration) {
        // a) if the new_tripDuration <= existing tripDuration, there would be no action needed.
        // b) if the existing_tripDuration < new_tripDuration <= 2*(existing tripDuration), the driver will
        //    cancel the existing ride and a new ride request would be created with a penalty of 10 on existing rideCost . 
        // We update the entry in the data structure with (rideNumber, rideCost+10, new_tripDuration)
        // c) if the new_tripDuration > 2*(existing tripDuration), the ride would be automatically declined and the ride would be removed from the data structure.
        // System.out.println(rideNumber+" "+newTripDuration);
        
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


        String fileName=args[0];
        List<String> methodList=new ArrayList<>();
        methodList=processInputFile(methodList);
        for(String str:methodList)
        {   
            String functionName=str.substring(0, str.indexOf("("));
            String functionParameters=str.substring(str.indexOf("(")+1,str.indexOf(")"));
            String[] parameterList=functionParameters.split(",");
            int paramLen=parameterList.length;
            int param1=-1,param2=-1,param3=-1;
            if(paramLen==1) 
            {   
                // System.out.println(1); 
                
                try
                {
                    param1=Integer.parseInt(parameterList[0]);

                }
                catch(Exception e)
                {

                }

            }
            else if(paramLen==2) 
            {   
                // System.out.print(2);
                param1=Integer.parseInt(parameterList[0]);
                param2=Integer.parseInt(parameterList[1]);
            }
            else if(paramLen==3) 
            {   
                // System.out.println(3); 
                param1=Integer.parseInt(parameterList[0]);
                param2=Integer.parseInt(parameterList[1]);
                param3=Integer.parseInt(parameterList[2]);
            }





            if(functionName.equals("Insert"))
            {
                insertData(param1, param2, param3);
            }
            else if(functionName.equals("GetNextRide"))
            {
                getNextRide();
            }
            else if(functionName.equals("Print"))
            {
                if(paramLen==1) printRideNumber(param1);
                else printRideNumber(param1, param2);

            }
            else if(functionName.equals("UpdateTrip"))
            {
                updateTrip(param1, param2);
            }

        }


    }
}