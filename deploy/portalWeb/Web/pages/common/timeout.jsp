<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>

<tiles:insert page="/common/layout/layout02.jsp" flush="true">
  <tiles:put name="title" value="Session Timeout"/>
  <tiles:put name="header" value="/common/header.jsp" />
  <tiles:put name="main" type="string">
	
		<table class="form">
		<tr class="form">
		<td class="form">
		<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
			<p class="error">ERROR:  Application resources not loaded -- check servlet container logs for error messages.
			</p>
		</logic:notPresent>
		
		<logic:notPresent name="ctx" property="userName">
		<p><bean:message key="index.message.logged.off"/></p>
		</logic:notPresent>
		
		</td>
		</tr>
		</table>

  </tiles:put>
    <tiles:put name="left" value="/common/left.jsp" />
  <tiles:put name="footer" value="/common/footer.jsp" />
</tiles:insert>