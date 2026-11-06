
class MessageBuffer {
    private int value;
    private boolean available = false;

    // Producer waits if buffer is already full
    public synchronized void produce(int number) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        value = number;
        available = true;

        System.out.println("Produced: " + number);

        notify();
    }

    // Consumer waits if buffer is empty
    public synchronized void consume() {
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        System.out.println("Consumed: " + value);

        available = false;

        notify();
    }
}

public class ProducerConsumer {

    public static void main(String[] args) {

        MessageBuffer buffer = new MessageBuffer();

        // Producer creates 10 numbers
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                buffer.produce(i);
            }
        });

        // Consumer consumes 10 numbers
        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                buffer.consume();
            }
        });

        producer.start();
        consumer.start();

        // Wait for both threads to finish
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("All items produced and consumed.");
    }
}
