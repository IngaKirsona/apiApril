package apiApril.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
//to ignore the rest of not needed values:
@JsonIgnoreProperties(ignoreUnknown = true)
public class List {
    @JsonProperty("id")
    //to save the needed information from response, need to create also setters and getters
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("idBoard")
    private String idBoard;
}
