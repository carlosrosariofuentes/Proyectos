Feature: create Token

Scenario: create Token
    Given url apiUrl
    Given path 'users/login'
    And request 
    """
        {
    "user": {
        "email": "#(userEmail)",
        "password": "#(userPassword)",   
    }
}
"""
When method Post
Then status 200
# And print response.user.Token
* def authToken =  response.user.token
* print authToken