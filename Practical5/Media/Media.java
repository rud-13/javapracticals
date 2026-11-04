public abstract class Media {
    protected String title;
    protected int daysLate;

    public Media(String title, int daysLate) {
        this.title = title;
        this.daysLate = daysLate;
    }

    public abstract double lateFee();
}