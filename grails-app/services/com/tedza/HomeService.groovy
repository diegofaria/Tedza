package com.tedza

class HomeService {
    def buildTagFilters() {
    	def tags = [
			steps: [
				"What kind of geek are you<span>?</span>",
				"How much time do you have now<span>?</span>",
				"Select a playlist <span>...</span>"
			],
			data: [
				[label:"Hightech", tags:["Science", "Technology"], img: "images/icon_hightech.png"],
				[label:"Design", tags:["Design", "Creativity"], img: "images/icon_design.png"],
				[label:"Engaged", tags:["Politics", "Global issues"], img: "images/icon_engaged.png"],
				[label:"Science", tags:["Education", "Science"], img: "images/icon_science.png"],
				[label:"Startup", tags:["Business", "Technology", "Design"], img: "images/icon_startup.png"],
				[label:"Travel", tags:["Culture", "Entertainment", "Arts", "Art"], img: "images/icon_travel.png" ]
			]
		];

    	for (def tagMap in tags.data) {
	    	def themes = Video.executeQuery("""
	    		SELECT new Map(
				    		count(theme.name) as quantity,
				    		theme.id as id,
				    		theme.name as name,
				    		theme.description as description,
				    		theme.imageUrl as imageUrl
			    			)
	    		FROM Video v
					join v.tags tag
					join v.themes theme
				WHERE tag.name in :tags
				group by theme.id, theme.name
				order by 1 desc""", [tags:tagMap.tags], [max: 6]);

	    	tagMap.put("themes", themes);
	    }
    	return tags;
    }
}