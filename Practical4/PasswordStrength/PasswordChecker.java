public class PasswordChecker {

    public static String strength(String pw) {
        int count = 0;

        boolean length = pw.length() >= 8;
        boolean uppercase = pw.matches(".*[A-Z].*");
        boolean digit = pw.matches(".*[0-9].*");
        boolean special = pw.matches(".*[^a-zA-Z0-9].*");

        System.out.println("Password: " + pw);
        System.out.println("Length >= 8: " + length);
        System.out.println("Uppercase: " + uppercase);
        System.out.println("Digit: " + digit);
        System.out.println("Special Character: " + special);

        if (length) count++;
        if (uppercase) count++;
        if (digit) count++;
        if (special) count++;

        if (count <= 1)
            return "Weak";
        else if (count <= 3)
            return "Medium";
        else
            return "Strong";
    }
}