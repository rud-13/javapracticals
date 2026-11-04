public class UserRecord {
    @Column(name = "name")
    String name;

    @Column(name = "city")
    String city;

    @Column(name = "pincode")
    String pincode;

    public String toString() {
        return "Name: " + name +
               "\nCity: " + city +
               "\nPincode: " + pincode;
    }
}