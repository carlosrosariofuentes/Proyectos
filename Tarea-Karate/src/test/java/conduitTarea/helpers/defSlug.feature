Feature: definicion del slug

Background: precondiciones
    * def tokenResponse = callonce read('classpath:conduitTarea/helpers/tokenCreacion.feature')
    * def token = tokenResponse.authToken 
    * url apiUrl
Scenario: slug
  
   #Step 1: Get atricles of the global feed
   Given  path 'articles'
   And header Authorization = 'Token '+ token
   Given params {limit:10,offset:0}
   When method Get
   Then status 200
#    * print response 
    # Step 2: Get the favorites count and slug ID for the first arice, save it to variables
    * def responseSlug = response.articles[0].slug
   
   