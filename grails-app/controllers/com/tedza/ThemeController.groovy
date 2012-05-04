package com.tedza

import org.springframework.dao.DataIntegrityViolationException

class ThemeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [themeInstanceList: Theme.list(params), themeInstanceTotal: Theme.count()]
    }

    def create() {
        [themeInstance: new Theme(params)]
    }

    def save() {
        def themeInstance = new Theme(params)
        if (!themeInstance.save(flush: true)) {
            render(view: "create", model: [themeInstance: themeInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'theme.label', default: 'Theme'), themeInstance.id])
        redirect(action: "show", id: themeInstance.id)
    }

    def show() {
        def themeInstance = Theme.get(params.id)
        if (!themeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'theme.label', default: 'Theme'), params.id])
            redirect(action: "list")
            return
        }

        [themeInstance: themeInstance]
    }

    def edit() {
        def themeInstance = Theme.get(params.id)
        if (!themeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theme.label', default: 'Theme'), params.id])
            redirect(action: "list")
            return
        }

        [themeInstance: themeInstance]
    }

    def update() {
        def themeInstance = Theme.get(params.id)
        if (!themeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theme.label', default: 'Theme'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (themeInstance.version > version) {
                themeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'theme.label', default: 'Theme')] as Object[],
                          "Another user has updated this Theme while you were editing")
                render(view: "edit", model: [themeInstance: themeInstance])
                return
            }
        }

        themeInstance.properties = params

        if (!themeInstance.save(flush: true)) {
            render(view: "edit", model: [themeInstance: themeInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'theme.label', default: 'Theme'), themeInstance.id])
        redirect(action: "show", id: themeInstance.id)
    }

    def delete() {
        def themeInstance = Theme.get(params.id)
        if (!themeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'theme.label', default: 'Theme'), params.id])
            redirect(action: "list")
            return
        }

        try {
            themeInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'theme.label', default: 'Theme'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'theme.label', default: 'Theme'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
