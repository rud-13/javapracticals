public class Driver {
    public static void main(String[] args) {
        String[] passwords = {
            "abc",
            "Pratham123",
            "Abcd1234!"
        };

        for (String pw : passwords) {
            String result = PasswordChecker.strength(pw);
            System.out.println("Strength: " + result);
            System.out.println();
        }
    }
}