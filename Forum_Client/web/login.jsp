<!--
-- Author                   Tejas Dwarkaram
-- FileName                 login.jsp
-- Date                     2012/11/01

-- File Description         JSP File that is the home page
                            when a user logins in succesfully
                            and shows current topics, and allows
                            for new topics to be added.
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
            String username = (String)session.getAttribute("user");
            boolean allCorrect = true;
            out.println("CrossFire&trade; : Welcome " + username );
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
        if(username == null || username == ""){
            response.sendRedirect("index.jsp?logged=false");
        }
        %>
        <table align="center" style="width:600px;">
            <tr>
                <!-- Using the custom tag that was imported in the taglib tag -->
                <td valign="top">
                    <ban:BannerTag/>
                </td>
                <td valign="top">
                    <table style="color:red;font-size:12pt;font-weight:bold;">
                        <tr>
                            <td valign="top">
                                Welcome <%out.println(username);%>
                            </td>
                            <td>
                                <form action="profile.jsp">
                                    <input type="submit" value="Edit Profile" name=<%out.println(username);%>/>
                                </form>
                            </td>
                            <td>
                                <form action="index.jsp?logoff=true">
                                    <input type="submit" value="Logout"/>
                                </form>
                            </td>   
                             
                        </tr>
                    </table>
                </td>
            </tr>
            <tr align="center">
                <td valign="top">
                    <h3 style="font-size:16pt" >
                        Latest Threads started by members
                    </h3>
                    <table border="2" style="font-size:14pt; border-color:black;">
                        <tr style="color:dodgerblue" >
                            <tr>
                                <td>Topic Name</td>
                                <td>Topic Description</td>
                                <td>Added By</td>
                                <td>Dated Added</td>
                            </tr>
                            <%
                            /*
                            *calling the webservice, and invoking the getTopics method
                            *which will display current topics if any exist
                            */
                            try {
                                //creating a new instance of the implementation of the web service
                                ForumService.ForumWebServiceService forumWebServiceService = new ForumService.ForumWebServiceService_Impl();
                                //using the implementation of the web service to get the active port used by the web service
                                ForumService.ForumWebService forumWebServicePort = forumWebServiceService.getForumWebServicePort();
                                //creating a string array to hold the values returned
                                String topics[] = forumWebServicePort.getTopics();
                                //for loop to get each element of the array returned
                                for(int y = 0; y < topics.length; y++){
                                    //Creating an array of the individual values in the elements
                                    String[] allTopics = topics[y].split(",");
                                    out.println("<tr>");
                                    String topicId = "";
                                    int count = 0;
                                    //using a for loop to print out the contents in html table form
                                    for(int x = 0; x < allTopics.length; x++){
                                        count++;
                                        if(count == 1){
                                            topicId = allTopics[x];
                                        }
                                        if(x==1){
                                            out.println("<td><a href='topicspage.jsp?topic=" + allTopics[x] + "&topicId=" + topicId + "'>" + allTopics[x] + "</td>");
                                        }else if(x>1){
                                            out.println("<td>" + allTopics[x] + "</td>");
                                        }
                                    }
                                    out.println("</tr>");
                                }
                                //catching any exception that might occure through the web service
                            } catch(javax.xml.rpc.ServiceException ex) {
                                System.err.println("ServiceException found on getting topic : " + ex.toString());
                            } catch(java.rmi.RemoteException ex) {
                                System.err.println("RemoteException found on getting topic : " + ex.toString());
                            } catch(Exception ex) {
                                System.err.println("UnknownException found on getting topic : " + ex.toString());
                            }
                            %>
                        </tr>
                    </table>
                </td>
                <!-- Creating a new HTML table to house components
                     that allow user to add a new topic for discussion-->
                <td valign="top" style="font-weight:bold;font-size:10pt">
                    <form method="post">
                        <fieldset>
                            <legend style="color:red;">
                                Add Discussion
                            </legend>
                            <table>
                                <tr>
                                    <td>
                                        <u>Topic Name</u>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="text" name="topicName"/>
                                    </td>
                                </tr>
                                <%  
                                /*
                                * Checking that the name field contains values
                                */
                                String topName = request.getParameter("topicName")== null ? "" : request.getParameter("topicName");
                                if(topName.length() < 1){
                                    out.println("<tr>");
                                    out.println("<td colspan='2' style='font-size:12pt; color:dodgerblue;'>Please enter a topic name!</td>");
                                    out.println("</tr>");
                                    allCorrect = false;
                                }
                                %>
                                <tr>
                                    <td>
                                        Whats the problem ?
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <textarea name="topicDesc"></textarea>
                                    </td>
                                </tr>
                                <%  
                                /*
                                * Checking that the description field contains values
                                */
                                String topDesc = request.getParameter("topicDesc")== null ? "  " : request.getParameter("topicDesc");
                                if(topDesc.length() < 1){
                                    out.println("<tr>");
                                    out.println("<td colspan='2' style='font-size:12pt; color:dodgerblue;'>Please enter a topic description!</td>");
                                    out.println("</tr>");
                                    allCorrect = false;
                                }
                                %>
                                <tr>
                                    <td>
                                        How urgent is this ?
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="radio" name="urgency" value="Asap" > ASAP </input>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="radio" name="urgency" value="Future" > In the near future </input>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="radio" name="urgency" value="Time" > When you have time </input>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input type="radio" name="urgency" value="Bored" > Never, Im bored </input>
                                    </td>
                                </tr>
                                <%  
                                /*
                                * Checking that the urgency field has been selected
                                */
                                String topicUrgency = request.getParameter("urgency") == null ? "  " : request.getParameter("urgency");
                                if(topicUrgency.length() < 1){
                                    out.println("<tr>");
                                    out.println("<td colspan='2' style='font-size:12pt; color:dodgerblue;'>Please enter the urgency!</td>");
                                    out.println("</tr>");
                                    allCorrect = false;
                                }
                                %>
                                <tr>
                                    <td>
                                        <input type="submit" value="Add Topic"/>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                    </form>
                    <%
                    /*
                    *If all of the fields are correct 
                    *the current date is determined, and
                    *the topic details are sent to the 
                    *web service, which adds the topic to 
                    *the table in the database
                    */
                    if(allCorrect){
                        //creating a new instance of the SimpleDateFormat class to format the date
                        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                        //getting the current sytem time and date
                        java.util.Date date = new java.util.Date();
                        //formatting the current date using the format method defined above
                        String fullDate = sdf.format(date);
                        try {
                            //creating a new instance of the implementation of the web service
                            ForumService.ForumWebServiceService forumWebServiceService = new ForumService.ForumWebServiceService_Impl();
                            //using the implementation of the web service to get the active port used by the web service
                            ForumService.ForumWebService forumWebServicePort = forumWebServiceService.getForumWebServicePort();
                            //creating a boolean variable to hold the returned "true" or "false "value"
                            boolean done = forumWebServicePort.addTopic(username.trim(), topName.trim(), topDesc.trim(), fullDate.trim());
                            response.sendRedirect("login.jsp");
                            //catching any exceptions that might be thrown
                        } catch(javax.xml.rpc.ServiceException ex) {
                            System.err.println("ServiceException found on adding topic : " + ex.toString());
                        } catch(java.rmi.RemoteException ex) {
                            System.err.println("RemoteException found on adding topic : " + ex.toString());
                        } catch(Exception ex) {
                            System.err.println("UnknownException found on adding topic : " + ex.toString());
                        }
                    }
                    %>
                </td>
            </tr>
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