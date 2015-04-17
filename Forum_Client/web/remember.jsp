<!--
-- Author                   Tejas Dwarkaram
-- FileName                 remember.jsp
-- Date                     2012/11/01

-- File Description         JSP File that will use the "step" session
                            and be used to help the user recover his
                            password by sending him/her their password 
                            via an email
-->

<%-- Creating the taglib tag to import the custom tag used for the banner --%>
<%@taglib uri="//WEB-INF/tlds/LogoFormatTag.tld" prefix="ban" %>
<html>
    
    <head>
        <title>CrossFire&trade; Password Recovery</title>
    </head>
    
    <body style="background:darkgray">
        <table align="center" style="width:600px;">
            <%
            //Creating the String variable to store which step the user is currently on
            String step = request.getParameter("step") == null ? "0" : request.getParameter("step");
            %>
            <tr>
                <!-- Using the custom tag that was imported in the taglib tag -->
                <td valign="top" colspan="2">
                    <ban:BannerTag/>
                </td>
            </tr>
            <tr>
                <td colspan="2" valign="top" align="center">
                    <h3 style="font-size:15pt">
                        <u>Password Recovery assistant</u>
                    </h3>
                    <b>
                        <% //checking the contents of the step variable
                        if(step.equals("0") || step.equals("1")){
                            session.setAttribute("step", "1");
                        %>
                        <form action="RecoveryServlet" method="post"> 
                            <fieldset style="align:top;width: 200px;" >
                                <legend style="align:left;color: black; font-size:14pt">Verify Email Address</legend>
                                <table style="color:red;">
                                    <tr>
                                        <td>
                                            <% 
                                            if(step.equals("1")){
                                            %>
                                            Ooops ! Please try that 1 more time..
                                            <%
                                            }
                                            %>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size:12pt">
                                            Your user name
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="text" size=18 maxlength=24 name="name"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size:12pt">
                                            Your email address
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="text" size=18 maxlength=24 name="email"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="submit" value="Verify"/>
                                        </td>
                                    </tr>        
                                </table>
                            </fieldset>
                        </form>
                        <%
                        }else if(step.equals("2")){
                        session.setAttribute("step", "2");
                        %>
                        <form action="RecoveryServlet" method="post"> 
                            <fieldset style="align:top;width: 200px;" >
                                <legend style="align:left;color: black; font-size:14pt">Security Question</legend>
                                <table style="color:red;">
                                    <tr>
                                        <td style="font-size:12pt">
                                            <b><u>Your Security Question is</u></b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <% 
                                            out.println(request.getParameter("que"));
                                            %>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="font-size:12pt">
                                            Please enter your answer
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="text" size=18 maxlength=24 name="answer"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="submit" value="Send"/>
                                        </td>
                                    </tr>        
                                </table>
                            </fieldset>
                        </form>
                        <%
                        /*
                         *checking if the step is on 3 which means the email
                         *was sent successfully
                         */
                        }else if(step.equals("3")){
                        %>
                        <form  method="post"> 
                            <fieldset style="align:top;width: 200px;" >
                                <legend style="align:left;color: black; font-size:14pt">Success!!</legend>
                                <table style="color:red;">
                                    <tr>
                                        <td style="font-size:12pt">
                                            Your password has been successfully recovered !<br/>
                                            An email has been sent to your email address <br/>
                                            Your password can be changed on next login to <br/>
                                            further forgetfulness<br/>
                                            <a href="index.jsp"> Back to home page for login </a>
                                        </td>
                                    </tr>        
                                </table>
                            </fieldset>
                        </form>
                        <%}%>
                    </b>   
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