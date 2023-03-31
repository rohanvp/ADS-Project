import java.util.*;;


public class GatorTaxi {

    private int rideNumber;
    private int rideCost;
    private int tripDuration;
    

    private static void printRideNumber(int rideNumber) {

        // prints the triplet (rideNumber, rideCost, tripDuration).
        
        
    }
    private static void printRideNumber(int rideNumber1,int rideNumber2) {

        // prints all triplets (rx, rideCost, tripDuration) for which rideNumber1 <= rx <= rideNumber2.
        
    }

    private static void insertData(int rideNumber,int rideCost,int tripDuration) {
        
        // where rideNumber differs from existing ride numbers.
        


    }

    private static void getNextRide() {
        
        //  When this function is invoked, the ride with the lowest rideCost (ties are broken by selecting the ride with the lowest tripDuration) is output.
        //  This ride is then deleted from the data structure.
    }

    private static void cancelRide() {
        // deletes the triplet (rideNumber, rideCost, tripDuration) from the data
        // structures, can be ignored if an entry for rideNumber doesn’t exist.
        
    }

    private static void updateTrip(int rideNumber,int newTripDuration) {
        // a) if the new_tripDuration <= existing tripDuration, there would be no action needed.
        // b) if the existing_tripDuration < new_tripDuration <= 2*(existing tripDuration), the driver will
        //    cancel the existing ride and a new ride request would be created with a penalty of 10 on existing rideCost . 
        // We update the entry in the data structure with (rideNumber, rideCost+10, new_tripDuration)
        // c) if the new_tripDuration > 2*(existing tripDuration), the ride would be automatically declined and the ride would be removed from the data structure.
        
    }



    public static void main(String[] args) {
        
        printRideNumber(10);
        printRideNumber(10,20);
    }


}