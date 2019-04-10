package Foundation;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by Lukas
 * 08-04-2019.
 */
public class DB {


    private static String port;
    private static String databaseName;
    private static String userName;
    private static String password;
    private static Connection con;


    public static void establishConnection(){

        Properties props = new Properties();
        String fileName = "db.properties";
        InputStream input;

        try{
            input = new FileInputStream(fileName);
            props.load(input);
            port = props.getProperty("port","1433");
            databaseName = props.getProperty("databaseName");
            userName=props.getProperty("userName", "sa");
            password=props.getProperty("password");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con=DriverManager.getConnection("jdbc:sqlserver://localhost:"+port+";databaseName="+databaseName+"",userName,password);
            System.out.println("Database Ready");

        }catch(Exception e){
            System.out.println("Couldn't load database");
            System.err.println(e.getMessage());
        }
    }


    public static Connection getCon() {
        return con;
    }












}
