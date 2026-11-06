
class BankAccount {
    String name;

    BankAccount(String name) {
        this.name = name;
    }
}

class Transfer {

    // Lock accounts in opposite order to demonstrate deadlock
    public void transferWithoutFix(BankAccount a, BankAccount b) {

        synchronized (a) {
            System.out.println(Thread.currentThread().getName()
                    + " locked " + a.name);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            synchronized (b) {
                System.out.println(Thread.currentThread().getName()
                        + " locked " + b.name);
                System.out.println("Transfer completed");
            }
        }
    }

    // Lock accounts in the same order to prevent deadlock
    public void transferWithFix(BankAccount a, BankAccount b) {

        BankAccount first;
        BankAccount second;

        if (a.name.compareTo(b.name) < 0) {
            first = a;
            second = b;
        } else {
            first = b;
            second = a;
        }

        synchronized (first) {
            synchronized (second) {
                System.out.println(Thread.currentThread().getName()
                        + " locked " + first.name + " and " + second.name);

                System.out.println("Transfer completed safely");
            }
        }
    }
}

public class DeadlockDemo {

    public static void main(String[] args) {

        BankAccount accountA = new BankAccount("AccountA");
        BankAccount accountB = new BankAccount("AccountB");

        Transfer t = new Transfer();

        System.out.println("Deadlock Demonstration:");

        // Two threads lock accounts in opposite order
        Thread t1 = new Thread(() -> {
            t.transferWithoutFix(accountA, accountB);
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            t.transferWithoutFix(accountB, accountA);
        }, "Thread-2");

        // Allow deadlocked threads to stop blocking JVM exit
        t1.setDaemon(true);
        t2.setDaemon(true);

        t1.start();
        t2.start();

        try {
            t1.join(500);
            t2.join(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (t1.isAlive() && t2.isAlive()) {
            System.out.println("Deadlock detected!");
        } else {
            System.out.println("Deadlock did not occur this time.");
        }

        System.out.println("\nDeadlock Prevention:");

        // Create new accounts for prevention
        BankAccount safeAccountA = new BankAccount("AccountA");
        BankAccount safeAccountB = new BankAccount("AccountB");

        // Both threads follow the same lock order
        Thread t3 = new Thread(() -> {
            t.transferWithFix(safeAccountA, safeAccountB);
        }, "Thread-3");

        Thread t4 = new Thread(() -> {
            t.transferWithFix(safeAccountB, safeAccountA);
        }, "Thread-4");

        t3.start();
        t4.start();

        // Wait for both threads to complete
        try {
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Both transfers completed safely.");
    }
}
