<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>

<tiles:insert page="/common/layout/layout02.jsp" flush="true">
<tiles:put name="title" value="Login"/>
  <tiles:put name="header" value="/common/header.jsp" />
  <tiles:put name="main" type="string">
	<div id="form">
		<table class="form">
				<html:form action="/customer/login">
					<html:hidden property="cmd" value="process"/>
					<tr><td colspan="2" ><h3>Please sign-in</h3></td></tr>
					<tr><td>Username</td><td><html:text property="username" styleId="form"/></td></tr>
					<tr><td>Password</td><td><html:password styleId="form" property="password"/></td></tr>			
					<tr><td align="right" colspan="2"><html:submit styleId="button" value="Sign In" /></td></tr>
				</html:form>
		</table>
	</div>
  </tiles:put> 
  <tiles:put name="left" value="/common/left.jsp" />
  <tiles:put name="footer" value="/common/footer.jsp" />
</tiles:insert>