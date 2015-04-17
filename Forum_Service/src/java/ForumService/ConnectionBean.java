package ForumService;
/*
 * @author Tejas Dwarkaram
 * @version 1.5.0_9
 * @since 21 October 2012, 10:51
 *
 *
 *  fileName    ConnectionBean.java
 *  description A java bean to initiate a
 *              connectiong to the database
 */


//importing the relevant libraries
import java.io.*;
import java.sql.*;

/*
 * 	Creating the bean class and implementing the serializable
 *	class
 */
public class ConnectionBean implements Serializable {
    private Connection connection;
    private static String dbURL = "jdbc:odbc:forum_db";
    private static String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
    
        /*
         *	Creating the constructor for the bean
         */
    public ConnectionBean() {
        makeConnection();
    }
    
        /*
         *	Creating the method to check if a connection is on
         *	@param null
         *	@return Connection type object
         */
    public Connection getConnection() {
        //checking if the connection variable has been created or not
        if(connection == null) {
            makeConnection();
        }
        return connection;
    }
    
        /*
         *	Creating the method to stop the connection
         *	@param Boolean variable of state of connection
         *	@return Null
         */
    public void setCloseConnection(boolean close) {
        //if the connection is true
        if(close) {
            try {
                //close the connection
                connection.close();
            } catch(Exception e) {
                connection = null;
            }
        }
    }
    
        /*
         *	Creating the method to create a connection
         *	@param null
         *	@return null
         */
    public void makeConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(dbURL);
        } catch(SQLException sqle) {
            System.out.println("Web Service Error -> SQL Exception on making db connection : " + sqle);
        } catch(ClassNotFoundException cnfe) {
            System.out.println("Web Service Error -> ClassNotFound Exception on making db connection : " + cnfe);
        }
    }
}