
interface Urgent {
}

// Functional interface
interface Notifier {
    void send(String message);
}

// SMS sender marked as urgent
class SmsNotifier implements Notifier, Urgent {
    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}

public class BroadCast {
    public static void main(String a[]) {

        // Create email and SMS senders using lambdas
        Notifier email = message ->
                System.out.println("Email: " + message);

        Notifier sms = new SmsNotifier();

        Notifier[] notifiers = {
            email,
            sms
        };

        // Broadcast message to all senders
        for (Notifier n : notifiers) {
            n.send("Server Down");

            // Send message twice to urgent senders
            if (n instanceof Urgent) {
                n.send("Server Down");
            }
        }
    }
}
