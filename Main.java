import java.util.*;
import java.io.*;

/*
========================================
INTERFACE
========================================

Why Used?

Common contract define karne ke liye.

Har student ko display() implement karna hi padega.
*/

interface Displayable {

    void display();
}

/*
========================================
CUSTOM EXCEPTION
========================================

Validation fail hone par use karenge.
*/

class StudentException extends Exception {

    public StudentException(String message) {
        super(message);
    }
}

/*
========================================
ABSTRACT CLASS
========================================

Common properties sab students ke liye.

Abstraction achieve karne ke liye.
*/

abstract class Person implements Displayable {

    /*
    Encapsulation

    private variables
    */
    private int id;
    private String name;
    private int age;

    /*
    Constructor
    */
    public Person(int id, String name, int age) {

        this.id = id;
        this.name = name;
        this.age = age;
    }

    /*
    Getters
    */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    /*
    Setters
    */

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /*
    Abstract Method

    Child class must implement
    */

    public abstract void display();
}

/*
========================================
CONCRETE CLASS
========================================

Inheritance

Student extends Person
*/

class Student extends Person {

    private String course;

    public Student(
            int id,
            String name,
            int age,
            String course) {

        super(id, name, age);

        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    /*
    Method Overriding

    Parent abstract method ko
    implement kiya
    */

    @Override
    public void display() {

        System.out.println(
                getId()
                + " | "
                + getName()
                + " | "
                + getAge()
                + " | "
                + course);
    }
}

/*
========================================
MAIN CLASS
========================================
*/

public class Main {

    /*
    Collection Framework

    ArrayList

    Dynamic memory allocation
    */
    static ArrayList<Student> students =
            new ArrayList<>();

    static Scanner sc =
            new Scanner(System.in);

    static final String FILE_NAME =
            "students.txt";

    /*
    ========================================
    LOAD FILE DATA
    ========================================
    */

    public static void loadStudents() {

        File file = new File(FILE_NAME);

        /*
        File exists check
        */

        if (!file.exists()) {
            return;
        }

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(FILE_NAME));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data =
                        line.split(",");

                Student student =
                        new Student(

                                Integer.parseInt(data[0]),
                                data[1],
                                Integer.parseInt(data[2]),
                                data[3]);

                students.add(student);
            }

            br.close();

        }
        catch (Exception e) {

            System.out.println(
                    "Load Error : "
                            + e.getMessage());
        }
    }

    /*
    ========================================
    SAVE FILE
    ========================================
    */

    public static void saveStudents() {

        try {

            FileWriter writer =
                    new FileWriter(FILE_NAME);

            /*
            Overwrite mode

            Entire list rewrite
            */

            for (Student s : students) {

                writer.write(

                        s.getId()
                        + ","

                        + s.getName()
                        + ","

                        + s.getAge()
                        + ","

                        + s.getCourse()

                        + "\n");
            }

            writer.close();
        }
        catch (Exception e) {

            System.out.println(
                    "Save Error : "
                            + e.getMessage());
        }
    }

    /*
    ========================================
    VALIDATION
    ========================================
    */

    public static void validate(

            int id,
            String name,
            int age)

            throws StudentException {

        if (id <= 0) {

            throw new StudentException(
                    "Invalid ID");
        }

        if (name.trim().isEmpty()) {

            throw new StudentException(
                    "Name Cannot Be Empty");
        }

        if (age < 18) {

            throw new StudentException(
                    "Age Must Be 18+");
        }

        /*
        Duplicate ID Validation
        */

        for (Student s : students) {

            if (s.getId() == id) {

                throw new StudentException(
                        "Duplicate ID Found");
            }
        }
    }

    /*
    ========================================
    CREATE
    ========================================
    */

    public static void addStudent() {

        try {

            System.out.print(
                    "Enter ID : ");

            int id =
                    Integer.parseInt(
                            sc.nextLine());

            System.out.print(
                    "Enter Name : ");

            String name =
                    sc.nextLine();

            System.out.print(
                    "Enter Age : ");

            int age =
                    Integer.parseInt(
                            sc.nextLine());

            System.out.print(
                    "Enter Course : ");

            String course =
                    sc.nextLine();

            /*
            Validation
            */

            validate(
                    id,
                    name,
                    age);

            Student student =
                    new Student(
                            id,
                            name,
                            age,
                            course);

            students.add(student);

            saveStudents();

            System.out.println(
                    "Student Added");

        }
        catch (StudentException e) {

            System.out.println(
                    "Validation Error : "
                            + e.getMessage());
        }
        catch (Exception e) {

            System.out.println(
                    "System Error : "
                            + e.getMessage());
        }
        finally {

            System.out.println(
                    "Add Operation Finished");
        }
    }

    /*
    ========================================
    READ
    ========================================
    */

    public static void viewStudents() {

        if (students.isEmpty()) {

            System.out.println(
                    "No Students Found");

            return;
        }

        System.out.println(
                "\nID | NAME | AGE | COURSE");

        /*
        Polymorphism

        Parent reference
        */

        for (Displayable d : students) {

            d.display();
        }
    }

    /*
    ========================================
    UPDATE
    ========================================
    */

    public static void updateStudent() {

        try {

            System.out.print(
                    "Enter Student ID : ");

            int id =
                    Integer.parseInt(
                            sc.nextLine());

            boolean found =
                    false;

            for (Student s : students) {

                if (s.getId() == id) {

                    found = true;

                    System.out.print(
                            "New Name : ");

                    s.setName(
                            sc.nextLine());

                    System.out.print(
                            "New Age : ");

                    s.setAge(
                            Integer.parseInt(
                                    sc.nextLine()));

                    System.out.print(
                            "New Course : ");

                    s.setCourse(
                            sc.nextLine());

                    saveStudents();

                    System.out.println(
                            "Updated Successfully");
                }
            }

            if (!found) {

                System.out.println(
                        "Student Not Found");
            }

        }
        catch (Exception e) {

            System.out.println(
                    e.getMessage());
        }
    }

    /*
    ========================================
    DELETE
    ========================================
    */

    public static void deleteStudent() {

        try {

            System.out.print(
                    "Enter Student ID : ");

            int id =
                    Integer.parseInt(
                            sc.nextLine());

            boolean removed =

                    students.removeIf(
                            s -> s.getId() == id);

            if (removed) {

                saveStudents();

                System.out.println(
                        "Deleted Successfully");
            }
            else {

                System.out.println(
                        "Student Not Found");
            }

        }
        catch (Exception e) {

            System.out.println(
                    e.getMessage());
        }
    }

    /*
    ========================================
    MAIN METHOD
    ========================================
    */

    public static void main(String[] args) {

        /*
        Load Existing File Data
        */

        loadStudents();

        while (true) {

            System.out.println(
                    "\n===== STUDENT MANAGEMENT SYSTEM =====");

            System.out.println(
                    "1 Add Student");

            System.out.println(
                    "2 View Students");

            System.out.println(
                    "3 Update Student");

            System.out.println(
                    "4 Delete Student");

            System.out.println(
                    "5 Exit");

            System.out.print(
                    "Enter Choice : ");

            int choice =
                    Integer.parseInt(
                            sc.nextLine());

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    updateStudent();
                    break;

                case 4:
                    deleteStudent();
                    break;

                case 5:

                    System.out.println(
                            "Thank You");

                    return;

                default:

                    System.out.println(
                            "Invalid Choice");
            }
        }
    }
}
