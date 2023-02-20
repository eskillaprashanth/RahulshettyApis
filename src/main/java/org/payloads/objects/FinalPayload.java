package org.payloads.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FinalPayload {

    private LocationPojo location;
    private int accuracy;
    private String name;
    private String phone_number;
    private String address;
    private List<String> types;
    private String website;
    private String language;

    @Override
    public String toString(){
        return "ReadingDataPojo{" +
                "location=" + location +
                ", accuracy=" + accuracy +
                ", name='" + name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", address='" + address + '\'' +
                ", types=" + types +
                ", website='" + website + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}