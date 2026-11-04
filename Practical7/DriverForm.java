import java.util.ArrayList;

public class DriverForm {
    public static void main(String[] args) {
        SignupForm form = new SignupForm(
            "",
            "pratham@example.com",
            "pratham123456"
        );

        ArrayList<String> errors = FormValidator.validate(form);

        if (errors.isEmpty()) {
            System.out.println("Form is valid");
        } else {
            System.out.println("Validation Errors:");

            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
}