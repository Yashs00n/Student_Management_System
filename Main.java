import java.util.Scanner;

public class Main {

    public static void main(
            String[] args) {

        Scanner sc =
                new Scanner(System.in);

        StudentService service =
                new StudentService();

        try {

            System.out.print(
                    "Enter ID : ");

            int id =
                    sc.nextInt();

            sc.nextLine();

            System.out.print(
                    "Enter Name : ");

            String name =
                    sc.nextLine();

            System.out.print(
                    "Enter Age : ");

            int age =
                    sc.nextInt();

            StudentValidator
                    .validate(
                            id,
                            name,
                            age);

            Student student =
                    new Student(
                            id,
                            name,
                            age);

            service.addStudent(
                    student);

            System.out.println(
                    "Student Added Successfully");

            service.displayStudents();
        }

        catch(StudentException e){

            System.out.println(
                    "Validation Error : "
                    + e.getMessage());
        }

        catch(Exception e){

            System.out.println(
                    "System Error : "
                    + e.getMessage());
        }

        finally {

            sc.close();
        }
    }
}
