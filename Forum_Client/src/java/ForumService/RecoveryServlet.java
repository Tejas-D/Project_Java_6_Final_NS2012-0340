/*
 * @author Tejas Dwarkaram
 * @version 1.5.0_9
 * @since  06 November 2012, 09:06
 *
 *
 *  fileName    RecoveryServlet.java
 *  description Creating a servlet to access the
 *              webservice and help retrieve a lost
 *              or forgotten password
 */

package ForumService;

import com.sun.msv.driver.textui.ReportErrorHandler;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Students
 * @version
 */
public class RecoveryServlet extends HttpServlet {
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
        //getting the step attribute from the session
        String number = (String)session.getAttribute("step");
        
        boolean correct = false;
        //checking which "step" is curently in play
        if(number.equals("1")){
            //getting the email address and name from the request object
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            
            try { // This code block invokes the ForumWebServicePort:checkLogin operation on web service
                //creating a new instance of the implementation of the web service
                ForumService.ForumWebServiceService forumWebServiceService = new ForumService.ForumWebServiceService_Impl();
                //using the implementation of the web service to get the active port used by the web service
                ForumService.ForumWebService forumWebServicePort = forumWebServiceService.getForumWebServicePort();
                //storing the result in a variable
                correct = forumWebServicePort.checkEmail(email, name);
                //catching any exceptions that might occur
            } catch(javax.xml.rpc.ServiceException ex) {
                System.err.println("Client Error -> ServiceException on recovery page :" + ex.toString());
            } catch(java.rmi.RemoteException ex) {
                System.err.println("Client Error -> RemoteExcepetion on recovery page :" + ex.toString());
            } catch(Exception ex) {
                System.err.println("Client Error -> Unknown Error on recovery page :" + ex.toString());
            }
            
            //if the name and email correspond
            if(correct){
                String question = "";
                try { // This code block invokes the ForumWebServicePort:checkLogin operation on web service
                    //creating a new instance of the implementation of the web service
                    ForumService.ForumWebServiceService forumWebServiceService = new ForumService.ForumWebServiceService_Impl();
                    //using the implementation of the web service to get the active port used by the web service
                    ForumService.ForumWebService forumWebServicePort = forumWebServiceService.getForumWebServicePort();
                    //storing the string return in the question variable
                    question = forumWebServicePort.getQuestion(email);
                } catch(javax.xml.rpc.ServiceException ex) {
                System.err.println("Client Error -> ServiceException on recovery page :" + ex.toString());
            } catch(java.rmi.RemoteException ex) {
                System.err.println("Client Error -> RemoteExcepetion on recovery page :" + ex.toString());
            } catch(Exception ex) {
                System.err.println("Client Error -> Unknown Error on recovery page :" + ex.toString());
            }
                //redirecting back to the remember page, which will reset the attribute to step 2
                response.sendRedirect("remember.jsp?step=2&que=" + question);
            }else{
                //redirecting back to the remember page, which will reset the attribute to step 1
                response.sendRedirect("remember.jsp?step=1");
            }
        }else{
            //getting the answer from the request object
            String answer = request.getParameter("answer");
            boolean recovered = false;
            try { // This code block invokes the ForumWebServicePort:checkLogin operation on web service
                //creating a new instance of the implementation of the web service
                ForumService.ForumWebServiceService forumWebServiceService = new ForumService.ForumWebServiceService_Impl();
                //using the implementation of the web service to get the active port used by the web service
                ForumService.ForumWebService forumWebServicePort = forumWebServiceService.getForumWebServicePort();
                //storing the boolean of whether the answer was correct
                recovered = forumWebServicePort.getAnswer(answer);
            } catch(javax.xml.rpc.ServiceException ex) {
                System.err.println("Client Error -> ServiceException on recovery page :" + ex.toString());
            } catch(java.rmi.RemoteException ex) {
                System.err.println("Client Error -> RemoteExcepetion on recovery page :" + ex.toString());
            } catch(Exception ex) {
                System.err.println("Client Error -> Unknown Error on recovery page :" + ex.toString());
            }
            
            //checking if true or false
            if(recovered){
                //redirecting to the remember page
                response.sendRedirect("remember.jsp?step=3");
            }else{
                //redirecting to the error page
                response.sendRedirect("remember.jsp?step=1");
            }
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