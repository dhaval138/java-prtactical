
class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

class Pair<A, B> {
    A first;
    B second;

    Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    void display() {
        System.out.println(first + " - " + second);
    }
}

public class GenericDemo {
    public static <T> T getFirst(T[] array) {
        return array[0];
    }

    public static void main(String[] args) {
        Box<Integer> b1 = new Box<>();
        b1.set(100);
        System.out.println("Box value: " + b1.get());

        Box<String> b2 = new Box<>();
        b2.set("Java");
        System.out.println("Box value: " + b2.get());

        Pair<String, Integer> p1 = new Pair<>("Rahul", 20);
        p1.display();

        String[] names = {"Amit", "Neha", "Rahul"};
        System.out.println("First name: " + getFirst(names));

        Integer[] numbers = {10, 20, 30};
        System.out.println("First number: " + getFirst(numbers));
    }
}