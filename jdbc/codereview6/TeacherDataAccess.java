// Julia Hinterecker

package jdbc.codereview6;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class TeacherDataAccess {

    private Connection conn;
    private static final String teacherTable = "teacher";

    public TeacherDataAccess()
            throws SQLException, ClassNotFoundException {


        //checking if the JDBC driver is available
        Class.forName("com.mysql.cj.jdbc.Driver");
        //opening a connection
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cr6_julia_hinterecker_school" +
                        "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root",
                "");

        // this connection is used to write to a file
        conn.setAutoCommit(true);
        conn.setReadOnly( false);
    }
    public void closeDb() throws SQLException {
        conn.close();
    }
    /**
     * Get all db records
     * @return
     * @throws SQLException
     */
    public List<Teacher> getAllRows()   throws SQLException {

        String sql = "SELECT * FROM " + teacherTable + " ORDER BY surname" ;
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        ResultSet rs = pstmnt.executeQuery();
        List<Teacher> list = new  ArrayList<>();

        while  (rs.next()) {
            int i = rs.getInt("teacherID" );
            String name = rs.getString("name" );
            String surname = rs.getString("surname" );
            String email = rs.getString("email");
            list.add( new Teacher(i, name, surname, email));
        }
        pstmnt.close(); // also closes related result set
        return list;
    }

    public List<Classes> getAllRows2(int i) throws SQLException {

        String sql = "SELECT class.classID, class.className FROM class INNER JOIN teachertoclass ON class.classID = teachertoclass.classID WHERE teachertoclass.teacherID = ?";
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        pstmnt.setInt(1, i);
        ResultSet rs = pstmnt.executeQuery();
        List<Classes> listClasses = new ArrayList<>();

        while (rs.next()) {
            int i2 = rs.getInt("classID");
            String name = rs.getString("className");
            listClasses.add(new Classes(i2, name));
        }
        pstmnt.close();
        return listClasses;
    }

    public  boolean nameExists(Teacher teacher) throws SQLException {

        String sql = "SELECT COUNT(id) FROM " + teacherTable + " WHERE name = ? AND id <> ?" ;
        PreparedStatement pstmnt = conn.prepareStatement(sql);
        pstmnt.setString( 1 , teacher.getName());
        pstmnt.setInt( 2 , teacher.getId());
        ResultSet rs = pstmnt.executeQuery();
        rs.next();
        int  count = rs.getInt( 1 );
        pstmnt.close();

        if  (count > 0 ) {

            return   true ;
        }

        return   false ;
    }
}
