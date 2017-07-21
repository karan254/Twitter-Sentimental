

<%@page import="java.util.ArrayList"%>
<%-- 
    Document   : index
    Created on : Feb 19, 2016, 4:49:01 PM
    Author     : DKG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Twitter Data Sentimental Analysis</title>
        <link rel="stylesheet" type="text/css"  href="css/style.css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css">
         <script src="http://code.jquery.com/jquery-latest.js">   
        </script>
                
    </head>

    <body>
        <center> <div>

                <h1 style="color:white ">Data Analysis</h1>
                <center><div style="margin-top: 100px">
                        <table>
                            <tr>
                                <td>
                            <left> <p>Text to Analyze</p>
                                <form name="stSearch" action="analyze.jsp" method="post">

                                    <%
                                        try {

                                            if (session.getAttribute("data") != null) {
                                                out.println("<textarea  name='txtsearch' placeHolder='Search' rows='5' cols='25'>" + session.getAttribute("data") + "</textarea>");
                                            } else {
                                                out.println("<textarea  name='txtsearch' placeHolder='Search' rows='5' cols='25'></textarea>");

                                            }

                                        } catch (Exception e) {
                                        }

                                    %>

                                    <br>
                                    <button type="submit">Submit</button>
                                </form>
                                </td>
                                </tr>
                                <%                                    try {

                                        if (session.getAttribute("data") != null) {
                                            out.println("<tr><td>Classification: " + session.getAttribute("result") + "</tr></td>");

                                        }

                                    } catch (Exception e) {
                                    }
                                    session.setAttribute("data", null);
                                    session.setAttribute("result", null);
                                %>
                                <tr>
                                    <td>
                                        <a href="index.jsp" target="new">Twitter Data Analysis</a>
                                    </td>
                                </tr>


                        </table>


                    </div>









            </div>

    </body>
</html>
