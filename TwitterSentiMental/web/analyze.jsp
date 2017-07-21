
<%@page import="DB.NavieBayesClassifier"%>
<%@page import="java.util.ArrayList"%>
<%
    String txtsearch = request.getParameter("txtsearch");
    
   
    DB.NavieBayesClassifier nb=new NavieBayesClassifier();
    String output=nb.analayseData(txtsearch);
   
    session.setAttribute("data", txtsearch);
    session.setAttribute("result", output);
    response.sendRedirect("analyzeData.jsp");
%>