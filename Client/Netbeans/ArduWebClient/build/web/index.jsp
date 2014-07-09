<%@page import="com.arduweb.client.Client"%>
<%
    Client client;
    client = new Client();
    client.run();
%>
<%=client.getMessage()%>