
package response.metaData;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "private_resource",
    "public_resource"
})
public class CommentIds {

    @JsonProperty("private_resource")
    private String privateResource;
    @JsonProperty("public_resource")
    private String publicResource;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("private_resource")
    public String getPrivateResource() {
        return privateResource;
    }

    @JsonProperty("private_resource")
    public void setPrivateResource(String privateResource) {
        this.privateResource = privateResource;
    }

    @JsonProperty("public_resource")
    public String getPublicResource() {
        return publicResource;
    }

    @JsonProperty("public_resource")
    public void setPublicResource(String publicResource) {
        this.publicResource = publicResource;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
