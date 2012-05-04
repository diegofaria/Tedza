
<%@ page import="com.tedza.Video" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'video.label', default: 'Video')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-video" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-video" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="date" title="${message(code: 'video.date.label', default: 'Date')}" />
					
						<g:sortableColumn property="duration" title="${message(code: 'video.duration.label', default: 'Duration')}" />
					
						<g:sortableColumn property="event" title="${message(code: 'video.event.label', default: 'Event')}" />
					
						<g:sortableColumn property="high" title="${message(code: 'video.high.label', default: 'High')}" />
					
						
					</tr>
				</thead>
				<tbody>
				<g:each in="${videoInstanceList}" status="i" var="videoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${videoInstance.id}">${fieldValue(bean: videoInstance, field: "date")}</g:link></td>
					
						<td>${fieldValue(bean: videoInstance, field: "duration")}</td>
					
						<td>${fieldValue(bean: videoInstance, field: "event.name")}</td>
					
						<td>${fieldValue(bean: videoInstance, field: "high")}</td>
					
						
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${videoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
