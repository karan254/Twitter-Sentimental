package DB;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Administrator
 */
public class Connect {

    public static Connection conn = null;
    public static Statement stat, stat1 = null;
    static Connection con = null;
    public static ResultSet rs1;
    static PreparedStatement ps = null;
    public static ResultSet rs = null;

  

    public static Connection openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/twitter_data?user='root'&password=");

            stat = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stat1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("Connection done");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return conn;
    }

    
    public static void closeConnection() {
        try {
            stat.close();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    
    public static int saveTweets(String tweet, String user, String location, String place,String created_at,String tweetid,String type) {
        
        int i = 0;

        try {

            String sql = "insert into twitter_data.tbltweet (tweet,location,place,user,rdate,created_at,tweetid,topic)values(?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, tweet);
            statement.setString(2, location);
            statement.setString(3, place);
            statement.setString(4, user);
            statement.setString(5, getDate());
            statement.setString(6, created_at);
            statement.setString(7, tweetid);
            statement.setString(8, type);
            i = statement.executeUpdate();
        } catch (Exception e) {
         //   e.printStackTrace();
        }
     
        return i;
    }

    
    
    public static String getFileDateTime() {
        DateFormat dateFormat = new SimpleDateFormat(
                "yyyy_MM_dd_hh_mm_ss");

        Calendar cal = Calendar.getInstance();

        return dateFormat.format(cal.getTime());// "11/03/14 12:33:43";
    }

    public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd:hh:mm:ss");

        Calendar cal = Calendar.getInstance();

        return dateFormat.format(cal.getTime());// "11/03/14 12:33:43";
    }

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd");

        Calendar cal = Calendar.getInstance();

        return dateFormat.format(cal.getTime());// "11/03/14 12:33:43";
    }

    public static String getTime() {
        DateFormat dateFormat1 = new SimpleDateFormat(
                "HH:mm:ss");

        Calendar cal = Calendar.getInstance();

        return dateFormat1.format(cal.getTime());// "11/03/14 12:33:43";
    }

    public static String getDT() {
        DateFormat dateFormat = new SimpleDateFormat(
                "yyyy_MM_dd_hh_mm_ss");

        Calendar cal = Calendar.getInstance();

        return dateFormat.format(cal.getTime());// "11/03/14 12:33:43";
    }

}
