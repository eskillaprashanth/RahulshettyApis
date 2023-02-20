package org.payloads.objects;
public class PutPojo {
    private String address;
    private String key;

    @Override
    public String toString() {
        return "PutPojo{" +
                "address='" + address + '\'' +
                ", key='" + key + '\'' +
                ", place_Id='" + place_Id + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPlace_Id() {
        return place_Id;
    }

    public void setPlace_Id(String place_Id) {
        this.place_Id = place_Id;
    }

    private String place_Id;

}