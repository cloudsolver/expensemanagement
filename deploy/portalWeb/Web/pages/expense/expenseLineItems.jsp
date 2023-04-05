<%@ page language="java" %>
<%@ page import="com.esood.expense.ui.form.*, java.util.*" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>

<tiles:insert page="/common/layout/layout02.jsp" flush="true">
<tiles:put name="title" type="string">
	<bean:message key="expense.items.title"/>
</tiles:put>
  <tiles:put name="header" value="/expense/tiles/header.jsp" />
  <tiles:put name="main" type="string">
<bean:message key="expense.items.help"/>
  <div class="form">
  		<%
		  		com.esood.ui.Context ctx=(com.esood.ui.Context) session.getAttribute(com.esood.ui.Context.NAME);
  		  		Integer expenseId=ctx.getExpenseId();
  		%>

		<table class="form">
		<html:form action="/expense/expenseLineItems">
		<html:hidden property="cmd" value="create"/>
		<tr>
		<th colspan="6" align="center"><bean:message key="expense.items.title"/> for Expense ID: <b><%=expenseId%></b></th>
		</tr>
		<tr>
		<th>#</th>
		<th><bean:message key="expense.lineitem.std.transactionDate"/></th>
		<th><bean:message key="expense.lineitem.std.transactionAmount"/></th>
		<th>Type</th>
		<th ><bean:message key="expense.lineitem.std.transactionJustification"/></th>
		<th >Action</th>
		</tr>
		<%
			List lineItems=(List)request.getAttribute("expenseLineItems");
		  	//Color initializer
  			boolean cs=false;
		  	String bgcolor="#EEEEEE";
		  			
		for(int a=0; a<lineItems.size();a++){
			ExpenseLineItem eli=(ExpenseLineItem) lineItems.get(a);
				if(true==cs){
			  			bgcolor="#FFFFFF";
			  		}else
			  		{
			  			bgcolor="#EEEEEE";
			  		}
			  	cs=!cs;
		%>
		<tr bgcolor="<%=bgcolor%>">
			<td ><%=(1+a)%></td>
			<td ><%=eli.getTransactionDate()%></td>
			<td >$<%=eli.getTransactionAmount()%></td>
			<td ><%=eli.getLineItemTypeDisplay()%></td>
			<td ><%=eli.getTransactionJustification()%></td>
			<td >
			
			<%if(null!=eli.getLineItemType() && "e".equals(eli.getLineItemType())){%>
			<button type="button"  onClick='location.href=<%="\"/mst/expense/edu/detail.do?id="+eli.getId()+"&cmd=read\""%>;'>View/Update</button>
			<%}%>			
			<%if(null!=eli.getLineItemType() && "s".equals(eli.getLineItemType())){%>
			<button type="button"  onClick='location.href=<%="\"/mst/expense/std/detail.do?id="+eli.getId()+"&cmd=read\""%>;'>View/Update</button>
			<%}%>
			</td>
		</tr>
		<%}%>
		<tr>
		<td colspan="6">
		<button type="button" onclick='location.href="/mst/expense/general.do?cmd=read&id=<%=expenseId%>" '>Back</button>
		<button type="button" onclick='location.href="/mst/expense/edu/detail.do?cmd=read"'>Add Education Line Item</button>
		<button type="button" onclick='location.href="/mst/expense/std/detail.do?cmd=read"'>Add Standard Line Item</button>
		<html:submit value="Next"/>
		</td>
		</tr>
		</html:form>
		</table>
	</div>
		</tiles:put> 
  <tiles:put name="left" value="/expense/tiles/left.jsp" />
  <tiles:put name="footer" value="/common/footer.jsp" />
</tiles:insert>