<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>

<tiles:insert page="/common/layout/layout02.jsp" flush="true">
		<tiles:put name="title" value="Profile" />
		<tiles:put name="header" value="/common/header.jsp" />
		<tiles:put name="left" value="/common/left.jsp" />
		<tiles:put name="footer" value="/common/footer.jsp" />

		<tiles:put name="main" type="string"> 
			<div class="form">
				<table class="form">
					<html:form action="/customer/profile">
					<html:hidden property="cmd" value="create"/>
					<html:hidden property="page" value="2"/>
						<th colspan="2">Anonymous Demographics</th>
						<tr>	
							<td>Years of Internet Experience:</td>
							<td><html:select property="internetYearsCode"><html:options collection="internetExperienceYears" labelProperty="label" property="value"/></html:select></td>
						</tr>
						<tr>
							<td>Internet Usage (Hours Per Week):</td>
							<td><html:select property="internetHoursPerWeekCode"><html:options collection="internetHours" labelProperty="label" property="value"/></html:select></td>
						</tr>
						<tr>
							<td>Level of Education:</td>
							<td><html:select property="levelOfEducationCode"><html:options collection="educationLevels" labelProperty="label" property="value"/></html:select></td>
						</tr>
						<tr>
							<td>Age Group:</td>
							<td><html:select property="ageGroupCode"><html:options collection="ageGroups" labelProperty="label" property="value"/></html:select></td>
						</tr>
						<tr>
							<td>Gender:</td>
							<td><html:select property="genderCode"><html:options collection="genders" labelProperty="label" property="value"/></html:select></td>
							</tr>

					
						<tr>
							<td colspan="2" align="right"><html:submit value="Register "/></td>
						</tr>	
				</html:form>
			</table>
		</div>
	</tiles:put>
	
</tiles:insert>