/*
 * @author Tejas Dwarkaram
 * @version 1.5.0_9
 * @since 08 November 2012, 02:30
 *
 *
 *  fileName    CommentServlet.java
 *  description Creating a servlet to check the
 *              details of a comment, and then
 *              access the webservice and insert
 *              the new comment
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
public class CommentServlet extends HttpServlet {
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //creating a new Printwriter object to write to the web page
        PrintWriter out = response.getWriter();
        
        //creating a new session object
        HttpSession session = request.getSession();
        //using the session object to retrieve the users name
        String userName = (String)session.getAttribute("user");
        //storing the parameter that holds the text of the comment posted
        String userComment = request.getParameter("commentArea");
        //converting the parameter recieved as the topic id
        int topic = Integer.parseInt(request.getParameter("topicId").trim());
        //creating a new instance of the SimpleDateFormat to format the date
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        //getting the current system time and date
        java.util.Date date = new java.util.Date();
        //formatting the current date using the SimpleDateFormat object
        String fullDate = sdf.format(date);
        
        try { // This code block invokes the ForumWebServicePort:postComments operation on web service
            //creating a new instance of the implementation of the web service
            ForumService.ForumWebServiceService forumWebServiceService = new ForumService.ForumWebServiceService_Impl();
            //using the implementation of the web service to get the active port used by the web service
            ForumService.ForumWebService forumWebServicePort = forumWebServiceService.getForumWebServicePort();
            //sending the correct details to the webservice
            forumWebServicePort.postComments(userName, userComment, fullDate, topic);
            //catching any exceptions that might occur
        } catch(javax.xml.rpc.ServiceException ex) {
            System.out.println("Client Error -> Service Exception on comment servlet : " + ex.toString());
        } catch(java.rmi.RemoteException ex) {
            System.out.println("Client Error -> Remote Exception on comment servlet : " + ex.toString());
        } catch(Exception ex) {
            System.out.println("Client Error -> Unknown Exception on comment servlet : " + ex.toString());
        }
        
        //redirecting the page bakc to the login homepage, so the new comment can be seen
        response.sendRedirect("topicspage.jsp");
        //closing the output stream that was created
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
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
