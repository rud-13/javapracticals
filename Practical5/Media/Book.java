public class Book extends Media {
    public Book(String title, int daysLate) {
        super(title, daysLate);
    }

    public double lateFee() {
        return daysLate * 2;
    }
}