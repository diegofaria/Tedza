package com.tedza

class Theme {

	String name
	String description
	String imageUrl	
	
    static constraints = {
    	description(nullable:true);
    	imageUrl(nullable:true);
    }
}
