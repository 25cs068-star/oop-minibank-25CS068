
interface Switchable {
    void on();
    void off();
    boolean isOn();

    // Toggle device state
    default void toggle() {
        if (isOn()) {
            off();
        } else {
            on();
        }
    }
}

// Functional interface to check switching permission
interface SwitchOnRule {
    boolean maySwitchOn(Switchable device, int hour);
}

class Light implements Switchable {
    boolean state = false;

    public void on() {
        state = true;
        System.out.println("Light ON");
    }

    public void off() {
        state = false;
        System.out.println("Light OFF");
    }

    public boolean isOn() {
        return state;
    }
}

class Fan implements Switchable {
    boolean state = false;

    public void on() {
        state = true;
        System.out.println("Fan ON");
    }

    public void off() {
        state = false;
        System.out.println("Fan OFF");
    }

    public boolean isOn() {
        return state;
    }
}

public class RemoteControle {
    public static void main(String a[]) {

        // Store different devices in one array
        Switchable[] devices = {
            new Light(),
            new Fan()
        };

        // Toggle every device using a loop
        for (Switchable d : devices) {
            d.toggle();
        }

        // Implement functional interface using anonymous class
        SwitchOnRule rule = new SwitchOnRule() {
            public boolean maySwitchOn(Switchable device, int hour) {
                return hour >= 6 && hour <= 22;
            }
        };

        if (rule.maySwitchOn(devices[0], 18)) {
            devices[0].on();
        } else {
            System.out.println("Light cannot turn ON at this time.");
        }

        // Implement the same rule using a lambda expression
        SwitchOnRule lambdaRule = (device, hour) ->
                hour >= 6 && hour <= 22;

        if (lambdaRule.maySwitchOn(devices[1], 20)) {
            devices[1].on();
        } else {
            System.out.println("Fan cannot turn ON at this time.");
        }
    }
}
