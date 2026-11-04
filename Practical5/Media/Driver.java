public class Driver {
    public static void main(String[] args) {
        Media[] items = {
            new Book("Java Programming", 3),
            new DVD("Avengers", 2),
            new Magazine("Technology Today", 5)
        };

        double total = 0;

        for (Media m : items) {
            double fee = m.lateFee();

            System.out.println("Title: " + m.title);
            System.out.println("Late Fee: Rs. " + fee);

            total += fee;
        }

        System.out.println("Total Late Fees: Rs. " + total);
    }
}