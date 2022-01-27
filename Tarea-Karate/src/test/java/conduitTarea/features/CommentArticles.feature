Feature: Comment articles
Background: precondiciones
    * def comentarioj = read('classpath:conduitTarea/data/newComment.json') 
    * def timeValidator = read('classpath:conduitTarea/helpers/time-validator.js')
    * def tokenResponse = callonce read('classpath:conduitTarea/helpers/tokenCreacion.feature')
    * def slug = callonce read('classpath:conduitTarea/helpers/defSlug.feature')
    * def dataSlug = slug.responseSlug
    * def token = tokenResponse.authToken 
    * url apiUrl

Scenario:  Commnet
    # Step 1: Get atricles of the global feed
    Given path 'articles'
    Given params {limit:10, offset:0}
    And header Authorization = 'Token ' + token
    When method Get
    Then status 200
    # Step 2: Get the slug ID for the first arice, save it to variable
    
    # Step 3: Make a GET call to 'comments' end-point to get all comments
     Given path 'articles/'+ dataSlug +'/comments'
     
     And header Authorization = 'Token ' + token
     When method Get
     Then status 200
      # Step 4: Verify response schema
      Then match  response ==
      """
        {
            "comments": [
                 {           
                    "id": '#number',
                     "createdAt": '#? timeValidator(_)',
                     "updatedAt": '#? timeValidator(_)',
                     "body": '#string',
                     "author": {
                                "username": '#string',
                                "bio": '##string',
                                "image": '#string',
                                "following": '#boolean'
                              }
                    },
                   
                    
                    ],
              }
                     
          
          """
        #  Step 5: Get the count of the comments array lentgh and save to variable
            #Example
            * def commentsCount = karate.sizeOf(response.comments)
            * print commentsCount
        # Step 6: Make a POST request to publish a new comment
        Given path 'articles/'+ dataSlug+ '/comments'
        And header Authorization = 'Token ' + token
        And request comentarioj
        When method Post
        Then status 200
         # Step 7: Verify response schema that should contain posted comment text
       
        And match response ==
        """
            {
             "comment": {
                         "id":"#number",
                          "createdAt": "#? timeValidator(_)",
                           "updatedAt": "#? timeValidator(_)",
                          "body": "#string",
                          "author": {
                                "username": "#string",
                                 "bio": "##string",
                                 "image": "#string",
                                  "following":"#boolean" 
                 }
            }
             }
        """
                # Step 8: Get the list of all comments for this article one more time
                Given path 'articles/'+ dataSlug +'/comments'
                And header Authorization = 'Token ' + token
                When method Get
                * def idComment = response.comments[0].id
                * print idComment
                Then status 200
               
               
                 # Step 9: Verify number of comments increased by 1 (similar like we did with favorite counts)
                 Given path 'articles/'+ dataSlug+'/comments'
                 And header Authorization = 'Token ' + token
                 * def initialCount = commentsCount
                 * def newCuent = karate.sizeOf(response.comments)
                 * match  newCuent == initialCount + 1
                
                

                 
                  # Step 10: Make a DELETE request to delete comment
                  
                  Given path idComment
                  And header Authorization = 'Token ' + token
                  When method DELETE
                  Then status 204

                # Step 11: Get all comments again and verify number of comments decreased by 1
                Given path 'articles/'+ dataSlug +'/comments'
                And header Authorization = 'Token ' + token
                When method Get
                Then status 200
                * def cuentaFinal = karate.sizeOf(response.comments)
                * match cuentaFinal == newCuent - 1






        

