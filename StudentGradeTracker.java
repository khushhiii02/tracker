import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double marks;

    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class StudentGradeTracker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        double total = 0;
        double highest = Double.MIN_VALUE;
        double lowest = Double.MAX_VALUE;
        String highestStudent = "";
        String lowestStudent = "";

        for (int i = 0; i < n; i++) {
            System.out.println("\nStudent " + (i + 1));

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Marks: ");
            double marks = sc.nextDouble();
            sc.nextLine();

            students.add(new Student(name, marks));

            total += marks;

            if (marks > highest) {
                highest = marks;
                highestStudent = name;
            }

            if (marks < lowest) {
                lowest = marks;
                lowestStudent = name;
            }
        }

        double average = total / n;

        System.out.println("\n========== STUDENT GRADE REPORT ==========");

        for (Student s : students) {
            System.out.println("Name: " + s.name + " | Marks: " + s.marks);
        }

        System.out.println("------------------------------------------");
        System.out.println("Average Marks : " + average);
        System.out.println("Highest Marks : " + highest + " (" + highestStudent + ")");
        System.out.println("Lowest Marks  : " + lowest + " (" + lowestStudent + ")");
        System.out.println("==========================================");

        sc.close();
    }
}
