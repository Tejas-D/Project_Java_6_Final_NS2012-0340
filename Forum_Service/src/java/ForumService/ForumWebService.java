/*
 * @author Tejas Dwarkaram
 * @version 1.5.0_9
 * @since 17 October 2012, 01:58
 *
 *
 *  fileName    ForumWebService.java
 *  description Creating the java file for the web service
 *              to hold all the methods the client can 
 *              access, as well as access the database
 */

package ForumService;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 *
 * @author Students
 */
@WebService()
public class ForumWebService {
    //creating a new instance of the ConnectionBEan class
    ConnectionBean connection = new ConnectionBean();
    /**
     * Web service operation
     */
    @WebMethod
    public boolean checkLogin(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        //creating a string variable to hold a default value
        String exists = "";
        //creating a new ResultSet
        ResultSet result;
        
        try {
            //Creating a new prepared statment to select the username and password and see if they correspond
            PreparedStatement loginStatement = connection.getConnection().prepareStatement("SELECT users_name, users_password FROM users WHERE users_name = ? AND users_password = ?");
            //setting the first placeholder to the username recieved from the client
            loginStatement.setString(1, username);
            //setting the first placeholder to the password recieved from the client
            loginStatement.setString(2, password);
            //executing the prepared statement and storing the results in the ResultSet
            result = loginStatement.executeQuery();
            
            //checking that the result set hold values
            while(result.next()){
                //getting the username value in the field returned
                exists = result.getString("users_name");
            }
            //closing the result set
            result.close();
            //closing the prepared statement
            loginStatement.close();
            //checking if the exists variable contains characters
            if(exists.length() < 1){
                //returning true if it did
                return false;
            }else{
                //returning false if it didnt 
                return true;
            }
            //catching a SQL exception if it occurs
        } catch(SQLException sqle) {
            System.err.println("WebServiceError -> SQL Exception - on checking login : " + sqle.toString());
        }
        return false;
    }
    
    /**
     * Web service operation
     */
    @WebMethod
    public String getQuestion(@WebParam(name = "userEmail") String userEmail) {
        //creating a String variable to hold a default value
        String question = "NoQ";
        try {
            //Creating a new prepared statment to select the secQ field where the email corresponds to the value recieved
            PreparedStatement queStatement = connection.getConnection().prepareStatement("SELECT user_secQ FROM users WHERE users_email = ?");
            //setting the first placeholder to the userEmail variable recieved from the client
            queStatement.setString(1, userEmail);
            //storing the results of the prepared statement in a resultSet
            ResultSet rs = queStatement.executeQuery();
            
            //storing the question if the result set contains any values
            while(rs.next()){
                question = rs.getString("user_secQ");
            }
            //closing the result set
            rs.close();
            //closing the prepared statement
            queStatement.close();
            //using the trim() method to remove whitespaces
            question = question.trim();
            //retrieving and reseting the questions according to what they were stored as
            if(question.equals("mum")) {
                question = "What is your mums maiden name?";
            } else if(question.equals("pet")) {
                question = "What is your first pets name?";
            } else if(question.equals("teacher")) {
                question = "What is your first teachers name?";
            } else if(question.equals("town")) {
                question = "What is your home towns name?";
            } else if(question.equals("phone")) {
                question = "What is the name of your fav phone?";
            }
        //catching a SQL Exception if an error occurs
        } catch (SQLException ex) {
            System.err.println("WebServiceError -> SQL Exception - on getting question :" + ex.toString());
        }
        //returning the question variable
        return question;
    }
    
    /**
     * Web service operation
     */
    @WebMethod
    public boolean getAnswer(@WebParam(name = "userAns") String userAns) {
        //creating the default variables to hold the values that are going to be retrieved
        String password = "NoPassword";
        String email = "default@localhost";
        try {
            //Creating a new prepared statment to select all fields where the answer corresponds to the value recieved
            PreparedStatement ansStatement = connection.getConnection().prepareStatement("SELECT * FROM users WHERE user_secA = ?");
            //setting the first placeholder to the userAns variable recieved from the client
            ansStatement.setString(1, userAns);
            //storing the results of the prepared statement in a resultSet
            ResultSet rs = ansStatement.executeQuery();
            
            //storing the email and password if the result set contains any values
            while(rs.next()){
                password = rs.getString("users_password");
                email = rs.getString("users_email");
            }
            //closing the result set
            rs.close();
            //closing the prepared statement
            ansStatement.close();
            
            if(password.equals("")){
                //if they password contains nothing false is returned
                return false;
                //if it does the JavaMail sends an email to the email address retrieved
            }else{
                //creating a new date instance and formating it to time
                Properties props = System.getProperties();
                props.put("mail.stmp.host", "127.0.0.1");
                
                //craeting a new session
                Session session = Session.getDefaultInstance(props, null);
                //creating a new instance of the MimieMessage class
                MimeMessage message = new MimeMessage(session);
                
                //setting the values required and sending the email
                message.setSubject("Password Recovery - CrossFire Forums");
                //setting the contents of the email to include the forgotten password
                message.setText("Good day. This is a recovery email for your lost password.\nYour password can be reset on next login.\nYour current password is: " + password + "\nSorry for any inconvinience caused.\nRegards \nSystem Admin\nTejas Dwarkaran\nCrossFire Team");
                //setting the email address the user will see it was sent from
                message.setFrom(new InternetAddress("administrator@crossfire.net"));
                //setting the recipient address to the email address retrieved
                message.addRecipient(RecipientType.TO, new InternetAddress(email));
                
                //sending the message
                Transport.send(message);
                //returning true
                return true;
            }
            //catching a SQL or MEssaging exception that might occur
        } catch (SQLException ex) {
            System.err.println("WebServiceError -> SQL Exception - on sending email : " + ex.toString());
        }catch(MessagingException me) {
            System.err.println("WebServiceError -> Messaging Exception - on sending email : " + me.toString());
        }
        //returning false by default
        return false;
    }
    
    /**
     * Web service operation
     */
    @WebMethod
    public boolean checkEmail(@WebParam(name = "userEmail") String userEmail, @WebParam(name = "userName") String userName) {
        try {
            //creating a prepared statement to select all fields where the user name and email address correspond
            PreparedStatement emailStatement = connection.getConnection().prepareStatement("SELECT * FROM users WHERE users_name = ? AND users_email = ?");
            //setting the first placeholder to the userName variable recieved from the client
            emailStatement.setString(1, userName);
            //setting the second placeholder to the userEmail variable recieved from the client
            emailStatement.setString(2, userEmail);
            //storing the values if the prepared statment return values      
            ResultSet rs = emailStatement.executeQuery();
            
            //if the result set contains values returns true else false
            if(rs.next()){
                //closing the result set
                rs.close();
                //closing the prepared statement
                emailStatement.close();
                return true;
            }else {
                rs.close();
                emailStatement.close();
                return false;
            }
            //catching a SQL Exception that might occur
        } catch (SQLException ex) {
            System.err.println("WebServiceError -> SQL Exception - on checking email : " + ex.toString());
        }
        //returning false by default
        return false;
    }
    
    /**
     * Web service operation
     */
    @WebMethod
    public boolean addUser(@WebParam(name = "name") String name, @WebParam(name = "surname") String surname, @WebParam(name = "dob") String dob,@WebParam(name = "password") String password, @WebParam(name = "email") String email, @WebParam(name = "secQ") String secQ, @WebParam(name = "secA") String secA) {
        try {
            int addRows;
            String emailExist = "";
            //creating a prepared statement to select email address that may be the same as the variable recieved from the client
            PreparedStatement prepState = connection.getConnection().prepareStatement("SELECT users_email FROM users WHERE users_email = ?");
            //setting the first placeholder to the email variable recieved from the client
            prepState.setString(1, email);
            //storing the returned results from the statement in a resultset
            ResultSet rs = prepState.executeQuery();
            
            //checking if there are any values in the result set
            //to check for matches
            while(rs.next()){
                emailExist = rs.getString("users_email");
            }
            
            //closing the resultset
            rs.close();
            //closing the prepared statement
            prepState.close();
            /*
             *checking if the string variable emailExists contains anything
             */
            if(emailExist.equals("")){
                //creating another prepared statement to add the user details to the database
                prepState = connection.getConnection().prepareStatement("INSERT INTO users(users_name, users_surname, users_dateOfBirth, users_password, users_email, user_secQ, user_secA) VALUES( ?,?,?,?,?,?,? )");
                prepState.setString(1, name);
                prepState.setString(2, surname);
                prepState.setString(3, dob);
                prepState.setString(4, password);
                prepState.setString(5, email);
                prepState.setString(6, secQ);
                prepState.setString(7, secA);
                //executing the preparedStatement
                addRows = prepState.executeUpdate();
                //returning true
                return true;
            }else{
                return false;
            }
            //catching any SQL Execptions that might occur
        } catch(SQLException sql) {
            System.out.println("WebServiceError -> SQL Exception - on adding user : " + sql.toString());
        }
        return false;
    }
    
    
    /**
     * Web service operation
     */
    @WebMethod
    public boolean addTopic(@WebParam(name = "user") String user, @WebParam(name = "topicName") String topicName, @WebParam(name = "topicDesc") String topicDesc, @WebParam(name = "topicDate") String topicDate) {
        try {
            int addRows;
            //creating a prepared statement to insert topic details in to the database
            PreparedStatement prepState = connection.getConnection().prepareStatement("INSERT INTO topics(topic_name, topic_decs, topic_user, topic_date) VALUES( ?,?,?,? )");
            prepState.setString(1, topicName);
            prepState.setString(2, topicDesc);
            prepState.setString(3, user);
            prepState.setString(4, topicDate);
            //executing the insert
            addRows = prepState.executeUpdate();
            //closing the prepared statment
            prepState.close();
            //returning true
            return true;
            //catching any SQL Exceptions that might occur
        } catch(SQLException sql) {
            System.out.println("WebServiceError -> SQL Exception - adding topic : " + sql.toString());
        }
        return false;
    }
    
    /**
     * Web service operation
     */
    @WebMethod
    public String[] getTopics() {
        //creating a new ArrayList to store the values retrieved from the database
        ArrayList<String> listTopics = new ArrayList<String>();
        
        try {
            //creating the prepared statement to get all the topics
            PreparedStatement prepState = connection.getConnection().prepareStatement("SELECT * FROM topics");
            //executing the prepared statment and storing the results in a result set
            ResultSet result = prepState.executeQuery();
            
            /*
             *adding each row of data to the arraylist 
             */
            while(result.next()){
                listTopics.add(result.getString("topic_id") + "," + result.getString("topic_name") + "," + result.getString("topic_decs") + "," + result.getString("topic_user") +  "," + result.getString("topic_date"));
            }
            //closing the result set
            result.close();
            //closing the prepared statement
            prepState.close();
            //catching any SQL Exceptions that might occur
        } catch(SQLException sqle) {
            System.err.println("WebServiceError -> SQL Exception - getting topics : " + sqle.toString());
        }
        //sending the arraylist to the listToString method and returning that
        return listToString(listTopics);
    }
    
    /*
     * @name listToString
     * @param List<String>
     * 
     * @description method to convert any List object
     *              to a String array
     */
    private String[] listToString(List<String> list) {
        //creating a new String Array with the size of the List recieved
        String[] stringArray = new String[list.size()];
        //storing each value of the list in the array
        for(int x = 0; x != list.size(); x++) {
            stringArray[x] = list.get(x);
        }
        //returning the string array
        return stringArray;
    }

    /**
     * Web service operation
     */
    @WebMethod
    public String getTopicDescription(@WebParam(name = "topicName") String topicName) {
        //creating a new Result Set
        ResultSet result;
        //creating a default string variable
        String topicDescription = "";
        
        try {
            //creating a new Prepared Statement to get the details of a specific topic
            PreparedStatement prepState = connection.getConnection().prepareStatement("SELECT * FROM topics WHERE topic_name = ?");
            //making the first placholders value of the parameter recieved
            prepState.setString(1, topicName);
            //executing the prepared statement and storing the values in a result set
            result = prepState.executeQuery();
            
            /*
             * While there are values in teh result set
             * Storing the value of the topic_decs column in the 
             *      topicDescription variable
             */
            while(result.next()){
                topicDescription = result.getString("topic_decs");
            }
            
            //closing the result set
            result.close();
            //closing the prepared statement
            prepState.close();
            //catching any Sql exception that might occure
        } catch(SQLException sqle) {
            System.err.println("WebServiceError -> SQL Exception - on getting topic description : " + sqle.toString());
        }
        //returning the topicDescription retrieved from the database
        return topicDescription;
    }

    /**
     * Web service operation
     */
    @WebMethod
    public String getTopicDatePosted(@WebParam(name = "topicName") String topicName) {
        //Creating a new result set
        ResultSet result;
        String topicDate = "";
        
        try {
            //creating a new Prepared Statement to get the details of a specific topic
            PreparedStatement prepState = connection.getConnection().prepareStatement("SELECT * FROM topics WHERE topic_name = ?");
            //making the first placholders value of the parameter recieved
            prepState.setString(1, topicName);
            //executing the prepared statement and storing the values in a result set
            result = prepState.executeQuery();
            
            /*
             * While there are values in teh result set
             * Storing the value of the topic_date column in the 
             *      topicDate variable
             */
            while(result.next()){
                topicDate = result.getString("topic_date");
            }
            
            //closing the result set
            result.close();
            //closing the prepared statement
            prepState.close();
            //catching any Sql exception that might occur
        } catch(SQLException sqle) {
            System.err.println("WebServiceError -> SQL Exception - on getting topic date : " + sqle.toString());
        }
        return topicDate;
    }

    /**
     * Web service operation
     */
    @WebMethod
    public void editName(@WebParam(name = "newName") String newName, @WebParam(name = "userName") String userName) {
        try {
            //creating the prepared statement to update the users table
            PreparedStatement prepState = connection.getConnection().prepareStatement("UPDATE users SET users_name  = ? WHERE users_name = ?");
            //setting the first placeholder to the new value parameter recieved
            prepState.setString(1, newName);
            //setting the second parameter to the users name parameter recieved
            prepState.setString(2, userName);
            //executing the prepared statement
            prepState.executeUpdate();
            //closing the prepared statement
            prepState.close();
            //catching any SQL Exception that occurs
        } catch(SQLException sqle) {
            System.err.println("WebServiceError -> SQL Exception - on updating name : " + sqle.toString());
        }
    }
    
    /**
     * Web service operation
     */
    @WebMethod
    public void editSurname(@WebParam(name = "newSurname") String newSurname, @WebParam(name = "userName") String userName) {
        try {
            //creating the prepared statement to update the users table
            PreparedStatement prepState = connection.getConnection().prepareStatement("UPDATE users SET users_surname  = ? WHERE users_name = ?");
            //setting the first placeholder to the new value parameter recieved
            prepState.setString(1, newSurname);
            //setting the second parameter to the users name parameter recieved
            prepState.setString(2, userName);
            //executing the prepared statement
            prepState.executeUpdate();
            //closing the prepared statement
            prepState.close();
            //catching any SQL Exception that occurs
        } catch(SQLException sqle) {
            System.err.println("WebServiceError -> SQL Exception - on updating surname : " + sqle.toString());
        }
    }
    
    /**
     * Web service operation
     */
    @WebMethod
    public void editDob(@WebParam(name = "newDob") String newDob, @WebParam(name = "userName") String userName) {
        try {
            //creating the prepared statement to update the users table
            PreparedStatement prepState = connection.getConnection().prepareStatement("UPDATE users SET users_dateOfBirth  = ? WHERE users_name = ?");
            //setting the first placeholder to the new value parameter recieved
            prepState.setString(1, newDob);
            //setting the second parameter to the users name parameter recieved
            prepState.setString(2, userName);
            //executing the prepared statement
            prepState.executeUpdate();
            //closing the prepared statement
            prepState.close();
            //catching any SQL Exception that occurs
        } catch(SQLException sqle) {
            System.err.println("WebServiceError -> SQL Exception - on updating dob : " + sqle.toString());
        }
    }
    
    /**
     * Web service operation
     */
    @WebMethod
    public void editPassword(@WebParam(name = "newPass") String newPass, @WebParam(name = "userName") String userName) {
        try {
            //creating the prepared statement to update the users table
            PreparedStatement prepState = connection.getConnection().prepareStatement("UPDATE users SET users_password  = ? WHERE users_name = ?");
            //setting the first placeholder to the new value parameter recieved
            prepState.setString(1, newPass);
            //setting the second parameter to the users name parameter recieved
            prepState.setString(2, userName);
            //executing the prepared statement
            prepState.executeUpdate();
            //closing the prepared statement
            prepState.close();
            //catching any SQL Exception that occurs
        } catch(SQLException sqle) {
            System.err.println("WebServiceError -> SQL Exception - on updating pass : " + sqle.toString());
        }
    }
    
    /**
     * Web service operation
     */
    @WebMethod
    public void editSecQ(@WebParam(name = "newQues") String newQues, @WebParam(name = "userName") String userName) {
        try {
            //creating the prepared statement to update the users table
            PreparedStatement prepState = connection.getConnection().prepareStatement("UPDATE users SET user_secQ  = ? WHERE users_name = ?");
            //setting the first placeholder to the new value parameter recieved
            prepState.setString(1, newQues);
            //setting the second parameter to the users name parameter recieved
            prepState.setString(2, userName);
            //executing the prepared statement
            prepState.executeUpdate();
            //closing the prepared statement
            prepState.close();
            //catching any SQL Exception that occurs
        } catch(SQLException sqle) {
            System.err.println("WebServiceError -> SQL Exception - on updating secQ : " + sqle.toString());
        }
    }
    
    /**
     * Web service operation
     */
    @WebMethod
    public void editSecA(@WebParam(name = "newAns") String newAns, @WebParam(name = "userName") String userName) {
        try {
            //creating the prepared statement to update the users table
            PreparedStatement prepState = connection.getConnection().prepareStatement("UPDATE users SET user_secA  = ? WHERE users_name = ?");
            //setting the first placeholder to the new value parameter recieved
            prepState.setString(1, newAns);
            //setting the second parameter to the users name parameter recieved
            prepState.setString(2, userName);
            //executing the prepared statement
            prepState.executeUpdate();
            //closing the prepared statement
            prepState.close();
            //catching any SQL Exception that occurs
        } catch(SQLException sqle) {
            System.err.println("WebServiceError -> SQL Exception - on updating secA : " + sqle.toString());
        }
    }
    
    /**
     * Web service operation
     */
    @WebMethod
    public void editEmail(@WebParam(name = "newEmail") String newEmail, @WebParam(name = "userName") String userName) {
        try {
            //creating the prepared statement to update the users table
            PreparedStatement prepState = connection.getConnection().prepareStatement("UPDATE users SET users_email  = ? WHERE users_name = ?");
            //setting the first placeholder to the new value parameter recieved
            prepState.setString(1, newEmail);
            //setting the second parameter to the users name parameter recieved
            prepState.setString(2, userName);
            //executing the prepared statement
            prepState.executeUpdate();
            //closing the prepared statement
            prepState.close();
            //catching any SQL Exception that occurs
        } catch(SQLException sqle) {
            System.err.println("WebServiceError -> SQL Exception - on updating secA : " + sqle.toString());
        }
    }

    /**
     * Web service operation
     */
    @WebMethod
    public String[] getComments(@WebParam(name = "topicName") String topicName) {
        ArrayList<String> listComments = new ArrayList<String>();        
        try {
            int topicId = 0;
            //creating a prepared statement to select the topic id from the topics table where the names correpond
            PreparedStatement prepState = connection.getConnection().prepareStatement("SELECT topic_id FROM topics WHERE topic_name = ?");
            //setting the place holder to the parameter recieved
            prepState.setString(1, topicName);
            // storing the results of the prepared statement in a result set
            ResultSet result = prepState.executeQuery();
            
            /*
             *While the result set contains values
             *Storing the topic ID in the topic ID variable
             */
            while(result.next()){
                topicId = result.getInt("topic_id");
            }
            //creating another prepared statement to select all the comments form the comments table where the topic id correspond
            prepState = connection.getConnection().prepareStatement("SELECT * FROM comment WHERE topic_id = ?");
            //setting the first placeholder to the topic ID that was retrieved above
            prepState.setInt(1, topicId);
            //storing the results of the prepared statement into the result set
            result = prepState.executeQuery();
            
            /*
             * While the result set contains values 
             * Storing the values in list variable
             */
            while(result.next()){
                listComments.add(result.getString("comment_id") + "," + result.getString("users_name") + "," + result.getString("user_text") + "," + result.getString("comment_date"));
            }
            
            //closing the result set
            result.close();
            //closing the prepared statement
            prepState.close();
            
        } catch(SQLException sqle) {
            System.err.println("WebServiceError -> SQL Exception - on getting comments : " + sqle.toString());
        }
        //sending the arraylist to the listToString method and returning that
        return listToString(listComments);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public String[] getReplies(@WebParam(name = "commentId") String commentId) {
        //creating a new ArrayList to store the values retrieved from the database
        ArrayList<String> listComments = new ArrayList<String>();        
        try {
            //creating the prepared statement to get all the replies where the comment id's correspond                       
            PreparedStatement prepState = connection.getConnection().prepareStatement("SELECT * FROM replies WHERE comment_id = ?");
            // setting the first placeholder to the parameter recieved
            prepState.setInt(1, Integer.parseInt(commentId));
            //executing the prepared statment and storing the results in a result set
            ResultSet result = prepState.executeQuery();
            
            /*
             *adding each row of data to the arraylist 
             */
            while(result.next()){
                listComments.add(result.getString("users_name") + "," + result.getString("user_reply") + "," + result.getString("reply_date"));
            }            
            //closing the result set
            result.close();
            //closing the prepared statement
            prepState.close();
            //catching any SQL Exceptions that might occur
        } catch(SQLException sqle) {
            System.err.println("WebServiceError -> SQL Exception - on getting comments : " + sqle.toString());
        }
        return listToString(listComments);
    }

    /**
     * Web service operation
     */
    @WebMethod
    @Oneway
    public void postComments(@WebParam(name = "userName") String userName, @WebParam(name = "userComment") String userComment, @WebParam(name = "date") String date, @WebParam(name = "topicId") int topicId) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:SS");
        Date curDate = new Date();
        String time = sdf.format(curDate);
        try {
            //creating a prepared statment to insert a new record in the comments table
            PreparedStatement prepState = connection.getConnection().prepareStatement("INSERT INTO comment(users_name, user_text, comment_date, topic_id) VALUES( ?,?,?,? )");
            //setting the name
            prepState.setString(1, userName);
            //setting the comment
            prepState.setString(2, userComment);
            //setting the date
            prepState.setString(3, date);
            //setting the topicId
            prepState.setInt(4, topicId);
            //executing the insert
            prepState.executeUpdate();
            //closing the prepared statement
            prepState.close();
            
            FileWriter fileOut = new FileWriter("C:/CrossFireLog.xflog", true);
            fileOut.write("-_-_-_-_-_-_-_-_-_\n");
            fileOut.write(date + " - " + time + " = User : " + userName + "\n");
            fileOut.write("\t Comment: " + userComment + " || On topic ID : " + topicId + "\n");
            fileOut.write("-_-_-_-_-_-_-_-_-_\n");
            
            fileOut.close();            
        } catch(SQLException sqle) {
            System.err.println("WebServiceError -> SQL Exception - on posting comments : " + sqle.toString());
        } catch(IOException ioe){
            System.err.println("WebServiceError -> IOException - on posting comments : " + ioe.toString());
        }
    }

    /**
     * Web service operation
     */
    @WebMethod
    @Oneway
    public void postReplies(@WebParam(name = "userName") String userName, @WebParam(name = "userReply") String userReply, @WebParam(name = "date") String date, @WebParam(name = "commentId") int commentId) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:SS");
        Date curDate = new Date();
        String time = sdf.format(curDate);
        
        try {
            //creating a prepared statment to insert a new record in the comments table
            PreparedStatement prepState = connection.getConnection().prepareStatement("INSERT INTO replies(users_name, user_reply, reply_date, comment_id) VALUES( ?,?,?,? )");
            //setting the name
            prepState.setString(1, userName);
            //setting the replyText
            prepState.setString(2, userReply);
            //setting the date
            prepState.setString(3, date);
            //setting the commentId
            prepState.setInt(4, commentId);
            //executing the insert
            prepState.executeUpdate();
            //closing the prepared statement
            prepState.close();
            
            FileWriter fileOut = new FileWriter("C:/CrossFireLog.xflog", true);
            fileOut.write("-_-_-_-_-_-_-_-_-_\n");
            fileOut.write(date + " - " + time + " = User : " + userName + "\n");
            fileOut.write("\t Reply: " + userReply + " || On comment ID : " + commentId + "\n");
            fileOut.write("-_-_-_-_-_-_-_-_-_\n");
            
            fileOut.close();
        } catch(SQLException sqle) {
            System.err.println("WebServiceError -> SQL Exception - on posting replies : " + sqle.toString());
        } catch(IOException ioe){
            System.err.println("WebServiceError -> IOException - on posting replies : " + ioe.toString());
        }
    }
}