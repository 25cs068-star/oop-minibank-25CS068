
import java.lang.annotation.*;
import java.lang.reflect.*;

// Create custom Run annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

class MyTests {

    @Run
    public void testOne() {
        System.out.println("Test One Passed");
    }

    @Run
    public void testTwo() {
        System.out.println("Test Two Passed");
    }

    public void normalMethod() {
        System.out.println("Normal Method");
    }

    @Run
    public void testThree() {
        System.out.println("Test Three Passed");
    }
}

public class MiniTestRunner {

    public static void main(String[] args) {

        MyTests obj = new MyTests();
        int count = 0;

        // Find and invoke only methods marked with @Run
        Method[] methods = MyTests.class.getDeclaredMethods();

        for (Method method : methods) {

            if (method.isAnnotationPresent(Run.class)
                    && method.getParameterCount() == 0) {

                try {
                    method.invoke(obj);
                    count++;
                } catch (Exception e) {
                    System.out.println("Test failed");
                }
            }
        }

        System.out.println("Total tests executed: " + count);
    }
}
