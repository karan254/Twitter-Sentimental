

<%@page import="java.util.ArrayList"%>


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

                <h1 style="color:orange ">Twitter Data Sentimental Analysis</h1>
                <center><div style="margin-top: 100px">
                        <table>
                            <tr>
                                <td>
                            <left> <p>Text to Search</p>
                                <form name="stSearch" action="search.jsp" method="post">

                                    <input type="text" name="txtsearch" placeHolder="Search"/>
                                    <br>
                                     <button type="submit">Submit</button>
                                </form>
                                </td>
                                </tr>
                                <tr>
                                    <td>
                                        <a href="analyzeData.jsp" target="new">Analyze Data</a>
                                    </td>
                                </tr>


                        </table>


                    </div>
                   
                    


                    <%
                     
                        try {

                            if (session.getAttribute("tweets") != null) {
                                  int positive = Integer.parseInt(session.getAttribute("positive").toString());
                            int negative = Integer.parseInt(session.getAttribute("negative").toString());
                            int neutral = Integer.parseInt(session.getAttribute("neutral").toString());
                               out.println("<script type='text/javascript' src='css/jsapi.js'>"
                                       + "</script> <script type='text/javascript'>"
                                       + "google.load('visualization', '1.0', {'packages':['corechart']});"
                                       + " google.setOnLoadCallback(drawChart); function drawChart() "
                                       + "{ var data = new google.visualization.DataTable(); data.addColumn('string', 'Topping');"
                                       + "  data.addColumn('number', 'Tweets');  data.addRows([ ['Positive',"+positive+"],['Negative', "+negative+"],['Neutral',"+neutral+"]  ]);"
                                       + "  var options = {'title':'Graph', 'width':450,'height':300,'radius':20};"
                                       + " var chart = new google.visualization.PieChart(document.getElementById('chart_div'));"
                                       + "chart.draw(data, options);}</script><div id='chart_div'></div>");

                    
                                
                                ArrayList results = (ArrayList) session.getAttribute("tweets");
                                ArrayList response1 = (ArrayList) session.getAttribute("response");
                                int count = results.size();
                                int count1 = count;
                              

                                for (int i = 0; i < count; i++) {
                                    if (i == 0) {
                                        out.print("<div><table border='1'><th>Tweet</th><th>Type</th>");
                                    }
                                    // Show title and URL of 1st result.
                                    out.print("<tr >");
                                    out.print("<td border='1'>");
                                    out.println(results.get(i));
                                    out.print("</td>");

                                    out.print("<td>");
                                    out.println(response1.get(i));

                                    out.print("</tr>");
                                    if (i == count - 1) {
                                        out.print("</table></div>");
                                        System.out.println("closed" + " i" + i + " co" + count);
                                    }
                                    //System.out.println("closed" + " i" + i + " co" + count);
                                }
                            }

                        } catch (Exception e) {
                        }

        session.setAttribute("response", null);
session.setAttribute("tweets", null);
                    %>



                   
            </div>

    </body>
</html>
