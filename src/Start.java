import comstudentmanage.StudentDao;
import comstudentmanage.student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Start {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello,Welcome to student management app");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println("Press 1 to ADD student");
                System.out.println("Press 2 to Delete student");
                System.out.println("Press 3 to display student");
                System.out.println("Press 4 to update student");
                System.out.println("Press 5 to exit app");

                int c = Integer.parseInt(br.readLine());
                if (c == 1) {
                    // add student...
                    System.out.println("Enter user name :");
                    String name = br.readLine();

                    System.out.println("Enter user phone :");
                    String phone = br.readLine();

                    System.out.println("Enter your city :");
                    String city = br.readLine();

                    //create student object to store student
                    student st = new student(name, phone, city);
                    boolean answer = StudentDao.insertStudentToDB(st);
                    if (answer) {
                        System.out.println("Student is added successfully...");
                    } else {
                        System.out.println("Something went wrong try again...");
                    }

                } else if (c == 2) { //delete student
                    System.out.println("Enter student id to delete: ");
                    int userId = Integer.parseInt(br.readLine());
                    boolean f = StudentDao.deleteStudent(userId);
                    if (f) {
                        System.out.println("Deleted.....");
                    } else {
                        System.out.println("Something went wrong....");
                    }

                } else if (c == 3) { //display students
                    StudentDao.showAllStudent();

                } else if (c == 4) {
                    // update student
                    System.out.println("Enter student ID to update: ");
                    int userId = Integer.parseInt(br.readLine());

                    System.out.println("Enter new name: ");
                    String newName = br.readLine();

                    System.out.println("Enter new phone: ");
                    String newPhone = br.readLine();

                    System.out.println("Enter new city: ");
                    String newCity = br.readLine();

                    boolean updated = StudentDao.updateStudent(userId, newName, newPhone, newCity);
                    if (updated) {
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Update failed. Please try again.");
                    }

                } else if (c == 5) {
                    break;
                }

                System.out.println("Thankyou for using my application...");
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }


    }
}



