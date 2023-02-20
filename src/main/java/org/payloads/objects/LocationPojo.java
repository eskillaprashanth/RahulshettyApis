package org.payloads.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocationPojo {
    private String lat;
    private String lng;

    @Override
    public String toString() {
        return "LocationPojo{" +
                "lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }


}
