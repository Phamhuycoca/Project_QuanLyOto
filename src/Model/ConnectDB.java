/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author phamk
 */
    public class ConnectDB {
    Connection conn=null;
     String connectionString = "jdbc:sqlserver://DESKTOP-3I7FN7I:1433;" 
          + "databaseName=QuanLyOto;user=sa;password=1234;";
    public Connection getConnection(){
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionString);
            if(conn!=null)
                //JOptionPane.showMessageDialog(null, "Mo ket noi");
                System.out.println("Ok");
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
        return conn;
    }
        public static void main(String[] args) {
            ConnectDB db = new ConnectDB();
            db.getConnection();
        }
}
