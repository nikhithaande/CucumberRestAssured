package StepDef;


import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        AddPlaceSteps sd = new AddPlaceSteps();
        if(AddPlaceSteps.placeId==null) {
            sd.add_place_payload_with("Tom", "French", "1234 drive");
            sd.user_calls_with_request("AddPlaceApi", "POST");
            sd.verify_place_id_created_maps_to_using("Tom", "GetPlaceAPI");
        }
    }
}
