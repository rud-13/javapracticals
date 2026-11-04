public class DriverColumn {
    public static void main(String[] args) {
        String[] headers = {"name", "pincode"};
        String[] data = {"Pratham", "390001"};

        UserRecord user = new UserRecord();

        ColumnMapper.populate(user, headers, data);

        System.out.println("\nUser Details:");
        System.out.println(user);
    }
}