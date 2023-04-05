<%@ page language="java" %>
<%@ page import="java.util.*,com.esood.expense.ui.*,com.esood.expense.ui.form.*"%>

<body onload="document.mainForm.submit()">
<form name="mainForm" action="/mst/creditCardExpense.do">
<input type="hidden" name="cmd" value="read"/>
</form>
Processing...<img src="aquaprogressbar.gif"/>
</body>