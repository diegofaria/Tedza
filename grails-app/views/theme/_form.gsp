<%@ page import="com.tedza.Theme" %>



<div class="fieldcontain ${hasErrors(bean: themeInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="theme.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${themeInstance?.name}"/>
</div>

