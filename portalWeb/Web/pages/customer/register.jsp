<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>

<tiles:insert page="/common/layout/layout02.jsp" flush="true">
<tiles:put name="title" value="Sign Up"/>
  <tiles:put name="header" value="/common/header.jsp" />
  <tiles:put name="main" type="string">
  <div class="form">
		<table class="form">
			<html:form action="/customer/register">
				<html:hidden property="cmd" value="create"/>
				<html:hidden property="page" value="1"/>
				<tr><th colspan="2">Sign Up</th></tr>
				<tr><td><bean:message key="register.username"/></td><td><html:text property="username"/></td></tr>
				<tr><td><bean:message key="register.password"/></td><td><html:password property="password"/></td></tr>			
				<tr><td><bean:message key="register.password2"/></td><td><html:password property="password2"/></td></tr>
				<tr><td colspan="2" align="right"><button type="button" onclick="location.href='/mst/home.do?cmd=read'">Back</button><html:submit value="Next"/></td></tr>
			</html:form>
		</table>
		</div>
	</tiles:put> 
  <tiles:put name="left" value="/common/left.jsp" />
  <tiles:put name="footer" value="/common/footer.jsp" />
</tiles:insert>