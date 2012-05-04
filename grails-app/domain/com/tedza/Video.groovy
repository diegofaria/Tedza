package com.tedza

class Video {

	String title
	Event event
	String page
	String low
	String medium
	String high
	Date date
	int duration
	
	static hasMany = [tags: Tag, themes: Theme]

    static constraints = {
    }
}
