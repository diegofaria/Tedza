<%@ page import="com.tedza.Filter" %>



<div class="fieldcontain ${hasErrors(bean: filterInstance, field: 'feeling', 'error')} ">
	<label for="feeling">
		<g:message code="filter.feeling.label" default="Feeling" />
		
	</label>
	<g:textField name="feeling" value="${filterInstance?.feeling}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: filterInstance, field: 'geekKind', 'error')} ">
	<label for="geekKind">
		<g:message code="filter.geekKind.label" default="Geek Kind" />
		
	</label>
	<g:textField name="geekKind" value="${filterInstance?.geekKind}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: filterInstance, field: 'place', 'error')} ">
	<label for="place">
		<g:message code="filter.place.label" default="Place" />
		
	</label>
	<g:textField name="place" value="${filterInstance?.place}"/>
</div>

