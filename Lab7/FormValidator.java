
import java.util.*;

class SignupForm {
    String name;
    String email;
    String password;

    SignupForm(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

public class FormValidator {

    // Check form fields and return validation errors
    static ArrayList<String> validate(SignupForm form) {
        ArrayList<String> errors = new ArrayList<>();

        if (form.name == null || form.name.trim().isEmpty()) {
            errors.add("Name must not be blank");
        }

        if (form.email == null || form.email.trim().isEmpty()) {
            errors.add("Email must not be blank");
        }

        if (form.password == null || form.password.trim().isEmpty()) {
            errors.add("Password must not be blank");
        }

        if (form.name != null && form.name.length() > 20) {
            errors.add("Name must not exceed 20 characters");
        }

        if (form.email != null && form.email.length() > 30) {
            errors.add("Email must not exceed 30 characters");
        }

        if (form.password != null && form.password.length() > 10) {
            errors.add("Password must not exceed 10 characters");
        }

        return errors;
    }

    public static void main(String[] args) {
        SignupForm form = new SignupForm(
            "Krisha",
            "",
            "123456789012"
        );

        ArrayList<String> errors = validate(form);

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
