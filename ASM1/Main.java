
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Student> studentList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            while (true) {
                System.out.print("\n0: View \n1: Add Student \n2: Remove Student \n3: Edit Student \n4: Search Student \n5: Sort Student \n6: Exit \n");
                System.out.print("\nEnter: ");
                int input = scanner.nextInt();
                scanner.nextLine();
                switch (input) {
                    case 6 -> {
                        System.out.println("Exit program.");
                        return; // Thoát khỏi chương trình
                    }
                    case 0 ->
                        printStudent();
                    case 1 -> {
                        System.out.print("\nNumber of students: ");
                        int num = scanner.nextInt();
                        scanner.nextLine();
                        if (num > 0) {
                            for (int i = 0; i < num; i++) {
                                addStudent();
                            }
                        } else {
                            System.out.println("Number must be > 0");
                        }
                    }
                    case 2 -> {
                        System.out.print("Number of students to remove: ");
                        int num = scanner.nextInt();
                        scanner.nextLine();
                        if (num > 0 && num <= Student.count) {
                            for (int i = 0; i < num; i++) {
                                removeStudent();
                            }
                        } else if (num <= 0) {
                            System.out.println("Number must be > 0");
                        } else {
                            System.out.println("Please enter a smaller number!");
                        }
                    }
                    case 3 ->
                        editStudent();
                    case 4 ->
                        searchStudent();
                    case 5 -> {
                        System.out.print("Sort Descending(1) / Sort Ascending(2) ?: ");
                        int select = scanner.nextInt();
                        scanner.nextLine();
                        if (select == 1) {
                            sortDescending();
                        } else if (select == 2) {
                            sortAscending();
                        } else {
                            System.out.println("Invalid selection. Please enter 1 or 2.");
                        }
                    }
                    default ->
                        System.out.println("Please enter a number between 0 and 6.");
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input, please try again!");
            scanner.nextLine(); // sửa loop
        } finally {
            scanner.close();
        }
    }

    public static void printStudent() {
        for (int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);
            System.out.println(s.StudentToString());
        }
    }

    public static void addStudent() {
        List<Subject> subjects = new ArrayList<>();
        System.out.print("\nEnter Student Code: ");
        String code = scanner.nextLine();
        if (checkDuplicate(code)) {
            System.out.println("Student already existed!");
        } else {
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter number of subjects: ");
            int numSubject = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < numSubject; i++) {
                System.out.print("Enter Subject Name: ");
                String subjectName = scanner.nextLine();
                System.out.print("Enter Credit: ");
                int credit = scanner.nextInt();
                System.out.print("Enter Grade of this subject: ");
                double grade = scanner.nextDouble();
                scanner.nextLine();
                Subject subject = new Subject(subjectName, credit, grade);
                subjects.add(subject);
            }
            String mark = "NONE";
            Student student = new Student(code, name, mark, subjects);
            Student.count += 1;
            studentList.add(student);
            System.out.println("Add student successfully.");
        }
    }

    private static boolean checkDuplicate(String code) {
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if (student.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static void editStudent() {
        Iterator<Student> iterator = studentList.iterator();
        System.out.print("\nEnter Student Code to edit: ");
        String studentCode = scanner.nextLine();
        boolean edited = false;
        boolean found = false;
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getCode().equals(studentCode)) {
                System.out.print("Enter new Student Code (leave empty to keep current): ");
                String newCode = scanner.nextLine();
                if (!newCode.isEmpty()) {
                    if (checkDuplicate(newCode)) {
                        System.out.println("Student already exits");
                    } else {
                        student.setCode(newCode);
                    }
                }

                System.out.print("Enter new Student Name (leave empty to keep current): ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) {
                    student.setName(newName);
                }

                System.out.print("Do you want to edit subjects? (y/n): ");
                String editSubjects = scanner.nextLine();
                if (editSubjects.equals("y")) {
                    List<Subject> subjectStudent = student.getSubjects();
                    for (Subject subject : subjectStudent) {
                        System.out.println(" - " + subject.getsName()
                                + " (Credit: " + subject.getCredit()
                                + ", Grade: " + subject.getGrade() + ")");
                    }
                    System.out.print("Do you want to add, remove, or edit subjects? (add/remove/edit): ");
                    String choose = scanner.nextLine();

                    switch (choose) {
                        case "add" -> {
                            System.out.print("Enter Subject Name: ");
                            String newSName = scanner.nextLine();
                            System.out.print("Enter Credit: ");
                            int newCredit = scanner.nextInt();
                            System.out.print("Enter Grade of this subject: ");
                            double newGrade = scanner.nextDouble();
                            scanner.nextLine();
                            if (newGrade <= 10 && newGrade >= 0) {
                                Subject newSubject = new Subject(newSName, newCredit, newGrade);
                                student.addSubject(newSubject);
                                System.out.println("Subject added successfully.");
                            } else {
                                System.out.println("Grade must in 0 -> 10");
                                return;
                            }
                        }
                        case "remove" -> {
                            System.out.print("Enter Subject Name to remove: ");
                            String subjectName = scanner.nextLine();
                            Iterator<Subject> subjectIterator = student.getSubjects().iterator();
                            while (subjectIterator.hasNext()) {
                                Subject subject = subjectIterator.next();
                                if (subject.getsName().equals(subjectName)) {
                                    subjectIterator.remove();
                                    found = true;
                                    System.out.println("Subject removed successfully.");
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("Subject not found.");
                            }
                        }
                        case "edit" -> {
                            System.out.print("Enter Subject Name to edit: ");
                            String subjectName = scanner.nextLine();
                            List<Subject> subjects = student.getSubjects();
                            for (Subject subject : subjects) {
                                if (subject.getsName().equals(subjectName)) {
                                    found = true;
                                    System.out.print("Enter new Name (leave empty to keep current): ");
                                    String newSname = scanner.nextLine();
                                    if (!newSname.isEmpty()) {
                                        subject.setsName(newSname);
                                    }
                                    System.out.print("Enter new Credit (leave empty to keep current "
                                            + subject.getCredit() + "): ");
                                    String newScredit = scanner.nextLine();
                                    if (!newScredit.isEmpty()) {
                                        subject.setCredit(Integer.parseInt(newScredit));
                                    }
                                    System.out.print("Enter new Grade (leave empty to keep current "
                                            + subject.getGrade() + "): ");
                                    String newSgrade = scanner.nextLine();
                                    if (!newSgrade.isEmpty()) {
                                        if (Double.parseDouble(newSgrade) <= 10 && Double.parseDouble(newSgrade) >= 0) {
                                            subject.setGrade(Double.parseDouble(newSgrade));
                                        } else {
                                            System.out.println("Grade must in 0 -> 10");
                                            return;
                                        }
                                    }
                                    System.out.println("Subject edited successfully.");
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("Subject not found.");
                                return;
                            }
                        }
                        default ->
                            System.out.println("\nNo subject had been edited.");
                    }
                }
                edited = true;
                break;
            }
        }
        if (edited) {
            System.out.println("\nStudent with code " + studentCode + " has been edited.");
        } else {
            System.out.println("\nNo student found with code " + studentCode);
        }
    }

    public static void removeStudent() {
        Iterator<Student> iterator = studentList.iterator();
        System.out.print("\nEnter the Student Code to remove: ");
        String code = scanner.nextLine();
        boolean removed = false;

        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getCode().equals(code)) {
                iterator.remove();
                Student.count -= 1;
                removed = true;
                break;
            }
        }
        if (removed) {
            System.out.println("\nStudent with code " + code + " has been removed.");
        } else {
            System.out.println("\nNo student found with code " + code);
        }
    }

    public static void searchStudent() {
        Iterator<Student> iterator = studentList.iterator();
        List<Student> foundStudent = new ArrayList<>();
        System.out.print("\nEnter the Student Code/Name to search: ");
        String search = scanner.nextLine();

        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getName().equalsIgnoreCase(search) || student.getCode().equals(search)) {
                foundStudent.add(student);
            }
        }
        if (foundStudent.isEmpty()) {
            System.out.println("\nNo student found!");
        } else {
            for (Student s : foundStudent) {
                System.out.println(s.StudentToString());
            }
        }
    }

    public static void sortAscending() {
        List<Student> sortedStudents = new ArrayList<>(studentList);

        for (int i = 0; i < sortedStudents.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (sortedStudents.get(i).getPoint() < sortedStudents.get(j).getPoint()) {
                    Student temp = sortedStudents.get(j);
                    sortedStudents.set(j, sortedStudents.get(i));
                    sortedStudents.set(i, temp);
                }
            }
        }
        studentList = sortedStudents;
        printStudent();
    }

    public static void sortDescending() {
        List<Student> sortedStudents = new ArrayList<>(studentList);

        for (int i = 0; i < sortedStudents.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (sortedStudents.get(i).getPoint() > sortedStudents.get(j).getPoint()) {
                    Student temp = sortedStudents.get(j);
                    sortedStudents.set(j, sortedStudents.get(i));
                    sortedStudents.set(i, temp);
                }
            }
        }
        studentList = sortedStudents;
        printStudent();
    }
}
