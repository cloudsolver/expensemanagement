<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>

<tiles:insert page="/common/layout/layout02.jsp" flush="true">
<tiles:put name="title" value="Admin Home"/>
  <tiles:put name="header" value="/common/header.jsp" />
  <tiles:put name="main" type="string">
 
<div id="form">
	<div id="error"><html:errors/></div>
	<table class="form">
		<center><h3>Admin Home</h3></center>
		
	</table>
</div>
  </tiles:put> 
  <tiles:put name="left" type="string">
  	<div id="inner"/>
  	<a href="#">Reset Password</a>
  	<a href="#">Search Users</a>
  	<a href="./diagnostic/index.html">Diagnostic</a>
  	</div>
  </tiles:put>
  <tiles:put name="footer" value="/common/footer.jsp" />
</tiles:insert>