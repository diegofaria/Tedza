package com.tedza

import com.tedza.HomeService

import grails.converters.JSON

class HomeController {

	def homeService

    def index() { 

    	def jsonFilter = homeService.buildTagFilters() as JSON;
    	[jsonFilter:jsonFilter];

    }
}
