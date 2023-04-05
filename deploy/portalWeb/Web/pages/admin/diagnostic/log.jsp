<%@ page import="org.apache.commons.logging.*"%>
<h4>Log</h4><br>

<%
Log log=LogFactory.getLog("test");
log.info("test");
System.out.println("Printing out");
%>