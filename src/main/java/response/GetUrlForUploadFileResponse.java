package response;

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
        "operation_id",
        "href",
        "method",
        "templated"
})
public class GetUrlForUploadFileResponse {

    @JsonProperty("operation_id")
    private String operationId;
    @JsonProperty("href")
    private String href;
    @JsonProperty("method")
    private String method;
    @JsonProperty("templated")
    private Boolean templated;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("operation_id")
    public String getOperationId() {
        return operationId;
    }

    @JsonProperty("operation_id")
    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    @JsonProperty("method")
    public String getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(String method) {
        this.method = method;
    }

    @JsonProperty("templated")
    public Boolean getTemplated() {
        return templated;
    }

    @JsonProperty("templated")
    public void setTemplated(Boolean templated) {
        this.templated = templated;
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