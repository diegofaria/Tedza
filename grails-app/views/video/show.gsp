
<%@ page import="com.tedza.Video" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'video.label', default: 'Video')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-video" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-video" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list video">
			
				<g:if test="${videoInstance?.date}">
				<li class="fieldcontain">
					<span id="date-label" class="property-label"><g:message code="video.date.label" default="Date" /></span>
					
						<span class="property-value" aria-labelledby="date-label"><g:formatDate date="${videoInstance?.date}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${videoInstance?.duration}">
				<li class="fieldcontain">
					<span id="duration-label" class="property-label"><g:message code="video.duration.label" default="Duration" /></span>
					
						<span class="property-value" aria-labelledby="duration-label"><g:fieldValue bean="${videoInstance}" field="duration"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${videoInstance?.event}">
				<li class="fieldcontain">
					<span id="event-label" class="property-label"><g:message code="video.event.label" default="Event" /></span>
					
						<span class="property-value" aria-labelledby="event-label"><g:fieldValue bean="${videoInstance}" field="event"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${videoInstance?.high}">
				<li class="fieldcontain">
					<span id="high-label" class="property-label"><g:message code="video.high.label" default="High" /></span>
					
						<span class="property-value" aria-labelledby="high-label"><g:fieldValue bean="${videoInstance}" field="high"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${videoInstance?.low}">
				<li class="fieldcontain">
					<span id="low-label" class="property-label"><g:message code="video.low.label" default="Low" /></span>
					
						<span class="property-value" aria-labelledby="low-label"><g:fieldValue bean="${videoInstance}" field="low"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${videoInstance?.medium}">
				<li class="fieldcontain">
					<span id="medium-label" class="property-label"><g:message code="video.medium.label" default="Medium" /></span>
					
						<span class="property-value" aria-labelledby="medium-label"><g:fieldValue bean="${videoInstance}" field="medium"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${videoInstance?.page}">
				<li class="fieldcontain">
					<span id="page-label" class="property-label"><g:message code="video.page.label" default="Page" /></span>
					
						<span class="property-value" aria-labelledby="page-label"><g:fieldValue bean="${videoInstance}" field="page"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${videoInstance?.tags}">
				<li class="fieldcontain">
					<span id="tags-label" class="property-label"><g:message code="video.tags.label" default="Tags" /></span>
					
						<g:each in="${videoInstance.tags}" var="t">
						<span class="property-value" aria-labelledby="tags-label"><g:link controller="tag" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${videoInstance?.themes}">
				<li class="fieldcontain">
					<span id="themes-label" class="property-label"><g:message code="video.themes.label" default="Themes" /></span>
					
						<g:each in="${videoInstance.themes}" var="t">
						<span class="property-value" aria-labelledby="themes-label"><g:link controller="theme" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${videoInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="video.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${videoInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${videoInstance?.id}" />
					<g:link class="edit" action="edit" id="${videoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
