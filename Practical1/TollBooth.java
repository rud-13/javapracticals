import java.util.Scanner;

public class TollBooth {
    record Vehicle(String number, String type) { }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalToll = 0;
        int bikes = 0, cars = 0, trucks = 0;

        while (true) {
            System.out.print("Enter vehicle number (or done): ");
            String number = sc.next();
            if (number.equalsIgnoreCase("done")) break;

            System.out.print("Enter vehicle type (bike/car/truck): ");
            String type = sc.next().toLowerCase();
            Vehicle vehicle = new Vehicle(number, type);

            int toll = switch (vehicle.type()) {
                case "bike" -> 20;
                case "car" -> 50;
                case "truck" -> 150;
                default -> 0;
            };
            totalToll += toll;

            switch (vehicle.type()) {
                case "bike" -> bikes++;
                case "car" -> cars++;
                case "truck" -> trucks++;
                default -> System.out.println("Unknown vehicle type.");
            }
            System.out.println("Toll: " + toll);
        }

        System.out.println("Total toll: " + totalToll);
        String mostFrequent = "bike";
        int highest = bikes;
        if (cars > highest) { mostFrequent = "car"; highest = cars; }
        if (trucks > highest) { mostFrequent = "truck"; highest = trucks; }

        System.out.println(highest == 0 ? "Most frequent: none" :
                "Most frequent: " + mostFrequent);
        sc.close();
    }
}
