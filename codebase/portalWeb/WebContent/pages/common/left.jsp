<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
		
	
		<logic:present name="ctx" property="userName">
		<div id="inner">
		<h3>Tasks</h3>
		<br>
		
		<a href="/mst/expense/home.do?type=A">Intranet Portal ver 1</a><br>
		<a href="/mst/expense/home.do?type=B">Intranet Portal ver 2</a><br>
		<a href="/mst/expense/home.do?type=C">Intranet Portal ver 3</a><br>


		</div>
		</logic:present>
		