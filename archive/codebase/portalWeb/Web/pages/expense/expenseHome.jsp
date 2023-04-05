<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>

<tiles:insert page="/common/layout/layout02.jsp" flush="true">
  <tiles:put name="title" type="string">
  <bean:message key="expense.reports.title"/>
  </tiles:put>
  <tiles:put name="header" value="/expense/tiles/header.jsp" />
  <tiles:put name="main" type="string">
  <div>
  	<div class="form">  	
  	<%
  				java.util.List forms=(java.util.List)request.getAttribute("forms");
  	%>
  	<%
  		if(forms.size()==0){
  	%>
  	<bean:message key="expense.reports.home.none"/>
  	<%
  		}
  		else
  		{
  	%>
  		
  		<table width="100%">
  		<th colspan="7" align="center"><bean:message key="expense.reports.title"/></th>
  		<tr style="background:#000;">
  			<td style="color:#FFF;">ID</td>
			<td style="color:#FFF;">Name</td>
  			<td style="color:#FFF;">Date</td>
  			<td style="color:#FFF;">Total</td>
  			<td style="color:#FFF;">Purpose</td>
  			<td style="color:#FFF;">Status</td>
  			<td style="color:#FFF;">Actions</td>
  		</tr>
  		<%
  	
  				java.util.Iterator formsIterator=forms.iterator();
  				//Color initializer
  					boolean cs=false;
		  			String bgcolor="#EEEEEE";

  				while(formsIterator.hasNext())
  				{
			  		com.esood.expense.ui.form.ExpenseForm form=(com.esood.expense.ui.form.ExpenseForm) formsIterator.next();
			  		
			  		if(true==cs){
			  			bgcolor="#FFFFFF";
			  		}else
			  		{
			  			bgcolor="#EEEEEE";
			  		}
			  	cs=!cs;
  		%>
  		
  		<tr bgcolor="<%=bgcolor%>">
  		<td><%=form.getId()%></td>
  		<td><%=form.getName()%></td>
  		<td><%=form.getDateDisplay()%></td>
  		<td>$<%=form.getExpenseAmount()%></td>
  		<td><%=form.getPurpose()%></td>
  		<td><%=form.getStatusDisplay()%></td>
  		<td>
  		<%
  		org.apache.commons.logging.Log log=org.apache.commons.logging.LogFactory.getLog(this.getClass());
  		String disable="disabled";
  		//if the type & version  in session matches the one in the form.
  		com.esood.ui.Context ctx=(com.esood.ui.Context) session.getAttribute(com.esood.ui.Context.NAME);
  		Integer contextVersion=ctx.getApplicationVersion();
  		String contextType=ctx.getApplicationType();
  		log.debug("ContextVersion="+contextVersion+" ContextType"+contextType);
  		log.debug("FormVersion"+form.getApplicationVersion()+" FormType"+form.getApplicationType());
  		boolean versionsEqual=contextVersion.equals(form.getApplicationVersion());
  		boolean typesEqual=contextType.equals(form.getApplicationType());
  		log.debug("versions equality: "+versionsEqual );
  		log.debug("types equality:"+typesEqual);
  		
   		if(versionsEqual && typesEqual)
  		{
			log.debug("equal");
			disable="";
  		}
  		%>
  		<button type="button" <%=disable%> onclick="doCommand('read','/mst/expense/general','<%=form.getId()%>')" alt="Click here to edit">View/Edit</button>
  		<button type="button" <%=disable%> onclick="doCommand('delete','/mst/expense/home','<%=form.getId()%>')">Delete</button>
  		</td>
  		<%
  		}
  		}//end else
  		%>
  		
  		</tr>
  		</table>
  				<center><button onclick='location.href="/mst/expense/general.do?cmd=read"'>Start New Expense</button></center>
  	</div>
  </div>
  </tiles:put>
    <tiles:put name="left" value="/expense/tiles/left.jsp" />
  	<tiles:put name="footer" value="/common/footer.jsp" />
</tiles:insert>