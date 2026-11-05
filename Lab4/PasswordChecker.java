import java.util.Scanner;

class PasswordCheckerDemo
{
    // Check whether password length is at least 8
    public static boolean hasLength(String pw)
    {
        return pw.length() >= 8;
    }

    // Check whether password contains an uppercase letter
    public static boolean hasUpperCase(String pw)
    {
        return pw.matches(".*[A-Z].*");
    }

    // Check whether password contains a digit
    public static boolean hasDigit(String pw)
    {
        return pw.matches(".*[0-9].*");
    }

    // Check whether password contains a special character
    public static boolean hasSpecialCharacter(String pw)
    {
        return pw.matches(".*[^a-zA-Z0-9].*");
    }

    // Calculate password strength based on passed rules
    public static String strength(String pw)
    {
        int count = 0;

        if(hasLength(pw))
            count++;

        if(hasUpperCase(pw))
            count++;

        if(hasDigit(pw))
            count++;

        if(hasSpecialCharacter(pw))
            count++;

        String strength = switch(count)
        {
            case 0, 1 -> "Weak";
            case 2, 3 -> "Medium";
            case 4 -> "Strong";
            default -> "Invalid";
        };

        return strength;
    }
}

public class PasswordChecker
{
    public static void main(String a[])
    {
        Scanner sc = new Scanner(System.in);

        // Enter password at runtime
        System.out.println("Please enter the Password:");
        String password = sc.nextLine();

        // Display which rules passed
        System.out.println("Length >= 8: " +
            PasswordCheckerDemo.hasLength(password));

        System.out.println("Uppercase: " +
            PasswordCheckerDemo.hasUpperCase(password));

        System.out.println("Digit: " +
            PasswordCheckerDemo.hasDigit(password));

        System.out.println("Special Character: " +
            PasswordCheckerDemo.hasSpecialCharacter(password));

        // Display final password strength
        System.out.println("Password Strength: " +
            PasswordCheckerDemo.strength(password));

        sc.close();
    }
}