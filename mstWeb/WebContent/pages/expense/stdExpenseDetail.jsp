<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>

<tiles:insert page="/common/layout/layout02.jsp" flush="true">
  <tiles:put name="header" value="/expense/tiles/header.jsp" />
  <tiles:put name="title" type="string">
  <bean:message key="expense.lineitem.std.title"/>
  </tiles:put>
  <tiles:put name="main" type="string">
		<html:form styleId="mainform" action="/expense/std/detail">
		<table class="form">	
		<html:hidden styleId="cmd" property="cmd" value="create"/>
		<th colspan="4"><center><bean:message key="expense.lineitem.std.title"/></center></th>
		<tr>
		<td><bean:message key="expense.lineitem.std.transactionDate"/></td><td><html:text styleId="transactionDate" property="transactionDate"/><button type="button" id="trigger">...</button>	<script type="text/javascript">Calendar.setup({inputField:"transactionDate",ifFormat:"%b %e, %Y",button:"trigger",align:"Tr"});</script></td>
		<td><bean:message key="expense.lineitem.std.transactionAmount"/></td><td><html:text property="transactionAmount"/></td></tr>
		<tr>
		<td><bean:message key="expense.lineitem.std.transactionType"/></td><td><html:select property="transactionType"><html:options collection="transactionTypes" labelProperty="label" property="value"/></html:select></td><td>Qualified Work Related</td><td><html:select property="qualified"><html:options collection="flags" labelProperty="label" property="value"/></html:select></td></tr>
		<tr><td><bean:message key="expense.lineitem.std.transactionJustification"/></td><td colspan="3"><html:textarea property="transactionJustification" rows="5" cols="51"/></td></tr>
		<tr><td colspan="4">
		<logic:notPresent name="ctx" property="expenseLineItemId"><button type="button" onclick="submitForm('mainform','create')">Add</button></logic:notPresent>
		<logic:present name="ctx" property="expenseLineItemId"><button type="button" onclick="submitForm('mainform','update')">Update</button></logic:present>
		<button type="button" onclick="submitForm('mainform','back')" >Cancel</button>
		<logic:present name="ctx" property="expenseLineItemId">
			<button type="button" onclick="submitForm('mainform','delete')">Delete</button>
		</logic:present>
		</td></tr>
		</table>
		</html:form>		
  </tiles:put> 
  <tiles:put name="left" value="/expense/tiles/left.jsp" />
  <tiles:put name="footer" value="/common/footer.jsp" />
</tiles:insert>