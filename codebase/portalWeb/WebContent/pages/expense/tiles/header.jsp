<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
		
<div id="expenseheader">
	<button  type="button" onclick='location.href="/mst/expense/home.do?cmd=read"'>My Expense Reports</button>
		<logic:present name="ctx" property="userName">
			<button  type="button" onclick='location.href="/mst/home.do?cmd=read"'>Home</button>
			<button  type="button" onclick='location.href="/mst/customer/logoff.do"'>Sign Out</button>
		</logic:present>
</div>