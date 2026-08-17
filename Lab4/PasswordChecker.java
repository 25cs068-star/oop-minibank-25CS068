import java.util.Scanner;

class PasswordCheckerDemo {
    public static boolean hasLength(String pw) {
        return (pw.length() >= 8);

    }

    public static boolean hasUpperCase(String pw) {
        return (pw.matches(".*[A-Z].*"));
    }

    public static boolean hasDigit(String pw) {
        return (pw.matches(".*[0-9].*"));
    }

    public static boolean hasspcialCharacter(String pw) {
        return (pw.matches(".*[@#*&].*"));
    }

    public static String getStrength(String pw)
    {
        int count=0;
        if(hasLength(pw))
            count++;
        if(hasUpperCase(pw))
            count++;
        
        if(hasDigit(pw))
            count++;
        
        if(hasspcialCharacter(pw))
            count++;

        String strength=switch(count)
        {
            case 0,1->"Week";
            case 2,3->"Medium";
            case 4->"Strong";
            default->"Invalide";
           
        };
         return strength;
    }
}

public class PasswordChecker
{

    public static void main(String a[])
    {
        System.out.println("Please enter the Password:");
        Scanner sc=new Scanner(System.in);
        String password=sc.next();
        System.out.println(PasswordCheckerDemo.getStrength(password));

    }

}
