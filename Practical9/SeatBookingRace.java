class TicketBookingSystem {
    private int seatsLeft = 5;

    public synchronized boolean bookSeat(String passenger) {
        if (seatsLeft > 0) {
            try { Thread.sleep(10); } catch (InterruptedException ignored) {}
            seatsLeft--;
            System.out.println("Seat successfully booked for: " + passenger + " | Remaining: " + seatsLeft);
            return true;
        } else {
            System.out.println("Sold out! Booking rejected for: " + passenger);
            return false;
        }
    }
}

public class SeatBookingRace {
    public static void main(String[] args) throws InterruptedException {
        TicketBookingSystem system = new TicketBookingSystem();
        Thread[] buyers = new Thread[10];

        for (int i = 0; i < 10; i++) {
            String name = "Passenger-" + (i + 1);
            buyers[i] = new Thread(() -> system.bookSeat(name));
            buyers[i].start();
        }

        for (Thread t : buyers) t.join();
        System.out.println("All booking requests processed.");
    }
}