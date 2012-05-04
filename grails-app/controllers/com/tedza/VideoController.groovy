package com.tedza

import org.springframework.dao.DataIntegrityViolationException

class VideoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def videoService

    def index() {
        def finished = false
        while(!finished) {
            try {
                finished = videoService.crawl()
            }
            catch(Exception e) {
                println e.getMessage()
                println "DEU ERRO NESSA PORRA!"
                finished = false
            }
        }
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [videoInstanceList: Video.list(params), videoInstanceTotal: Video.count()]
    }

    def create() {
        [videoInstance: new Video(params)]
    }

    def save() {
        def videoInstance = new Video(params)
        if (!videoInstance.save(flush: true)) {
            render(view: "create", model: [videoInstance: videoInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'video.label', default: 'Video'), videoInstance.id])
        redirect(action: "show", id: videoInstance.id)
    }

    def show() {
        def videoInstance = Video.get(params.id)
        if (!videoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'video.label', default: 'Video'), params.id])
            redirect(action: "list")
            return
        }

        [videoInstance: videoInstance]
    }

    def edit() {
        def videoInstance = Video.get(params.id)
        if (!videoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'video.label', default: 'Video'), params.id])
            redirect(action: "list")
            return
        }

        [videoInstance: videoInstance]
    }

    def update() {
        def videoInstance = Video.get(params.id)
        if (!videoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'video.label', default: 'Video'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (videoInstance.version > version) {
                videoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'video.label', default: 'Video')] as Object[],
                          "Another user has updated this Video while you were editing")
                render(view: "edit", model: [videoInstance: videoInstance])
                return
            }
        }

        videoInstance.properties = params

        if (!videoInstance.save(flush: true)) {
            render(view: "edit", model: [videoInstance: videoInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'video.label', default: 'Video'), videoInstance.id])
        redirect(action: "show", id: videoInstance.id)
    }

    def delete() {
        def videoInstance = Video.get(params.id)
        if (!videoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'video.label', default: 'Video'), params.id])
            redirect(action: "list")
            return
        }

        try {
            videoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'video.label', default: 'Video'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'video.label', default: 'Video'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
