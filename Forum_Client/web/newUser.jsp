<!--
-- Author                   Tejas Dwarkaram
-- FileName                 newUser.jsp
-- Date                     2012/11/01

-- File Description         JSP File that will welcome a user
                            if all registration details were
                            correct
-->

<%-- Creating the taglib tag to import the custom tag used for the banner --%>
<%@taglib uri="//WEB-INF/tlds/LogoFormatTag.tld" prefix="ban" %>
<html>
    
    <!-- Setting the title of the page -->
    <head>
        <title>Welcome to CrossFire&trade;</title>
    </head>
    
    <body style="background:darkgray">
        <table align="center" style="width:600px;">
            <tr>
                <!-- Using the custom tag that was imported in the taglib tag -->
                <td valign="top" colspan="2">
                    <ban:BannerTag/>
                </td>
            </tr>
            <tr>
                <!-- Writing out a welcome message to the user -->
                <td valign="top" align="center" colspan="2">
                    <table style="color:red;font-weight:bold;border-style:solid;border-width:thin;color:red">
                        <tr>
                            <td>
                                Congratulations !
                                Welcome to CrossFire!
                                We hope we help to solve all your problems, and good luck with you getting
                                <u><big>Headshots!</big></u>
                                You will now be redirected to the Home page
                                Where you will conduct your login
                                <hr/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href='index.jsp' style='text-decoration: none'>Click here to proceed!</a>
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