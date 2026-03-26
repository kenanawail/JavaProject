package univdbb;

import java.util.ArrayList;
import java.util.Scanner;

public class Student {

    private int id;
    private String name;
    private int age;
    private static ArrayList<Student> students = new ArrayList<>();
    private static int counter = 1;   

    public Student(String name, int age) {
        this.id = counter++;
        this.name = name;
        this.age = age;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age;
    }



    public static void addStudent(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        Student s = new Student(name, age);
        students.add(s);    

        Database db = new Database();
        db.insertStudent(s);

        System.out.println("Student added: " + s);
    }

    public static void deleteStudent(Scanner sc) {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

            
        Database db = new Database();
        db.deleteStudentById(id);

          
        Student toRemove = null;
        for (Student s : students) {
            if (s.getId() == id) {
                toRemove = s;
                break;
            }
        }
        if (toRemove != null) {
            students.remove(toRemove);
        }
    }
    public static void displayStudents() {
        Database db = new Database();
        ArrayList<Student> dbStudents = db.getAllStudents();

        if (dbStudents.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student s : dbStudents) {
                System.out.println(s);
            }
        }
    }

    public static void exitProgram() {
        System.out.println("Exiting program...");
        System.exit(0);
    }
}