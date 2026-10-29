import java.io.*;

// 1. Define a small Serializable class
class Student implements Serializable {
    // Best practice to define a explicit serialVersionUID
    private static final long serialVersionUID = 1L; 
    
    private String name;
    private int age;
    // This field will NOT be saved
    private transient String temporarySessionToken; 

    public Student(String name, int age, String temporarySessionToken) {
        this.name = name;
        this.age = age;
        this.temporarySessionToken = temporarySessionToken;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + 
               ", temporarySessionToken='" + temporarySessionToken + "'}";
    }
}

public class SerializationDemo {
    public static void main(String[] args) {
        String filepath = "students.ser";
   
        Student[] originalStudents = {
            new Student("Alice", 20, "TOKEN_XYZ_123"),
            new Student("Bob", 22, "TOKEN_ABC_789")
        };

        System.out.println("--- Original Objects ---");
        for (Student s : originalStudents) {
            System.out.println(s);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(originalStudents);
            System.out.println("\nSuccessfully saved array to " + filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }

       
        Student[] restoredStudents = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
            restoredStudents = (Student[]) ois.readObject();
            System.out.println("Successfully read array back from file.\n");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    
        System.out.println("--- Restored Objects ---");
        if (restoredStudents != null) {
            for (Student s : restoredStudents) {
                System.out.println(s);
            }
        }
        
        System.out.println("\nVerification: Notice that 'temporarySessionToken' is now 'null' because it was marked transient!");
    }
}