Feature: articulos
Background: precondiciones
    * def timeValidator = read('classpath:conduitTarea/helpers/time-validator.js')
    * def tokenResponse = callonce read('classpath:conduitTarea/helpers/tokenCreacion.feature')
    * def token = tokenResponse.authToken 
    * def slug = callonce read('classpath:conduitTarea/helpers/defSlug.feature')
    * def dataSlug = slug.responseSlug
   
    * url apiUrl

    
Scenario: favoritos

    # Step 3: Make POST request to increse favorites count for the first 
    Given path 'articles/'+ dataSlug+'/favorite'
    And header Authorization = 'Token ' + token
    When method Post
    Then status 200
    * print response
    # Step 4: Verify response schema
    And match response ==

    """
        {
	"article": {
		"id": "#number",
		"slug": "#string",
		"title": "#string",
		"description": "#string",
		"body": "#string",
		"createdAt": "#? timeValidator(_)",
		"updatedAt": "#? timeValidator(_)",
		"authorId":"#number",
		"tagList":"#array",

		"author": {
			"username": "#string",
			"bio": "##string",
			"image": "#string",
			"following": "#boolean"
		},

		"favoritedBy": [
        {
				"id": "#number",
				"email": "#string",
				"username": "#string",
				"password": "#string",
				"image": "#string",
				"bio": "##string",
				"demo": "#boolean",
		}
        ],
        "favorited": "#boolean",
		"favoritesCount": "#number"
	}
}
    
    """

   
    # Step 5: Verify that favorites article incremented by 1
    Given path 'articles'
    Given params {favorited:carlos23, limit:10, offset:0}
    And header Authorization = 'Token ' + token
    When method Get
    Then status 200
    * print response
    * def initialCount = 0
    * match response.articles[0].favoritesCount == initialCount + 1
    * print response

    # # Step 6: Get all favorite articles

    Given path 'articles'
    Given params {favorited:carlos2, limit:10, offset:0}
    And header Authorization = 'Token ' + token
    When method Get
    Then status 200
    And print response
    
    #  # Step 7: Verify response schema 
     Given path 'articles'
     Given params {limit:10, offset:0}
     And header Authorization = 'Token ' + token
     When method Get
     Then status 200   

      And match each response.articles ==
      """
         {
             "slug": '#string',
             "title": '#string',
             "description": '#string',
             "body": '#string',
             "createdAt": '#? timeValidator(_)',
             "updatedAt": '#? timeValidator(_)',
             "tagList": '#array',
             "author": {
                 "username": '#string',
                 "bio": '##string',
                 "image": '#string',
                 "following": '#boolean'
             },
             "favoritesCount": '#number',
             "favorited": '#boolean',
         }
         """

    
    # Step 8: Verify that slug ID from Step 2 exist in one of the favorite articles
       Given path 'articles'
       Given params {favorited:carlos23, limit:10, offset:0}
       And header Authorization = 'Token ' + token
       When method Get
       Then status 200 
      
       And match  response.articles[0].slug == dataSlug
       
      
      # step 9 remove favorite  that slug ID and verify  response.favoritesCount = 0

       Given path 'articles/'+ dataSlug+'/favorite'
       And header Authorization = 'Token ' + token
       When method delete
       Then status 200
       And match response.article.favoritesCount ==0



        
