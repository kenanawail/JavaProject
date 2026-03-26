package univdbb;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Delete Student");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> Student.addStudent(sc);
                case 2 -> Student.displayStudents();
                case 3 -> Student.deleteStudent(sc);
                case 0 -> Student.exitProgram();
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}