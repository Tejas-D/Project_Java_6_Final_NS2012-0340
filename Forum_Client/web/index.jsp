<!--
-- Author                   Tejas Dwarkaram
-- FileName                 index.jsp
-- Date                     2012/11/01

-- File Description         JSP File that is the main page
                            /index page of the client website
                            contains little detail about the 
                            forum, as well as facility to go sign
                            up, and login as well as to recover
                            passwords
-->

<%-- Creating the taglib tag to import the custom tag used for the banner --%>
<%@taglib uri="//WEB-INF/tlds/LogoFormatTag.tld" prefix="ban" %>
<html>
    <head>
        <title>CrossFire&trade;</title>
        <!-- setting a new session object that will be used in the recovery process -->
        <%session.setAttribute("step", "0");%>
    </head>
    
    <body style="background:darkgray">
        <table align="center" style="width:600px;">
            <tr>
                <!-- Using the custom tag that was imported in the taglib tag -->
                <td valign="top">
                    <ban:BannerTag/>
                </td>
                <td valign="top">
                    <form action="LoginServlet" method="post">
                        <% 
                            /*
                            *Creating the string variables to check if any errors
                            *have occured, and display them if so. By using 
                            *parameters sent thru the header fields from the other
                            *pages and servlets
                             */
                            String error = request.getParameter("error") == null ? "" : "Error on username or password, please try again"; 
                            String logged = request.getParameter("logged") == null ? "" : "You arent logged in!";
                            String logoff = request.getParameter("logoff") == null ? "" : request.getParameter("logoff");
                            /*
                            *If statment to check if the user had clicked the
                            *logoff button on the login page, which then end the 
                            *session by removing the attribute linked to the user 
                            *that logged in.
                             */
                            if(logoff.equals("true")){
                               session.removeAttribute("user");
                            }%>
                                
                        <!-- Creating the simple form for users
                             to log into the website as well as see other
                             options if they arent users
                             or have forgotten their passwords-->
                        <table style="color:red;font-weight:bold;border-style:solid;border-width:thin;color:red">
                            <tr>
                                <td>
                                    Username
                                </td>
                                <td>
                                    <input type="text" size=18 maxlength=15 name="username"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Password
                                </td>
                                <td>
                                    <input type="password" size=18 maxlength=15 name="password"/>
                                </td>
                            </tr>
                            <tr><td colspan="2"><%out.println(error + " " + logged);%></td></tr>
                            <tr>
                                <td>
                                    <input type="submit" value="Login"/>
                                </td>
                                <td>
                                    Forgot password ? <a href="remember.jsp" style="text-decoration: none" title="Click here we'll help you remember !">Click to recover</a>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    Not signed up ? <a href="register.jsp" style="text-decoration: none" title="Not a user ? Sign up today! Dont hesitate">Register Here</a>
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>
            <tr>
                <td colspan="2" valign="top"></td>
            </tr>
            <!-- A small paragraph of text to tell the user
                 what this website is all about-->
            <tr>
                <td colspan="2" valign="top" align="center">
                    <table style="border-style:solid;border-width:thin;color:red">
                        <tr>
                            <td>
                                <h3 style="font-size:16pt;color:dodgerblue" >
                                    About CrossFire &trade;
                                </h3>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p style="color:black; font-size:14pt">
                                    So CrossFire .... 
                                    
                                    <b><u>What is CrossFire?</u></b>
                                    <hr style="color:dodgerblue"/>
                                    CrossFire is essentially a forum based web site, that allow users to <br/>
                                    post about questions that they have which they, themselves, cannot answer.<br/>
                                    So the topics will be put up for discussion, and other users, can log in<br/>
                                    and assist you with these topics.<br/>
                                    
                                    <hr style="color:dodgerblue"/>
                                    The meaning behind "So who's gonna get that headshot?" is simple !<br/>
                                    Basically, when trying to answer a topic, during the process of<br/>
                                    replying and commenting, one can be seen as "firing" bullets at the <br/>
                                    target(which is the topic at hand). And the person to actually solve the<br/>
                                    discussion with have in turn "killed the topic", which in essence can be seen<br/>
                                    as a Headshot!<br/><br/>
                                    
                                    
                                    
                                    <hr style="color:dodgerblue"/>
                                    So .... Do you have what it takes to get some Headshots !?
                                </p>
                            </td>
                        </tr>
                    </table>
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
