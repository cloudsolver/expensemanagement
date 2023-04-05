<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"   %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"   %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
<head>
	<title>
		Intranet Portal - <tiles:getAsString name="title"/>
	</title>
	<link rel="stylesheet" type="text/css" href="/mst/common/css/layout02.css">
	</link>
	<link rel="stylesheet" type="text/css" href="/mst/common/css/style02.css">
	</link>
	<link rel="stylesheet" type="text/css" href="/mst/common/css/calendar.css">
	</link>
	<script type="text/javascript" src="/mst/common/script/common.js">
	</script>
	<script type="text/javascript" src="/mst/common/script/ajax.js">
	</script>
	<!--Calendar -->
	<script type="text/javascript" src="/mst/common/script/calendar.js">
	</script>
	<script type="text/javascript" src="/mst/common/script/calendar-en.js">
	</script>
	<script type="text/javascript" src="/mst/common/script/calendar-setup.js">
	</script>
</head>
<body>
<!-- HEADER-->
<div id="header">
	<tiles:insert attribute="header"/>
</div>
<!-- CENTER-->
<div id="center" align="left">
	<!-- CONTENT -->
	<div id="content" align="left">
		<logic:messagesPresent>
		<div id="error">
			<ul>
			<html:errors/>
			</ul>
		</div>
		</logic:messagesPresent>
		<tiles:insert attribute="main"/>
	</div> 
</div>
<!-- FOOTER -->
<div id="footer" align="center">
	<tiles:insert attribute="footer"/>
</div>
	<!-- LEFT -->
	<div id="left" >
		<tiles:insert attribute="left"/>
	</div>

</body>
</html:html>