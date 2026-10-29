public class MyResource implements AutoCloseable {

    public MyResource() {
         System.out.println("25AIMML039");
        System.out.println("Resource opened");
    }

    public void useResource() {
        System.out.println("Using resource");
    }

    @Override
    public void close() {
        System.out.println("Resource closed");
    }

    public static void main(String[] args) {

        try (MyResource r = new MyResource()) {

            r.useResource();

        } catch (Exception e) {

            System.out.println("Original error: " + e.getMessage());
        }
    }
}