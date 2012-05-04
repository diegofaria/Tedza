package com.tedza



import org.junit.*
import grails.test.mixin.*

@TestFor(ThemeController)
@Mock(Theme)
class ThemeControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/theme/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.themeInstanceList.size() == 0
        assert model.themeInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.themeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.themeInstance != null
        assert view == '/theme/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/theme/show/1'
        assert controller.flash.message != null
        assert Theme.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/theme/list'


        populateValidParams(params)
        def theme = new Theme(params)

        assert theme.save() != null

        params.id = theme.id

        def model = controller.show()

        assert model.themeInstance == theme
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/theme/list'


        populateValidParams(params)
        def theme = new Theme(params)

        assert theme.save() != null

        params.id = theme.id

        def model = controller.edit()

        assert model.themeInstance == theme
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/theme/list'

        response.reset()


        populateValidParams(params)
        def theme = new Theme(params)

        assert theme.save() != null

        // test invalid parameters in update
        params.id = theme.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/theme/edit"
        assert model.themeInstance != null

        theme.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/theme/show/$theme.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        theme.clearErrors()

        populateValidParams(params)
        params.id = theme.id
        params.version = -1
        controller.update()

        assert view == "/theme/edit"
        assert model.themeInstance != null
        assert model.themeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/theme/list'

        response.reset()

        populateValidParams(params)
        def theme = new Theme(params)

        assert theme.save() != null
        assert Theme.count() == 1

        params.id = theme.id

        controller.delete()

        assert Theme.count() == 0
        assert Theme.get(theme.id) == null
        assert response.redirectedUrl == '/theme/list'
    }
}
