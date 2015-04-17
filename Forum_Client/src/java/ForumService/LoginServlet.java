/*
 * @author Tejas Dwarkaram
 * @version 1.5.0_9
 * @since  24 October 2012, 10:54
 *
 *
 *  fileName    LoginServlet.java
 *  description Creating a servlet to access the
 *              webservice and check if the login
 *              details are correct
 */

package ForumService;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Students
 * @version
 */
public class LoginServlet extends HttpServlet {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        boolean correct = false;
        //getting the username parameter from the request object
        String username = request.getParameter("username");
        //getting the password parameter from the request object
        String password = request.getParameter("password");
        try { // This code block invokes the ForumWebServicePort:checkLogin operation on web service
            //creating a new instance of the implementation of the web service
            ForumService.ForumWebServiceService forumWebServiceService = new ForumService.ForumWebServiceService_Impl();
            //using the implementation of the web service to get the active port used by the web service
            ForumService.ForumWebService forumWebServicePort = forumWebServiceService.getForumWebServicePort();
            //storing the boolean value returned from the web service on checkin login
            correct = forumWebServicePort.checkLogin(username, password);
            //catching any exceptions if they might occur
        } catch(javax.xml.rpc.ServiceException ex) {
            System.err.println("Client Error -> ServiceException on login page :" + ex.toString());
        } catch(java.rmi.RemoteException ex) {
            System.err.println("Client Error -> RemoteExcepetion on login page :" + ex.toString());
        } catch(Exception ex) {
            System.err.println("Client Error -> UnknownException on login page :" + ex.toString());
        }
        
        //checking if the login returned true
        if(correct){
            //creating a new session object
            HttpSession session = request.getSession();
            //creating a new attribute to hold that a user is logged in
            session.setAttribute("user", username);
            //redirecting the page to the login homepage
            response.sendRedirect("login.jsp?user=" + username);
        }else{
            //if the login was false, redirecting to the index page
            response.sendRedirect("index.jsp?error=1");
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