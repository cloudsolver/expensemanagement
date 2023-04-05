<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>

<tiles:insert page="/common/layout/layout02.jsp" flush="true">
<tiles:put name="title" type="string">
  	<bean:message key="expense.review.title"/>
</tiles:put>
  <tiles:put name="header" value="/expense/tiles/header.jsp" />
  <tiles:put name="main" type="string">
  	<bean:message key="expense.review.help"/>
  <div class="form">
		<table class="form">
		<html:form action="/expense/review">
		<html:hidden name="expenseForm" property="cmd" value="create"/>
		<th colspan="4">Please Review Expense Name: <b><bean:write name="expenseForm" property="name"/></b></th>
		<tr><td>Expense Purpose:</td><td><bean:write name="expenseForm" property="purpose"/></td><td>Expense Date:</td><td><bean:write name="expenseForm" property="dateDisplay"/></td></tr>
		<tr><td>Expense Amount:</td><td><bean:write name="expenseForm" property="expenseAmount"/></td><td>Expense Approver:</td><td><bean:write name="expenseForm" property="approver"/></td></tr>
		<tr><td></td><td colspan="2"><button type="button" onclick="location.href='/mst/expense/expenseLineItems.do?cmd=read'">Back</button><html:submit value="Submit Expense Report"/></td><td></td></tr>
		</html:form>
		</table>
	</div>
		</tiles:put> 
  <tiles:put name="left" value="/expense/tiles/left.jsp" />
  <tiles:put name="footer" value="/common/footer.jsp" />
</tiles:insert>