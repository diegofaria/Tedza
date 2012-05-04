<%@ page import="com.tedza.Video" %>



<div class="fieldcontain ${hasErrors(bean: videoInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="video.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${videoInstance?.date}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: videoInstance, field: 'duration', 'error')} required">
	<label for="duration">
		<g:message code="video.duration.label" default="Duration" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="duration" required="" value="${fieldValue(bean: videoInstance, field: 'duration')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: videoInstance, field: 'event', 'error')} ">
	<label for="event">
		<g:message code="video.event.label" default="Event" />
		
	</label>
	<g:textField name="event" value="${videoInstance?.event}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: videoInstance, field: 'high', 'error')} ">
	<label for="high">
		<g:message code="video.high.label" default="High" />
		
	</label>
	<g:textField name="high" value="${videoInstance?.high}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: videoInstance, field: 'low', 'error')} ">
	<label for="low">
		<g:message code="video.low.label" default="Low" />
		
	</label>
	<g:textField name="low" value="${videoInstance?.low}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: videoInstance, field: 'medium', 'error')} ">
	<label for="medium">
		<g:message code="video.medium.label" default="Medium" />
		
	</label>
	<g:textField name="medium" value="${videoInstance?.medium}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: videoInstance, field: 'page', 'error')} ">
	<label for="page">
		<g:message code="video.page.label" default="Page" />
		
	</label>
	<g:textField name="page" value="${videoInstance?.page}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: videoInstance, field: 'tags', 'error')} ">
	<label for="tags">
		<g:message code="video.tags.label" default="Tags" />
		
	</label>
	<g:select name="tags" from="${com.tedza.Tag.list()}" multiple="multiple" optionKey="id" size="5" value="${videoInstance?.tags*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: videoInstance, field: 'themes', 'error')} ">
	<label for="themes">
		<g:message code="video.themes.label" default="Themes" />
		
	</label>
	<g:select name="themes" from="${com.tedza.Theme.list()}" multiple="multiple" optionKey="id" size="5" value="${videoInstance?.themes*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: videoInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="video.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${videoInstance?.title}"/>
</div>

