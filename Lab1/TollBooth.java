
import java.util.*;

record Vehicle(String number, String type) {}

class TollBooth
{
    public static void main(String a[])
    {
        int totalToll = 0;
        int bike = 0;
        int car = 0;
        int truck = 0;

        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println("Enter Vehicle Number (or done):");
            String number = sc.next();

            // Stop the loop when user enters done
            if(number.equalsIgnoreCase("done"))
                break;

            System.out.println("Enter Vehicle Type:");
            String type = sc.next();

            // Create vehicle record
            Vehicle v = new Vehicle(number, type);

            // Calculate toll according to vehicle type
            int toll = switch(v.type().toLowerCase())
            {
                case "bike" -> {
                    bike++;
                    yield 20;
                }

                case "car" -> {
                    car++;
                    yield 50;
                }

                case "truck" -> {
                    truck++;
                    yield 150;
                }

                default -> 0;
            };

            totalToll = totalToll + toll;

            System.out.println(v.type() + " passed with Toll: " + toll);
        }

        System.out.println("Total Bike Passed: " + bike);
        System.out.println("Total Car Passed: " + car);
        System.out.println("Total Truck Passed: " + truck);

        System.out.println("Total toll: " + totalToll);

        // Find the vehicle type with highest count
        if(bike >= car && bike >= truck)
        {
            System.out.println("Most frequent: bike");
        }
        else if(car >= bike && car >= truck)
        {
            System.out.println("Most frequent: car");
        }
        else
        {
            System.out.println("Most frequent: truck");
        }

        sc.close();
    }
}
