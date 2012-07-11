package com.tedza

import grails.converters.JSON

import org.apache.commons.lang.StringUtils

class VideoController {
	def videoService
	
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

    def grabThemes() {
        try {
            println "Vai pegar themes"
            videoService.crawlThemes()
            println "Terminou a pesquisa de themes"
        } catch(Exception e){
            println "Terminou até terminou... MAS DEU PAU!!!!! >:) "
            println e
        }
    }
	
	def getResults() {
        println "\n\n\n" + params.dump()
		def geekTags = params.geekTags
        def themeId = params.themeId
        def spendingTime = new Integer(params.spendingTime)

		def tags = "'" + geekTags + "'"
		tags = tags.replace(",", "','")
		
		println "tags " + tags

        def totalDuration = spendingTime * 60
		def query = "SELECT new Map(v.id as id, v.title as title, v.duration as duration, v.low as low, v.medium as medium, v.high as high, v.date as date, v.event as event) FROM Video AS v, IN (v.tags) AS t, IN (v.themes) AS th " +
                    "WHERE t.name in (${tags}) AND th.id = ${themeId} AND v.duration <= ${totalDuration} " +
                    "order by random()"

        println query

		def videos = Video.executeQuery(query)
		println videos.size()

        int userTime = 0
		List<Video> videoPlaylist = new ArrayList<Video>()
		for (video in videos) {
			if ((video.duration + userTime) > totalDuration) continue
			userTime += video.duration
			videoPlaylist.add(video)
			if ((totalDuration - userTime) < 180) break
		}

		render videoPlaylist as JSON
	}
}