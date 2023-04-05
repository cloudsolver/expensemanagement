<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>

<html>
<head>
	<title>Session objects</title>
</head>

<BODY>
<div id="title">Session Object List JSP</div>
<br>

This JSP will dump information about the current HTTPSession.<br>
<%@ page import="java.io.*,java.util.*,javax.servlet.*" session="false" %>
<%!
public final static int FACTOR=1024;
%>

<%java.net.InetAddress addr = java.net.InetAddress.getLocalHost();
out.println("Hostname is " + addr.getHostName() + "<br><br>");%>


<%!
public void dumpSession(HttpServletRequest request, JspWriter out) throws IOException {

	HttpSession session = request.getSession(false);
	boolean convert=true;
	String convertString=request.getParameter("convert");

	if(convertString!=null && convertString.equalsIgnoreCase("false")){
	convert=false;
	convertString=null;
	}
        Object ro = null ;
	out.println("Session ID from session.getID : "
	      + session.getId() + "<br>");
	out.println("Session ID from getHeader: "
	      + request.getHeader("Cookie") + "<br>");

 	Enumeration enumz = session.getAttributeNames();
        if ( enumz.hasMoreElements() )
        {
            int totalSize = 0;

            out.println("<h3>Session Objects:</h3>");
            out.println("<TABLE Border=\"1\" bordercolor='black' WIDTH=\"65%\" BGCOLOR=\"#DDDDDD\">");
			out.println("<tr><td><b>Name</b></td><td><b>Object.toString()</b></td>");
			out.println("<!--<td>Raw Bytes</td>--><td><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Size&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b></td></tr>");

            while ( enumz.hasMoreElements()){

				String name = (String)enumz.nextElement();

				Object sesobj = session.getAttribute(name) ;


				ObjectOutputStream oos = null;
	            ByteArrayOutputStream bstream = new ByteArrayOutputStream();

				try {
					oos = new ObjectOutputStream(bstream);
					oos.writeObject(sesobj);
					}catch (Exception e) {
							e.printStackTrace();
					}//end catch
					finally {
						if (oos != null) {
							try {oos.flush();}
								catch (IOException ioe) {}
							try {oos.close();}
								catch (IOException ioe) {}
					}//end finally
			}//end while

			ObjectInputStream ois = null;
			ro = null ;

			try {
				ois = new ObjectInputStream(new ByteArrayInputStream(bstream.toByteArray()));
				ro = ois.readObject();
				}catch (Exception e) {
						e.printStackTrace();
						}
				finally {
						if (ois != null) {
						try {
							ois.close();
							}catch (IOException ioe) {}//end catch
				}//end finally
		}//end if

	      totalSize += bstream.size();
                out.println("<tr><td>" + name + "</td><td>" + session.getAttribute(name) + 	"</td><!--<td>" + ro + "</td>-->");
	if(bstream.size()>FACTOR&&convert){
	     out.println("<td>" + (bstream.size()/FACTOR) + " KB </td></tr>");
		}else{
	      out.println("<td>" + bstream.size() + " bytes </td></tr>");
	      }
            }
            out.println("</table><BR>");
        if(convert&&totalSize>(FACTOR*FACTOR)){
        out.println("<b>Total : </b>" + (totalSize/(FACTOR*FACTOR)) + "<font color='red'><b>MB</b></font><br><br>");
        }
        else if(totalSize>FACTOR&&convert){
			out.println("<b>Total : " + (totalSize/(FACTOR)) + " KB</b><br><br>");

        }else{
		out.println("<b>Total : " + totalSize + "</b>bytes<br><br>");
		}

        } else {
		out.println("No objects in session");
}
  }
  %>

<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires",0);

    HttpSession session = request.getSession(false);
    if (session == null) {
	    out.println("No session");
    }  else {
	    dumpSession(request, out);
    }
%>
</BODY>
</HTML>
