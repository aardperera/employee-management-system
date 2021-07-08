package employeemanagementsystem;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DBOperations {

    String url = "jdbc:mysql://localhost:3306/employee";
    String username = "root";
    String password = "";
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    boolean addEmployee(EmployeeDetails em) {

        try {
            con = (Connection) DriverManager.getConnection(url, username, password);//get the connection

            String query = "INSERT INTO employeedetails VALUES(?,?,?,?,?,?,?,?)";

            pst = (PreparedStatement) con.prepareStatement(query);

            pst.setInt(1, em.getRegID());//add values to the sql query
            pst.setString(2, em.getFirstName());//add values to the sql query
            pst.setString(3, em.getLastName());//add values to the sql query
            pst.setInt(4, em.getAge());//add values to the sql query
            pst.setString(5, em.getCountry());//add values to the sql query
            pst.setString(6, em.getEmail());//add values to the sql query
            pst.setString(7, em.getUsername());//add values to the sql query
            pst.setString(8, em.getPassword());//add values to the sql query

            pst.executeUpdate();//execute the sql query and insert values to the db table

            return true;

        } catch (Exception e) {
            System.out.println(e);

            return false;

        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    int checkUsername(String username) {
        try {
            con = (Connection) DriverManager.getConnection(url, this.username, password);
            String query = "SELECT username FROM employeedetails";
            pst = (PreparedStatement) con.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                if (username.equals(rs.getString(1))) {
                    return 0;//the username provided already exists in the db
                }
            }
            return 1;//the username provided does not exists in the db

        } catch (Exception e) {
            System.out.println(e);
            return 2;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    int checkLogin(String userName, String Password) {
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "SELECT username,password FROM employeedetails";
            pst = (PreparedStatement) con.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                if (userName.equals(rs.getString(1))) {
                    if (Password.equals(rs.getString(2))) {
                        return 0;
                    } else {
                        return -1;//the username provided already exists in the db
                    }
                }
            }
            return 1;//the username provided does not exists in the db

        } catch (Exception e) {
            System.out.println(e);
            return 2;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }
}
