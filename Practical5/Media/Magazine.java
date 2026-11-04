public class Magazine extends Media {
    public Magazine(String title, int daysLate) {
        super(title, daysLate);
    }

    public double lateFee() {
        return daysLate * 1;
    }
}