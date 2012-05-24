package com.tedza

import com.tedza.Video
import com.tedza.Theme
import com.tedza.Tag
import java.text.SimpleDateFormat
import org.jsoup.*

class VideoService {

    def crawl() {
    	@Grab('org.jsoup:jsoup:1.6.1')
    	def sdf = new SimpleDateFormat("MMM yyyy")
    	def page = Jsoup.connect("http://www.ted.com/talks/quick-list").get()
    	def table = page.select("table.downloads tr")

        def count = 0
    	for(row in table) {
            count++

    		def publishDate = row.select("td:eq(0)").text()
    		def downloads = row.select("td:eq(4)")
    		if (publishDate == "" ) {
                println "!!!!!!!!!!!!!!!!!!!!!! ACHOU UM SEM PUBLISH DATE, E CODIGO ATUALIZADO :)"
                continue
            }
            if (downloads == "") {
                println "!!!!!!!!!!!!!!!!!!!!!! ACHOU UM SEM DOWNLOAD"
                continue
            }

            println "Vai realizar uma pesquisa no objeto video"

            if (Video.findByTitle(row.select("td:eq(2)").select("a").text()) != null) {
                println "!!!!!!!!!!!!!!!!!!!!!! ACHOU UM DUPLICADO"
                continue   
            }

            println "Vai criar objeto video"
	    	Video video = new Video()	
            println "CRIOU objeto video"
            
    		def fullDuration = row.select("td:eq(3)").text()
    		def duration = fullDuration.split(':')
    		def intMinutes = new Integer(duration[0])
    		def secMinutes = new Integer(duration[1])
    		def seconds = ( intMinutes * 60 ) + secMinutes
    		video.duration = seconds

    		def eventName = row.select("td:eq(1)").text()
    		def event = Event.findByName(eventName) ?: new Event(name: eventName).save(flush: true)
    		video.event = event

			def title = row.select("td:eq(2)")
    		video.title = title.select("a").text()
    		video.date = sdf.parse(publishDate)
    		video.page = "http://www.ted.com" + title.select("a").attr("href")
			video.low = downloads.select("a:eq(0)").attr("href")
			video.medium = downloads.select("a:eq(1)").attr("href")
			video.high =  downloads.select("a:eq(2)").attr("href")

    		def tagsPage = Jsoup.connect(video.page).get()
    		def themeTags = tagsPage.select("ul.relatedThemes li")
    		themeTags.each{ theme ->
    			def themeName = theme.select("a").text()
    			try {
                    Theme themeInstance = Theme.findByName(themeName) ?: new Theme(name: themeName).save(flush: true)
                    video.addToThemes(themeInstance)
                }
                catch(Exception e) {
                    println "!!!!!!!!!!!!!!!!!!! ERRO AO SALVAR THEME"                
                }
    		}

    		def tagsTags = tagsPage.select("dd.tags li")
    		tagsTags.each { tags ->
    			def tagName = tags.select("a").text()
                try {
                    Tag tag = Tag.findByName(tagName) ?: new Tag(name: tagName).save(flush: true)
                    video.addToTags(tag)
                }
                catch(Exception e) {
                    println "!!!!!!!!!!!!!!!!!!! TAG COM ERRO"
                }
    		}

            try {
                def saved = Video.findByTitle(video.title) ?: video.save(flush: true)
                println video.title    
            }
            catch(Exception e) {
                println "!!!!!!!!!!!!!!!!!!! VIDEO COM ERRO"
            }
    	}

    	return true
    }
}
