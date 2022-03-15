Feature: Validate PlaceAPI

  @AddPlace
  Scenario Outline: Verify if we can add a place to PlaceAPI
    Given Add place payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceApi" with "POST" request
    Then User got a response with StatusCode 200
    And "status" in the response is "OK"
    And Verify placeID created maps to "<name>" using "GetPlaceAPI"
    #And "Scope" in the response is "APP"

    Examples:
    | name | language | address |
    | AA House | Indian | 234 central |
 #   | BB House | Spanish | 123 Paradise |

  @DeletePlace
  Scenario: Verify if delete place functionality is working
    Given Delete place payload
    When User calls "DeletePlaceApi" with "POST" request
    Then User got a response with StatusCode 200
    And "status" in the response is "OK"
