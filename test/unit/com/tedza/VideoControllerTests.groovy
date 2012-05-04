package com.tedza



import org.junit.*
import grails.test.mixin.*

@TestFor(VideoController)
@Mock(Video)
class VideoControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/video/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.videoInstanceList.size() == 0
        assert model.videoInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.videoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.videoInstance != null
        assert view == '/video/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/video/show/1'
        assert controller.flash.message != null
        assert Video.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/video/list'


        populateValidParams(params)
        def video = new Video(params)

        assert video.save() != null

        params.id = video.id

        def model = controller.show()

        assert model.videoInstance == video
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/video/list'


        populateValidParams(params)
        def video = new Video(params)

        assert video.save() != null

        params.id = video.id

        def model = controller.edit()

        assert model.videoInstance == video
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/video/list'

        response.reset()


        populateValidParams(params)
        def video = new Video(params)

        assert video.save() != null

        // test invalid parameters in update
        params.id = video.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/video/edit"
        assert model.videoInstance != null

        video.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/video/show/$video.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        video.clearErrors()

        populateValidParams(params)
        params.id = video.id
        params.version = -1
        controller.update()

        assert view == "/video/edit"
        assert model.videoInstance != null
        assert model.videoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/video/list'

        response.reset()

        populateValidParams(params)
        def video = new Video(params)

        assert video.save() != null
        assert Video.count() == 1

        params.id = video.id

        controller.delete()

        assert Video.count() == 0
        assert Video.get(video.id) == null
        assert response.redirectedUrl == '/video/list'
    }
}
