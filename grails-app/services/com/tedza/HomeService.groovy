package com.tedza

class HomeService {

    def buildTagFilters() {

    	List tags = [
						[label:"Hightech", tags:["Science", "Technology"]],
						[label:"Design", tags:["Design", "Creativity"]],
						[label:"Engaged", tags:["Politics", "Global issues"]],
						[label:"Science", tags:["Education", "Science"]],
						[label:"Startup", tags:["Business", "Technology", "Design"]],
						[label:"Travel", tags:["Culture", "Entertainment", "Arts", "Art"]]
    				];

    	for (def tagMap in tags)
    	{
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


//{
//
//	name: "Technology",
//	tagIcon: "/source"
//	themes: {
//		theme: {
//			name: "Tales Of Invention",
//			description: "BLb lLAbl B ALBLAL ablA lb",
//			imageUrl: "/source"
//		}
//	}
//}