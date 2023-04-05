<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<logic:present name="ctx" property="userName">
		<div>
			<a href="/mst/expense/general.do?cmd=read">[New Expense]</a>
		</div>
</logic:present>
		