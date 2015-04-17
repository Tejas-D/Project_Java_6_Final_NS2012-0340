/*
 * @author Tejas Dwarkaram
 * @version 1.5.0_9
 * @since  07 November 2012, 09:42
 *
 *
 *  fileName    LoginServlet.java
 *  description Creating a servlet to access the
 *              webservice and check if the login
 *              details are correct
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
public class ProfileServlet extends HttpServlet {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        //creating a new session object
        HttpSession session = request.getSession();
        //getting the 'user' attribute from the session object
        String userName = (String)session.getAttribute("user");
        
        boolean added = false;
        //getting the parameters required for registration and checking if they are empty or not
        String name = request.getParameter("name").trim() == null ? "" : request.getParameter("name").trim();
        String surname = request.getParameter("surname").trim() == null ? "" : request.getParameter("surname").trim();
        String year = request.getParameter("year").trim() == null ? "" : request.getParameter("year").trim();
        String month = request.getParameter("month").trim() == null ? "" : request.getParameter("month").trim();
        String day = request.getParameter("day").trim() == null ? "" : request.getParameter("day").trim();
        String pass = request.getParameter("pwd").trim() == null ? "" : request.getParameter("pwd").trim();
        String pass2 = request.getParameter("cnpwd").trim() == null ? "" : request.getParameter("cnpwd").trim();
        String email = request.getParameter("email").trim() == null ? "" : request.getParameter("email").trim();
        String secQ = request.getParameter("secq").trim() == null ? "" : request.getParameter("secq").trim();
        String secA = request.getParameter("seca").trim() == null ? "" : request.getParameter("seca").trim();
        
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
        String updated = "";
        
        boolean contents = false;
        
        try { // This code block invokes the ForumWebServicePort:checkLogin operation on web service
            //creating a new instance of the implementation of the web service
            ForumService.ForumWebServiceService forumWebServiceService = new ForumService.ForumWebServiceService_Impl();
            //using the implementation of the web service to get the active port used by the web service
            ForumService.ForumWebService forumWebServicePort = forumWebServiceService.getForumWebServicePort();
            
            /*
             *Checking if the name variable exists or is empty
             */            
            if(name != "" || name != null){
                /*
                 *If Statement to check that the name only contains letters
                 */
                if(!Pattern.matches("[A-Za-z]+", name)){
                    //adding the number to errors found
                    errors = errors + "1";
                    //setting the boolean variable to false, because of an error
                    allContents = false;
                }
                if(allContents){
                    forumWebServicePort.editName(name, userName);
                    updated = updated + "1";
                }
            }
            
            /*
             *Checking if the surname variable exists or is empty
             */
            if(surname != "" || surname != null){
                /*
                 *If Statement to check that the surname only contains letters
                 */
                if(!Pattern.matches("[A-Za-z]+", surname)){
                    //adding the number to errors found
                    errors = errors + "2";
                    //setting the boolean variable to false, because of an error
                    allContents = false;
                }
                if(allContents){
                    forumWebServicePort.editSurname(surname, userName);
                    updated = updated + "2";
                }
            }
            
            /*
             *Checking if the date variable exists or is empty
             */
            if(fullDate!= "" || fullDate != null){
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
                if(allContents){
                    forumWebServicePort.editDob(fullDate, userName);
                    updated = updated + "3";
                }
            }
            
            /*
             *Checking if the pass variable exists or is empty
             */
            if(pass != "" || pass != null){
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
                if(allContents){
                    forumWebServicePort.editPassword(pass, userName);
                    updated = updated + "4";
                }
            }
            
            /*
             *Checking if the secQ variable exists or is empty
             */
            if(secQ != "" || secQ != null){
                forumWebServicePort.editSecQ(secQ, userName);
                updated = updated + "5";
            }
            
            /*
             *Checking if the secA variable exists or is empty
             */
            if(secA != "" || secA != null){
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
                    forumWebServicePort.editSecA(secA, userName);
                    updated = updated + "6";
                }
            }
            
            /*
             *Checking if the email variable exists or is empty
             */
            if(email != "" || email != null){
                /*
                 *If Statement to check that the email address contains "@" and "."
                 */
                if(!Pattern.matches(".+?@.+?\\.{1}.+?", email)){
                    //adding the number to errors found
                    errors = errors + "7";
                    //setting the boolean variable to false, because of an error
                    allContents = false;
                }
                if(allContents){
                    forumWebServicePort.editEmail(email, userName);
                    updated = updated + "7";
                }
            }
        } catch(javax.xml.rpc.ServiceException ex) {
            System.err.println("Client Error -> ServiceException on profile page : " + ex.toString());
        } catch(java.rmi.RemoteException ex) {
            System.err.println("Client Error -> RemoteExcepetion on profile page : " + ex.toString());
        } catch(Exception ex) {
            System.err.println("Client Error -> Unknown Error on profile page : " + ex.toString());
        }
        
        if(allContents){
            response.sendRedirect("profile.jsp?updated=" + updated);
        }else{
            response.sendRedirect("profile.jsp?errors=" + errors);
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