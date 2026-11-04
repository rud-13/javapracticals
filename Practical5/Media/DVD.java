public class DVD extends Media {
    public DVD(String title, int daysLate) {
        super(title, daysLate);
    }

    public double lateFee() {
        return daysLate * 5;
    }
}