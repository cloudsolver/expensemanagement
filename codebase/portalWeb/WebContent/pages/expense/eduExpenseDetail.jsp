<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>

<tiles:insert page="/common/layout/layout02.jsp" flush="true">

  <tiles:put name="header" value="/expense/tiles/header.jsp" />
  <tiles:put name="title" type="string">
  <bean:message key="expense.lineitem.edu.title"/>
  </tiles:put>
  <tiles:put name="main" type="string">
	<bean:message key="expense.lineitem.edu.help"/>
		<html:form styleId="mainform" action="/expense/edu/detail">
		<table class="form">
		<html:hidden styleId="cmd" property="cmd" value="create"/>
		<th colspan="4"><bean:message key="expense.lineitem.edu.title"/></th>
		<tr>
			<td><bean:message key="expense.lineitem.edu.startDate"/></td>
			<td><html:text styleId="startDate" property="startDate"/>
			<button type="button" id="startDateTrigger">...</button><script type="text/javascript">Calendar.setup({inputField:"startDate",ifFormat:"%b %e, %Y",button:"startDateTrigger",align:"Tr"});</script></td>
			<td><bean:message key="expense.lineitem.edu.endDate"/></td>
			<td><html:text styleId="endDate" property="endDate"/>	<button type="button" id="endDateTrigger">...</button>	<script type="text/javascript">Calendar.setup({inputField:"endDate",ifFormat:"%b %e, %Y",button:"endDateTrigger",align:"Tr"});</script></td>
		</tr>
		<tr>
			<td><bean:message key="expense.lineitem.edu.transactionDate"/></td>
			<td><html:text styleId="transactionDate" property="transactionDate"/><button type="button" id="transactionDateTrigger">...</button><script type="text/javascript">Calendar.setup({inputField:"transactionDate",ifFormat:"%b %e, %Y",button:"transactionDateTrigger",align:"Tr"});</script></td>
			<td><bean:message key="expense.lineitem.edu.transactionAmount"/></td>
			<td><html:text property="transactionAmount"/></td>
		</tr>
		<tr>
			<td><bean:message key="expense.lineitem.edu.schoolName"/></td>
			<td><html:text property="schoolName"/></td><td>Course Name</td>
			<td><html:text property="courseName"/></td>
		</tr>
		<tr> 
			<td><bean:message key="expense.lineitem.edu.grade"/></td>
			<td><html:select property="grade"><html:options collection="grades" labelProperty="label" property="value"/></html:select></td>
			<td><bean:message key="expense.lineitem.edu.level"/></td>
			<td><html:select property="educationLevel"><html:options collection="educationLevels" labelProperty="label" property="value"/></html:select></td>
		</tr>
		<tr>
			<td><bean:message key="expense.lineitem.edu.qualified"/></td>
			<td><html:select property="qualified"><html:options collection="flags" labelProperty="label" property="value"/></html:select></td>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td><bean:message key="expense.lineitem.edu.transactionJustification"/></td><td colspan="3"><html:textarea property="transactionJustification" rows="5" cols="51"/></td>
		</tr>
		<tr>
			<td colspan="4">
			<logic:notPresent name="ctx" property="expenseLineItemId"><button type="button" onclick="submitForm('mainform','create')">Add</button></logic:notPresent>
			<logic:present name="ctx" property="expenseLineItemId"><button type="button" onclick="submitForm('mainform','update')">Update</button></logic:present>
			<button type="button" onclick="submitForm('mainform','back')" >Cancel</button>
			<logic:present name="ctx" property="expenseLineItemId">
			<button type="button" onclick="submitForm('mainform','delete')">Delete</button>
			</logic:present>
			</td>
		</tr>
		</table>
		</html:form>		
  </tiles:put> 
  <tiles:put name="left" value="/expense/tiles/left.jsp" />
  <tiles:put name="footer" value="/common/footer.jsp" />
</tiles:insert>