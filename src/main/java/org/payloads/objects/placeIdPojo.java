package org.payloads.objects;

public class placeIdPojo {

    private String place_id;

    @Override
    public String toString() {
        return "placeIdPojo{" +
                "place_id='" + place_id + '\'' +
                '}';
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }
}
