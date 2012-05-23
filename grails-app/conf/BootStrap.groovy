import com.tedza.VideoService

class BootStrap {

	def videoService

    def init = { servletContext ->
    	videoService.crawl()
    }

    def destroy = {
    }
}
