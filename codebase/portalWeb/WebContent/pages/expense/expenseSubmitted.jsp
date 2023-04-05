<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>

<tiles:insert page="/common/layout/layout02.jsp" flush="true">
<tiles:put name="title" type="string">
<bean:message key="expense.submitted.title"/>
</tiles:put>
  <tiles:put name="header" value="/expense/tiles/header.jsp" />
  <tiles:put name="main" type="string">
	<bean:message key="expense.submitted.help"/>
  <div id="form">
		<table class="form">
		<th>
			<bean:message key="expense.submitted.title"/>
		</th>
		<tr>
				<td><bean:message key="expense.submitted.info"/></td>
		</tr>
		<tr>
				<td><center><bean:message key="expense.submitted.button"/></center></td>
		</tr>			
		</table>
	</div>
</tiles:put> 
  <tiles:put name="left" value="/expense/tiles/left.jsp" />
  <tiles:put name="footer" value="/common/footer.jsp" />
</tiles:insert>