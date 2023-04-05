<%@ page language="java" %>
<%@ page session="true" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>

<tiles:insert page="/common/layout/layout02.jsp" flush="true">
  <tiles:put name="title" value="Home Page"/>
  <tiles:put name="header" value="/common/header.jsp" />
  <tiles:put name="main" type="string">
	<bean:message key="index.message.logged.on.help"/>
<div class="form">
	<table>
			<tr>
			<td>
			<tr><td><a href="/mst/doc/instructions.doc">Download Instructions</a>
			<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
				<p class="error">ERROR:  Application resources not loaded -- check servlet container logs for error messages.</p>
			</logic:notPresent>
			<logic:notPresent name="ctx" property="userName">
			<table class="form">
					<html:form action="/customer/login">
						<html:hidden property="cmd" value="process"/>
						<th colspan="2" >Sign in to Expense Portal</th>
						<tr><td><bean:message key="register.username"/></td><td><html:text property="username" /></td></tr>
						<tr><td><bean:message key="register.password"/></td><td><html:password  property="password"/></td></tr>			
						<tr><td align="right" colspan="2"><html:submit styleId="button" value="Sign In" /></td></tr>
					</html:form>
			</table>
			<bean:message key="index.message.logged.off"/>
			</logic:notPresent>
			<logic:present name="ctx" property="userName">
					<logic:present name="ctx" property="customerId">
						<p><center><bean:message key="index.welcome"/></center></p>
						<p>You are signed in as user: <bean:write name="ctx" property="userName"/>.</p>
						<p>If you are not <bean:write name="ctx" property="userName"/> please sign out.</p>
						<p>Start with Expense Portal (version 1) ,then version 2 and finally version 3. (See instruction booklet)</p>
					</logic:present>
					<p>
					<center>
						<%
									com.esood.ui.Context ctx=(com.esood.ui.Context)session.getAttribute(com.esood.ui.Context.NAME);
									Integer customerId=ctx.getCustomerId();
									if(customerId!=null){
									String[] portalType=com.esood.ui.RandomizerUtil.getPortalTypes(customerId);
									for(int a=0;a<3;a++){
						%>
						<button type="button" onclick="location.href='/mst/expense/home.do?applicationType=<%=portalType[a]%>&applicationVersion=<%=(a+1)%>'">Expense Portal (version <%=(a+1)%>)</button>
						<%
							}//end for
							}//end if
						%>
					</center>
					</p>
			</logic:present>
			<logic:present name="ctx" property="userName">
					<logic:notPresent name="ctx" property="customerId">
						<p class="error">Customer information incomplete. It seems you have not filled out the demographics form</p>
						<p>Please complete the demographics profile first. <a href="/mst/customer/profile.do?cmd=read">Click here</a> to complete the demographics information before proceeding.</p>
					</logic:notPresent>
			</logic:present>
			</td>
			</tr>
			</table>
	</div>
  </tiles:put>
    <tiles:put name="left" value="/common/left.jsp" />
  <tiles:put name="footer" value="/common/footer.jsp" />
</tiles:insert>