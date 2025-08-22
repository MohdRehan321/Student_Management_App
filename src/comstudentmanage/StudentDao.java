package comstudentmanage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class StudentDao {
    public static boolean insertStudentToDB(student st) {
        boolean f = false;
        try {
            //jdbc code...
            Connection con = ConnectionProvider.createConnection();
            if (con == null) {
                System.out.println("Failed to connect to DB.");
                return false;
            }
            String q = "insert into students(sname,sphone,scity) values(?,?,?)";
            // PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);

            System.out.println("Inserting: " + st.getStudentName() + ", " + st.getStudentPhone() + ", " + st.getStudentCity());


            // set the value of parameter
            pstmt.setString(1, st.getStudentName());
            pstmt.setString(2, st.getStudentPhone());
            pstmt.setString(3, st.getStudentCity());

            // execute...
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                f = true; // student is added
            }

        } catch (Exception e) {
            e.printStackTrace();
          }
        return f;
    }

    public static boolean deleteStudent(int userId) {
        boolean f = false;
        try {
                //jdbc code...
                Connection con = ConnectionProvider.createConnection();
            if (con == null) {
                System.out.println("Failed to connect to DB.");
                return false;
            }

                String q = "delete from students where sid=?";

                //PreparedStatement
                PreparedStatement pstmt = con.prepareStatement(q);

                // set the value of parameter
                pstmt.setInt(1, userId);

                // execute...
            // executeUpdate() returns the number of rows affected. If no rows are affected (e.g., invalid sid), then the method returns 0
            int rowsAffected = pstmt.executeUpdate(); // capture number of rows affected
            if (rowsAffected > 0) {
                f = true; // success: student with given sid was deleted
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public static void showAllStudent() {
        boolean f = false;
        try {
            //jdbc code...
            Connection con = ConnectionProvider.createConnection();
            String q = "select * from students";
            Statement stmt = con.createStatement();

            ResultSet set = stmt.executeQuery(q);

            while (set.next()) {
                int id = set.getInt(1);
                String name = set.getString(2);
                String phone = set.getString(3);
                String city = set.getString("scity");

                System.out.println("ID : " + id);
                System.out.println("Name : " + name);
                System.out.println("Phone : " + phone);
                System.out.println("City : " + city);
                System.out.println("---------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();

            f = true;
        }

    }

    public static boolean updateStudent(int userId, String newName, String newPhone, String newCity){
        boolean updated = false;
        try{
            //jdbc code...
            Connection con = ConnectionProvider.createConnection();
            if (con == null) {
                System.out.println("Failed to connect to DB.");
                return false;
            }
            String q = "update students SET sname=?, sphone=?, scity=? where sid=?";
            //PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(q);
            // set the value of parameter
            pstmt.setString(1, newName);
            pstmt.setString(2, newPhone);
            pstmt.setString(3, newCity);
            pstmt.setInt(4, userId);

            //execute...
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Update Rows affected: " + rowsAffected);

            if (rowsAffected > 0) {
                updated = true; // Update succeeded
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }

}
