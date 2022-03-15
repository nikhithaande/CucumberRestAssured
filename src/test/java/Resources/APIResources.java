package Resources;

public enum APIResources {
    AddPlaceApi("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceApi("/maps/api/place/delete/json");
    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource(){
        return resource;
    }
}
