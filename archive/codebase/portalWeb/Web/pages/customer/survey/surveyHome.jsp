<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>

<tiles:insert page="/common/layout/layout02.jsp" flush="true">
<tiles:put name="title" value="Survey"/>
  <tiles:put name="header" value="/common/header.jsp" />
  <tiles:put name="main" type="string">
  
  <bean:message key="survey.help"/>
  <div class="form" align="center" width="100%">
<h3>Quality Survey</h3>
<bean:message key="survey.intro"/>

		<table>
		<html:form action="/customer/survey">
		<html:hidden property="cmd"  value="create"/>

			<!--Show all the questions-->
			<%
			boolean cs=false;
		  	String bgcolor="#EEEEEE";
			
			int[] random=com.esood.ui.RandomizerUtil.randomizeArray(8);
			for(int qid=0;qid<8;qid++){
			if(true==cs){
			  			bgcolor="#FFFFFF";
			  		}else
			  		{
			  			bgcolor="#EEEEEE";
			  		}
			  	cs=!cs;
			%>
			<tr bgcolor="<%=bgcolor%>">
				<td><%=(qid+1)%></td>
				<td><bean:write name="<%=com.esood.survey.ui.SurveyHelper.NAME%>" property='<%="question["+random[qid]+"]"%>' /> </td>
				<td>
					<logic:iterate id="label" name="efficiencyAnswers">
						<td class="surveyResponse"><bean:write name="label" property="label"/><html:radio property='<%="eff0"+(1+random[qid])%>' value="value" idName="label" styleClass="surveyRadio"/></td>
					</logic:iterate>
				</td>
			</tr>
			<%}%>
			<tr><td colspan="7" align="center"><html:submit value="Submit Survey"/></td></tr>
		</html:form>
		</table>
		</div>
	</tiles:put> 
  <tiles:put name="left" value="/common/left.jsp" />
  <tiles:put name="footer" value="/common/footer.jsp" />
</tiles:insert>