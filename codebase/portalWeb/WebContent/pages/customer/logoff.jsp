<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/common/layout/layout02.jsp" flush="true">
<tiles:put name="title" value="Log off"/>
  <tiles:put name="header" value="/common/header.jsp" />
  <tiles:put name="main" type="string">
  	<div id="form">
  		<h4>Log off</h4>
  			<span>You have successfully logged off</span>
  	</div>
  </tiles:put> 
  <tiles:put name="left" value="/common/left.jsp" />
  <tiles:put name="footer" value="/common/footer.jsp" />
</tiles:insert>