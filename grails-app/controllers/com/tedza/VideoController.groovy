package com.tedza

import grails.converters.JSON

import org.apache.commons.lang.StringUtils
import org.springframework.dao.DataIntegrityViolationException

class VideoController {
	
	enum Type {
		TECHNOLOGY("Hightech"), DESIGN("Design"), ENGAGED("Engaged"), SCIENCE("Science"), STARTUP("Startup"), TRAVEL("Travel")
		final String id
		private Type(String id) { this.id = id }
		static Type byId(String id) {
			values().find { it.id == id }
		}
		String toString() {
			return StringUtils.capitalize(name().toLowerCase())
		}
	}

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def videoService

    def index() {
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

	def grabVideos() {
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
	}
	
	def getResults() {
		def q1 = params.q1
		def q2 = params.q2
		def q3 = params.q3
		
		Type type = Type.byId(q1)
        def totalDuration = Integer.parseInt(params.q2.split(" ")[0]) * 60
        println "q1: " + type + "   duration: " + totalDuration + "    q3: " + q3
		
		def videos = Video.executeQuery("from Video v, IN (v.tags) as tags, IN (v.themes) as themes where tags.name = '${q1}' and themes.name = '${q3}'")
		int duration = 0
		List<Video> videoPlaylist
		for (video in videos) {
			// if the video duration plus the sum of playlist exceeds the total, 
			// skip to the next
			if ((video.duration + duration) > totalDuration) continue
			
			duration += video.duration
			videoPlaylist.add(video)
			
			// if the difference between the total minutes and playlist
			// is lower than 5 minutes, the playlist is done
			if ((totalDuration - duration) < 300) break
		}

		println videoPlaylist.size()		
		render videoPlaylist as JSON
	}
}
