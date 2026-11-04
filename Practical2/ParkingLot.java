public class ParkingLot {
    private int twoWheelers;
    private int fourWheelers;

    private final int twoCap;
    private final int fourCap;

    private static long revenue = 0;

    public ParkingLot(int twoCap, int fourCap) {
        this.twoCap = twoCap;
        this.fourCap = fourCap;
        this.twoWheelers = 0;
        this.fourWheelers = 0;
    }

    public void park(String type) {
        if (type.equalsIgnoreCase("two")) {
            if (twoWheelers < twoCap) {
                twoWheelers++;
                revenue += 20;
                System.out.println("Two-wheeler parked successfully.");
            } else {
                System.out.println("Two-wheeler parking Full.");
            }
        } else if (type.equalsIgnoreCase("four")) {
            if (fourWheelers < fourCap) {
                fourWheelers++;
                revenue += 40;
                System.out.println("Four-wheeler parked successfully.");
            } else {
                System.out.println("Four-wheeler parking Full.");
            }
        } else {
            System.out.println("Invalid vehicle type.");
        }
    }

    public void leave(String type) {
        if (type.equalsIgnoreCase("two")) {
            if (twoWheelers > 0) {
                twoWheelers--;
                System.out.println("Two-wheeler left.");
            } else {
                System.out.println("No two-wheelers available.");
            }
        } else if (type.equalsIgnoreCase("four")) {
            if (fourWheelers > 0) {
                fourWheelers--;
                System.out.println("Four-wheeler left.");
            } else {
                System.out.println("No four-wheelers available.");
            }
        } else {
            System.out.println("Invalid vehicle type.");
        }
    }

    public void display() {
        System.out.println("\nParking Lot Status");
        System.out.println("Two-wheelers: " + twoWheelers + "/" + twoCap);
        System.out.println("Four-wheelers: " + fourWheelers + "/" + fourCap);
        System.out.println("Total Revenue: Rs. " + revenue);
    }

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(2, 2);

        lot.park("two");
        lot.park("two");
        lot.park("two");

        lot.park("four");
        lot.park("four");

        lot.display();

        lot.leave("two");
        lot.park("two");

        lot.leave("four");

        lot.display();
    }
}