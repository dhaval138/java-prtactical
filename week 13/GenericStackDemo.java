
class MyStack<T> {
    private Object[] items;
    private int top;

    MyStack(int size) {
        items = new Object[size];
        top = -1;
    }

    public void push(T value) {
        if (top == items.length - 1) {
            System.out.println("Stack is full");
            return;
        }

        items[++top] = value;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return null;
        }

        T value = (T) items[top];
        items[top] = null;
        top--;
        return value;
    }
}

public class GenericStackDemo {
    public static void main(String[] args) {
        MyStack<Integer> numbers = new MyStack<>(5);
        numbers.push(10);
        numbers.push(20);

        System.out.println(numbers.pop());
        System.out.println(numbers.pop());
        System.out.println(numbers.pop());

        MyStack<String> names = new MyStack<>(5);
        names.push("Amit");
        names.push("Rahul");

        System.out.println(names.pop());
        System.out.println(names.pop());
    }
}