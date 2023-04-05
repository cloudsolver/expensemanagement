<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>

<tiles:insert page="/common/layout/layout02.jsp" flush="true">
<tiles:put name="title" value="Thank You"/>
  <tiles:put name="header" value="/common/header.jsp" />
  <tiles:put name="main" type="string">
  	<div id="form">
  		<center><h4><bean:message key="survey.success"></bean:message></h4></center>
  	</div>
  </tiles:put> 
  <tiles:put name="left" value="/common/left.jsp" />
  <tiles:put name="footer" value="/common/footer.jsp" />
</tiles:insert>