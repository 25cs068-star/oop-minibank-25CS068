
class Stack<T> {

    private T[] stack;
    private int top;

    @SuppressWarnings("unchecked")
    Stack(int size) {
        stack = (T[]) new Object[size];
        top = -1;
    }

    // Add an element to the stack
    public void push(T value) {
        if (top == stack.length - 1) {
            System.out.println("Stack is full!");
            return;
        }

        stack[++top] = value;
        System.out.println(value + " pushed into stack");
    }

    // Remove and return the top element
    public T pop() {
        if (top == -1) {
            System.out.println("Stack is empty! Cannot pop.");
            return null;
        }

        T value = stack[top];
        stack[top] = null;
        top--;

        return value;
    }

    // Display stack elements
    public void display() {
        if (top == -1) {
            System.out.println("Stack is empty!");
            return;
        }

        System.out.println("Stack elements:");

        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }
}

public class GenericStack {

    public static void main(String[] args) {

        // Integer type stack
        Stack<Integer> intStack = new Stack<>(5);

        System.out.println("Integer Stack:");
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);
        intStack.display();

        System.out.println("Popped: " + intStack.pop());
        intStack.display();

        // String type stack
        Stack<String> stringStack = new Stack<>(5);

        System.out.println("\nString Stack:");
        stringStack.push("Java");
        stringStack.push("Python");
        stringStack.push("C++");
        stringStack.display();

        System.out.println("Popped: " + stringStack.pop());
        stringStack.display();

        // Handling empty stack
        System.out.println("\nEmpty Stack Test:");
        Stack<Integer> emptyStack = new Stack<>(3);
        System.out.println("Popped: " + emptyStack.pop());
    }
}
