import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

class Student implements Comparable<Student> {
    private String name;
    private String address;
    private double GPA;

    public Student(String name, String address, double GPA) {
        this.name = name;
        this.address = address;
        this.GPA = GPA;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Address: " + address + ", GPA: " + GPA;
    }
}

public class StudentDataCollector {

    public static void main(String[] args) {
        LinkedList<Student> students = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        String name, address;
        double GPA;

        while (true) {
            System.out.println("Enter student's name (or type 'exit' to finish):");
            name = scanner.nextLine();
            if (name.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Enter student's address:");
            address = scanner.nextLine();

            while (true) {
                System.out.println("Enter student's GPA:");
                try {
                    GPA = Double.parseDouble(scanner.nextLine());
                    if (GPA < 0.0 || GPA > 4.0) {
                        throw new IllegalArgumentException("GPA must be between 0.0 and 4.0.");
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid numeric GPA.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            students.add(new Student(name, address, GPA));
        }

        // sort the list by name
        Collections.sort(students);

        // writ to a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {
            for (Student s : students) {
                writer.write(s.toString());
                writer.newLine();
            }
            System.out.println("Student data has been written to 'students.txt'");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}