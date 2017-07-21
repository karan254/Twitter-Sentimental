
<%@page import="java.util.ArrayList"%>
<%
    String txtsearch = request.getParameter("txtsearch");
    
    DB.GetTwitterData.authenticate(txtsearch);
    DB.GetTwitterData.classify(txtsearch);
    int posiive = DB.GetTwitterData.getPositiveTweet();
    int negative = DB.GetTwitterData.getNegativeTweet();
    int neutral = DB.GetTwitterData.getNeutralTweet();
    ArrayList<String> lstTweets = DB.GetTwitterData.getTweet();
    ArrayList<String> lstResponse = DB.GetTwitterData.getResponse();
    session.setAttribute("positive", posiive);
    session.setAttribute("negative", negative);
    session.setAttribute("neutral", neutral);
    session.setAttribute("tweets", lstTweets);
    session.setAttribute("response", lstResponse);
    response.sendRedirect("index.jsp");
%>