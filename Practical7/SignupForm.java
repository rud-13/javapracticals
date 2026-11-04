public class SignupForm {
    @NotBlank
    @MaxLength(20)
    String name;

    @NotBlank
    @MaxLength(30)
    String email;

    @NotBlank
    @MaxLength(10)
    String username;

    public SignupForm(String name, String email, String username) {
        this.name = name;
        this.email = email;
        this.username = username;
    }
}