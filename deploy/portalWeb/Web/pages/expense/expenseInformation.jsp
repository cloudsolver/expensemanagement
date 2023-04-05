<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>

<tiles:insert page="/common/layout/layout02.jsp" flush="true">
<tiles:put name="title" value="Enter Expense Information"/>
  <tiles:put name="header" value="/expense/tiles/header.jsp" />
  <tiles:put name="main" type="string">
 <bean:message key="expense.general.help"/>
  <div class="form">

		<table class="form">
		<html:form action="/expense/general">
		<html:hidden property="cmd" value="create"/>
		<html:hidden property="page" value="1"/>
		<th colspan="2" ><bean:message key="expense.general.title"/></th>
		<tr>
		<td><bean:message key="expense.general.costCenter"/></td>
		<td><html:text property="costCenter"/></td>
		</tr>
		<tr>
		<td><bean:message key="expense.general.name"/></td>
		<td><html:text property="name"/></td>
		</tr>
		<tr>
		<td><bean:message key="expense.general.purpose"/></td>
		<td><html:text property="purpose"/></td>
		</tr>
		<tr>
		<td><bean:message key="expense.general.dateDisplay"/></td>
		
		<td><html:text styleId="dateDisplay" property="dateDisplay"/>
		<button type="button" id="trigger">...</button>
		<script type="text/javascript">Calendar.setup({inputField:"dateDisplay",ifFormat:"%b %e, %Y",button:"trigger",align:"Tr"});</script>
		</td>
		</tr>
		
		<tr>
		<td><bean:message key="expense.general.approver"/></td>
		<td><html:text property="approver" /></td>
		</tr>
		<tr>
		<td>
		<button type="button" onclick='location.href="/mst/expense/home.do?cmd=read"'>Cancel</button>
		<html:submit value="Next"/>
		<td>
		</tr>
		</html:form>
		</table>
	</div>
		</tiles:put> 
  <tiles:put name="left" value="/expense/tiles/left.jsp" />
  <tiles:put name="footer" value="/common/footer.jsp" />
</tiles:insert>