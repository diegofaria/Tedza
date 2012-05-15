package com.tedza

import org.springframework.dao.DataIntegrityViolationException

class FilterController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def getResults() {
        def q1 = params.q1
        def q2 = params.q2
        def q3 = params.q3
        println q1
        Tag tag = Tag.findByName(q1)
        Video video = new Video(tags: [tag])
        List<Video> videos = Video.executeQuery("from Video v, IN (v.tags) as t where t.name = '${q1}'")
        
        videos.each { 
            
        }


        println videos.size()
    }

    def index() {
        println params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [filterInstanceList: Filter.list(params), filterInstanceTotal: Filter.count()]
    }

    def create() {
        [filterInstance: new Filter(params)]
    }

    def save() {
        def filterInstance = new Filter(params)
        if (!filterInstance.save(flush: true)) {
            render(view: "create", model: [filterInstance: filterInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'filter.label', default: 'Filter'), filterInstance.id])
        redirect(action: "show", id: filterInstance.id)
    }

    def show() {
        def filterInstance = Filter.get(params.id)
        if (!filterInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'filter.label', default: 'Filter'), params.id])
            redirect(action: "list")
            return
        }

        [filterInstance: filterInstance]
    }

    def edit() {
        def filterInstance = Filter.get(params.id)
        if (!filterInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'filter.label', default: 'Filter'), params.id])
            redirect(action: "list")
            return
        }

        [filterInstance: filterInstance]
    }

    def update() {
        def filterInstance = Filter.get(params.id)
        if (!filterInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'filter.label', default: 'Filter'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (filterInstance.version > version) {
                filterInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'filter.label', default: 'Filter')] as Object[],
                          "Another user has updated this Filter while you were editing")
                render(view: "edit", model: [filterInstance: filterInstance])
                return
            }
        }

        filterInstance.properties = params

        if (!filterInstance.save(flush: true)) {
            render(view: "edit", model: [filterInstance: filterInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'filter.label', default: 'Filter'), filterInstance.id])
        redirect(action: "show", id: filterInstance.id)
    }

    def delete() {
        def filterInstance = Filter.get(params.id)
        if (!filterInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'filter.label', default: 'Filter'), params.id])
            redirect(action: "list")
            return
        }

        try {
            filterInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'filter.label', default: 'Filter'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'filter.label', default: 'Filter'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
