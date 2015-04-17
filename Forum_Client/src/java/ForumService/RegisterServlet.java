/*
 * @author Tejas Dwarkaram
 * @version 1.5.0_9
 * @since  24 October 2012, 03:33
 *
 *
 *  fileName    RegisterServlet.java
 *  description Creating a servlet to check
 *              the values of the register page
 *              and access the web service to
 *              add the new user
 */

package ForumService;

import java.io.*;
import java.util.regex.Pattern;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Students
 * @version
 */
public class RegisterServlet extends HttpServlet {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        boolean added = false;
        //getting the parameters required for registration and checking if they are empty or not
        String name = request.getParameter("name").trim();
        String surname = request.getParameter("surname").trim();
        String year = request.getParameter("year").trim();
        String month = request.getParameter("month").trim();
        String day = request.getParameter("day").trim();
        String pass = request.getParameter("pwd").trim();
        String pass2 = request.getParameter("cnpwd").trim();
        String email = request.getParameter("email").trim();
        String secQ = request.getParameter("secq").trim();
        String secA = request.getParameter("seca").trim();
        
        boolean allContents = true;
        
        //adding the year, month and day variables together
        String fullDate = year + "-" + month + "-" + day;
        //creating an array of all the variables
        String[] allArray = {name, surname, day, month,
        year, pass, pass2, email, secA};
        //creating an array of the months of the year
        String[] months = {"January", "February", "March", "April",
        "May","June","July","August","September",
        "October", "November", "December"};
        //sotring default values to represent the data
        String[] values = {"Name", "Surname", "Day", "Month",
        "Year","Password", "Confirm Password",
        "Email Address",  "Security Answer"};
        
        String errors = "";
        
        boolean contents = false;
        /*
         * For loop to check each value
         * for illegal characters
         */
        for(int x=0; x< allArray.length;x++){
            //Regex to check for illegal matches
            if(!Pattern.matches("[.+?@.+?\\.{1}.+?\\w]+", allArray[x])){
                //adding the number to errors found
                errors = errors + "0";
                //setting the boolean variable to false, because of an error
                allContents = false;
                break;
            }
        }
        /*
         *If statement to check if the passwords match eachother
         */
        if(!pass.equals(pass2)){
            //adding the number to errors found
            errors = errors + "6";
            //setting the boolean variable to false, because of an error
            allContents = false;
        }
        
        /*
         *If statement to check that the length of the password
         *is good
         */
        if(pass.length()<8 || pass.length()>28){
            //adding the number to errors found
            errors = errors + "5";
            //setting the boolean variable to false, because of an error
            allContents = false;
        }
        
       /*
        *If Statement to check that the name only contains letters
        */
        if(!Pattern.matches("[A-Za-z]+", name)){
            //adding the number to errors found
            errors = errors + "1";
            //setting the boolean variable to false, because of an error
            allContents = false;
        }
        
        
        /*
         *If Statement to check that the surname only contains letters
         */
        if(!Pattern.matches("[A-Za-z]+", surname)){
            //adding the number to errors found
            errors = errors + "2";
            //setting the boolean variable to false, because of an error
            allContents = false;
        }
        
        
        /*
         *If statement to check that certain
         *months only contain 30 days
         */
        if(month.equals("04") || month.equals("06") ||
                month.equals("09") || month.equals("11")){
            if(Integer.parseInt(day) > 30){
                //adding the number to errors found
                errors = errors + "3";
                //setting the boolean variable to false, because of an error
                allContents = false;
            }
        }
        
        
        /*
         *If statement to check the feb only has less than 29 days
         */
        if(month.equals("02")){
            if(Integer.parseInt(day) > 29){
                //adding the number to errors found
                errors = errors + "4";
                //setting the boolean variable to false, because of an error
                allContents = false;
            }
        }
        
        
        /*
         *If Statement to check that the email address contains "@" and "."
         */
        if(!Pattern.matches(".+?@.+?\\.{1}.+?", email)){
            //adding the number to errors found
            errors = errors + "7";
            //setting the boolean variable to false, because of an error
            allContents = false;
        }
        
        
        /*
         *If statment to check that the security answer conatins valid characters
         */
        if(!Pattern.matches("[\\w]+", secA)){
            //adding the number to errors found
            errors = errors + "8";
            //setting the boolean variable to false, because of an error
            allContents = false;
        }
        if(allContents){
            try { // This code block invokes the ForumWebServicePort:checkLogin operation on web service
                //creating a new instance of the implementation of the web service
                ForumService.ForumWebServiceService forumWebServiceService = new ForumService.ForumWebServiceService_Impl();
                //using the implementation of the web service to get the active port used by the web service
                ForumService.ForumWebService forumWebServicePort = forumWebServiceService.getForumWebServicePort();
                //storing the result in the boolean variable
                added = forumWebServicePort.addUser(name, surname, fullDate.trim(), pass, email, secQ, secA);
                if(added){
                    //redirecting to the new user page
                    response.sendRedirect("newUser.jsp");
                }else{
                    //redirecting back to the register page
                    response.sendRedirect("register.jsp?exist=1");
                }
                //catching any possible exceptions
            } catch(javax.xml.rpc.ServiceException ex) {
                System.err.println("Client Error -> ServiceException on register page : " + ex.toString());
            } catch(java.rmi.RemoteException ex) {
                System.err.println("Client Error -> RemoteExcepetion on register page : " + ex.toString());
            } catch(Exception ex) {
                System.err.println("Client Error -> UnknownException on register page : " + ex.toString());
            }
            
        }else{
            //redirecting to the register page with errors
            response.sendRedirect("register.jsp?errors=" + errors);
        }
        out.close();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
}