<!--
-- Author                   Tejas Dwarkaram
-- FileName                 register.jsp
-- Date                     2012/11/01

-- File Description         JSP File that will allow the user to
                            easily input their personal information
                            and complete the user registration process
-->

<%-- Creating the taglib tag to import the custom tag used for the banner --%>
<%@taglib uri="//WEB-INF/tlds/LogoFormatTag.tld" prefix="ban" %>
<html>
    
    <head>
        <title>Sign up for CrossFire&trade;</title>
    </head>
    
    <body style="background:darkgray">
        <%
        /*
        *Creating string variables to hold values if
        *any errors occur, which will be seen in the
        *header field
        */
        String invalid = "";
        String nameError = "";
        String surnameError = "";
        String monthError = "";
        String febError = "";
        String lengthError = "";
        String matchError = "";
        String emailError = "";
        String answerError = "";
        //checking if the header field for parameters that will show success, errors or email exists
        String errors = request.getParameter("errors") == null ? "" : request.getParameter("errors");
        String exists = request.getParameter("exist") == null ? "" : "The email address has been registered before!";
        
        /*
        *checking the errors variable, which checks if the header for
        *that parameter contains numbers in correspondence to the error
        *occured in which field
         */
        if(errors.contains("0")){
            invalid = "Invalid charaters have been used";
        }
        if(errors.contains("1")){
            nameError = "Error on your name";
        }
        if(errors.contains("2")){
            surnameError = "Error on your surname";
        }
        if(errors.contains("3")){
            monthError = "This month has only 30 days";
        }
        if(errors.contains("4")){
            febError = "February has 28 days";
        }
        if(errors.contains("5")){
            lengthError = "Password is to short";
        }
        if(errors.contains("6")){
            matchError = "Passwords dont match";
        }
        if(errors.contains("7")){
            emailError = "Error in your email";
        }
        if(errors.contains("8")){
            answerError = "Answer cant contain errors";
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
                    <h1 style="color:dodgerblue">
                        <u>Registration CrossFire&trade;</u>
                    </h1>
                     <!-- Printing out an error if the email address is in use already -->
                    <h2 style="color:red"><%out.println(exists);%></h2>
                    <form action="RegisterServlet" method="post" style="font-size:15">
                        <b>
                            <table style="font-size:15pt">
                                <tr>
                                    <td></td>
                                    <!-- Printing out an error if there are unknown characters present-->
                                    <td style="font-size:12pt; color:dodgerblue;"><%out.println(invalid);%></td>
                                </tr>
                                <tr>
                                    <td>Name:</td>
                                    <td><input type="text" name="name"/></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <!-- Printing out an a message if  there is an error -->
                                    <td style="font-size:12pt; color:dodgerblue;"><%out.println(nameError);%>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Surame:</td>
                                    <td><input type="text" name="surname"/></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <!-- Printing out an a message if  there is an error -->
                                    <td style="font-size:12pt; color:dodgerblue;"><%out.println(surnameError);%>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Date of birth:</td>
                                    <td>
                                        <select name="day">
                                            <%
                                            for(int x=1 ;  x<32 ; x++){
                                        out.print("<option value='" + x + "'>" + x + "</option>");
                                            }
                                            %>
                                        </select>
                                        <select name="month">
                                            <option value="01">January</option>
                                            <option value="02">Febuary</option>
                                            <option value="03">March</option>
                                            <option value="04">April</option>
                                            <option value="05">May</option>
                                            <option value="06">June</option>
                                            <option value="07">July</option>
                                            <option value="08">August</option>
                                            <option value="09">September</option>
                                            <option value="10">October</option>
                                            <option value="11">November</option>
                                            <option value="12">December</option>
                                        </select>
                                        <select name="year">
                                            <%
                                            for(int y = 1900 ; y <2012 ; y++){
                                        out.print("<option value='" + y + "'>" + y + "</option>");
                                            }
                                            %>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <!-- Printing out an a message if  there is an error -->
                                    <td style="font-size:12pt; color:dodgerblue;"><%out.println(monthError);%></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <!-- Printing out an a message if  there is an error -->
                                    <td style="font-size:12pt; color:dodgerblue;"><%out.println(febError);%></td>
                                </tr>
                                <tr>
                                    <td>Password:</td>
                                    <td><input type="password" name="pwd"/></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <!-- Printing out an a message if  there is an error -->
                                    <td style="font-size:12pt; color:dodgerblue;"><%out.println(lengthError);%></td>
                                </tr>
                                <tr>
                                    <td>Confirm Password:</td>
                                    <td><input type="password" name="cnpwd"/></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <!-- Printing out an a message if  there is an error -->
                                    <td style="font-size:12pt; color:dodgerblue;"><%out.println(matchError);%></td>
                                </tr>
                                <tr>
                                    <td>Email Address:</td>
                                    <td><input type="text" name="email"/></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <!-- Printing out an a message if  there is an error -->
                                    <td style="font-size:12pt; color:dodgerblue;"><%out.println(emailError);%></td>
                                </tr>
                                <tr>
                                    <td>Security Question</td>
                                    <td>
                                        <select name="secq">
                                            <option value="pet">What is your first pets name?</option>
                                            <option value="mum">What is your mums name?</option>
                                            <option value="teacher">What is your first teachers name?</option>
                                            <option value="town">What is your home towns name?</option>
                                            <option value="phone">What is the name of your fav phone?</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>What is the answer to this question ?</td>
                                    <td><input type="text" name="seca"/></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <!-- Printing out an a message if there is an error -->
                                    <td style="font-size:12pt; color:dodgerblue;"><%out.println(answerError);%></td>
                                </tr>
                                <tr>
                                    <td style="font-size:14pt">
                                        <input type="submit" value="Sign Up"/>
                                        <input type="reset" value="Reset"/>
                                    </td>
                                    <td colspan="3">
                                        <a href="index.jsp" style="text-decoration:none; font-size:14pt;">Back to index page</a>
                                    </td>
                                </tr>
                            </table>
                            <hr style="color:dodgerblue" width="550px"/>
                            <hr style="color:red" width="550px"/>
                        </b>
                    </form>
                </td>
            </tr>            
            <!-- A simple footer -->
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