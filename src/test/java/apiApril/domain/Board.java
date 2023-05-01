package apiApril.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
//say that we don't want to save everything, but just those values what we are mentioning here, for rest values  - ignore
@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {
    //sort out the in from from all the json response
    //we are creating our own data type, which store two values (parameters)
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

}
