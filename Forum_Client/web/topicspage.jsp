<!--
-- Author                   Tejas Dwarkaram
-- FileName                 topicspage.jsp
-- Date                     2012/11/01

-- File Description         JSP File that displays information,
                            comments, and replies to any topic 
                            the user selects from the login
                            homepage
-->

<%-- Creating the taglib tag to import the custom tag used for the banner --%>
<%@taglib uri="//WEB-INF/tlds/LogoFormatTag.tld" prefix="ban" %>
<html>
    
    <head>
        <title>
            <%
            /*
            *creating a string variable to retrieve the active session
            *object that contains a attribute, that signifies if the user 
            *logged in or not
             */
            String user = (String)session.getAttribute("user");
            String topic = request.getParameter("topic");
            //getting the topicId of the topic to be displayed
            String topicId = request.getParameter("topicId");
            out.println("CrossFire&trade;: " + topic + "");
            %>
        </title>
    </head>
    
    <body style="background:darkgray">
        <%
        /*
        *checking if the user is logged in, else 
        *redirecting to the index page,
        *and stating he needs to login
        */
        if(user == null || user == ""){
                response.sendRedirect("index.jsp?logged=false");
        }
        %>
        <table align="center" style="width:600px;">
            <!-- Using the custom tag that was imported in the taglib tag -->
            <tr>
                <td valign="top" colspan="2">
                    <ban:BannerTag/>
                </td>
            </tr>
            <tr>
                <td colspan="2" valign="top" align="center">
                    <table width="600px" border="2" style="font-size:14pt; border-color:black;">
                        <tr style="color:dodgerblue" >
                            <tr>
                                <td colspan="4">
                                    Topic Description
                                </td>
                                <td>
                                    Date:
                                </td>
                                <td>
                                    <%
                                    try { // This code block invokes the ForumWebServicePort:checkLogin operation on web service
                                        //creating a new instance of the implementation of the web service
                                        ForumService.ForumWebServiceService forumWebServiceService = new ForumService.ForumWebServiceService_Impl();
                                        //using the implementation of the web service to get the active port used by the web service
                                        ForumService.ForumWebService forumWebServicePort = forumWebServiceService.getForumWebServicePort();
                                        //printing out the date the topic was posted
                                        out.println(forumWebServicePort.getTopicDatePosted(topic));                                    
                                    %>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="7">
                                    <%
                                    //printing out the description of the topic selected
                                    out.println(forumWebServicePort.getTopicDescription(topic));
                                    //catching any exceptions that might occur from the webservice
                                    } catch(javax.xml.rpc.ServiceException ex) {
                                        System.err.println("ServiceException on topicsPage:" + ex.toString());
                                    } catch(java.rmi.RemoteException ex) {
                                        System.err.println("RemoteExcepetion on topicsPage :" + ex.toString());
                                    } catch(Exception ex) {
                                        System.err.println("Unknown Error on topicsPage :" + ex.toString());
                                    }
                                    %>
                                </td>
                            </tr>
                        </tr>
                    </table>
                    <hr/>
                    <hr/>
                    <big style="font-size:18; colour:gray"><b><u>Comments and Replies to this topic </u></b></big>
                    <hr/>
                    <hr/>
                </td>
            </tr>
            <!-- Creaing the HTML table layout to display the comments and replies
                 for this topic
             -->
            <tr>
                <td colspan="2" valign="top" align="center">
                    <table width="600px" style="font-size:14pt; border-color:black;">
                        <tr>
                            <td>
                                <%
                                /*
                                *printing out the contents of the comments and replies tables
                                *that are associated with the topic selected
                                */
                                try {
                                    //creating a new instance of the implementation of the web service
                                    ForumService.ForumWebServiceService forumWebServiceService = new ForumService.ForumWebServiceService_Impl();
                                    //using the implementation of the web service to get the active port used by the web service
                                    ForumService.ForumWebService forumWebServicePort = forumWebServiceService.getForumWebServicePort();
                                    //creating a string array to hold the values returned
                                    String comments[] = forumWebServicePort.getComments(topic);
                                    for(int y = 0; y < comments.length; y++){
                                        //creating a new HTML table for each comment returned
                                        out.println("<table align='center' width='600px' border='3' style='font-size:14pt; border-color:black;'>");
                                        //Creating an array of the individual values in the elements
                                        String[] allComments = comments[y].split(",");
                                        int count = 0;
                                        String commentId = "";
                                        for(int x = 0; x < allComments.length; x++){
                                            count++;
                                            /*
                                            *Performing an if..else statement to check the position of the element
                                            *to determine date, user, comment, or the id of the comment
                                            */
                                            if(count == 1){
                                                commentId = allComments[x];
                                            }else if(count == 2){
                                                //printing out who posted the topic
                                                out.println("<td valign='top' align=left colspan='2'><font style='color:red'>Comment posted by user : </font><font style='color:blue'>" + allComments[x] + "</font></td>");
                                            }else if(count == 3){
                                                out.println("<tr>");
                                                //printing out what the topic is
                                                out.println("<td colspan='2' align=center valign='top'>" + allComments[x] + "</td>");
                                                out.println("</tr>");
                                            }else if(count == 4){
                                                out.println("<tr>");
                                                //printing out when the topic was posted 
                                                out.println("<td valign='top' align='right' colspan='2'> Date posted : " + allComments[x] + "<hr/></td>");
                                                out.println("</tr>");
                                                out.println("<form action='ReplyServlet'><tr><td valign='top' align=right colspan='2' valign='top'><textarea name='replyArea'></textarea><input type='hidden' value='" + commentId + "' name='commentId'/><input type='submit' value='Reply to this'/><hr/></td></tr></form>");
                                                out.println("</table>");
                                                //creating a string array to hold the replies returned for this element
                                                String replies[] = forumWebServicePort.getReplies(commentId);
                                                for(int e = 0; e < replies.length; e++){
                                                    //Creating an array of the individual values in the elements
                                                    String[] allReplies = replies[y].split(",");
                                                    int counter = 0;
                                                    for(int w = 0; w < allReplies.length; w++){
                                                        //creating a new row to align to the bottom right of the comment
                                                        out.println("<tr><td colspan='2'><table align='right' style='font-size:11pt; border-color:black;'>");
                                                        counter++;
                                                        if(counter == 1){
                                                            //printing out who replied
                                                            out.println("<tr><td valign='top' align='center' colspan='2'><u><font style='color:green'>Reply by : </font><font style='color:aqua'>" + allReplies[w] + "</font></u></td></tr>");
                                                        }else if(counter == 2){
                                                            out.println("<tr>");
                                                            //printing out what they replied
                                                            out.println("<td colspan='2' valign='top' align='right'>" + allReplies[w] + "</td>");
                                                            out.println("</tr>");
                                                        }else if(counter == 3){
                                                            out.println("<tr>");
                                                            //printing out when they replied
                                                            out.println("<td align='right' valign='top'> Date posted : " + allReplies[w] + "</td>");
                                                            out.println("</tr>");
                                                        }
                                                        //closing the html table
                                                        out.println("</table></td></tr>");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    //catching any errors that might occur from the web service
                                } catch(javax.xml.rpc.ServiceException ex) {
                                    System.err.println("ServiceException found on topics page : " + ex.toString());
                                } catch(java.rmi.RemoteException ex) {
                                    System.err.println("RemoteException found on topics page : " + ex.toString());
                                } catch(Exception ex) {
                                    System.err.println("UnknownException found on topics page : " + ex.toString());
                                }
                                %>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <!-- creating the layout for the user to post a new comment to the topic -->
            <tr>
                <td colspan="2" valign="top" align="center">
                    <!-- Sending the contents of this form to the CommentServlet -->
                    <form action="CommentServlet">
                        <table align="center" width="600px" style="font-size:14pt; border-color:black;">
                            <tr>
                                <td>
                                    Post a comment to this topic : 
                                </td>
                                <td align="right" valign="top">
                                    <textarea name="commentArea"></textarea>
                                </td>
                                <td>
                                    <input type="hidden" value="<%out.println(topicId);%>" name="topicId"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="right">
                                    <input type="submit" value="Post Comment"/>
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>
            <tr>
                <td>
                    <a href="login.jsp">Go Back</a>
                </td>
            </tr>
            <!-- A simple footer for the page -->
            <tr style="font-weight:bold;font-size:10pt;color:red;">
                <td valign="top">
                    <p align="left">
                        Tejas Dwarkaram 
                    </p>
                </td>
                <td valign="top">
                    <p align="right">
                        NS2012-0340
                    </p>
                </td>
            </tr>
        </table>
    </body>
</html>