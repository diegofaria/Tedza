
<%@ page import="com.tedza.Filter" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'filter.label', default: 'Filter')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-filter" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-filter" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list filter">
			
				<g:if test="${filterInstance?.feeling}">
				<li class="fieldcontain">
					<span id="feeling-label" class="property-label"><g:message code="filter.feeling.label" default="Feeling" /></span>
					
						<span class="property-value" aria-labelledby="feeling-label"><g:fieldValue bean="${filterInstance}" field="feeling"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${filterInstance?.geekKind}">
				<li class="fieldcontain">
					<span id="geekKind-label" class="property-label"><g:message code="filter.geekKind.label" default="Geek Kind" /></span>
					
						<span class="property-value" aria-labelledby="geekKind-label"><g:fieldValue bean="${filterInstance}" field="geekKind"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${filterInstance?.place}">
				<li class="fieldcontain">
					<span id="place-label" class="property-label"><g:message code="filter.place.label" default="Place" /></span>
					
						<span class="property-value" aria-labelledby="place-label"><g:fieldValue bean="${filterInstance}" field="place"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${filterInstance?.id}" />
					<g:link class="edit" action="edit" id="${filterInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
