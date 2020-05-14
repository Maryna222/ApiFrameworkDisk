
package response.trash;

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
    "_embedded",
    "name",
    "exif",
    "resource_id",
    "origin_path",
    "modified",
    "created",
    "path",
    "comment_ids",
    "type",
    "revision"
})
public class TrashResponse {

    @JsonProperty("_embedded")
    private response.trash.Embedded embedded;
    @JsonProperty("name")
    private String name;
    @JsonProperty("exif")
    private response.trash.Exif_ exif;
    @JsonProperty("resource_id")
    private String resourceId;
    @JsonProperty("origin_path")
    private Object originPath;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("created")
    private String created;
    @JsonProperty("path")
    private String path;
    @JsonProperty("comment_ids")
    private response.trash.CommentIds_ commentIds;
    @JsonProperty("type")
    private String type;
    @JsonProperty("revision")
    private Long revision;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("_embedded")
    public response.trash.Embedded getEmbedded() {
        return embedded;
    }

    @JsonProperty("_embedded")
    public void setEmbedded(response.trash.Embedded embedded) {
        this.embedded = embedded;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("exif")
    public response.trash.Exif_ getExif() {
        return exif;
    }

    @JsonProperty("exif")
    public void setExif(response.trash.Exif_ exif) {
        this.exif = exif;
    }

    @JsonProperty("resource_id")
    public String getResourceId() {
        return resourceId;
    }

    @JsonProperty("resource_id")
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @JsonProperty("origin_path")
    public Object getOriginPath() {
        return originPath;
    }

    @JsonProperty("origin_path")
    public void setOriginPath(Object originPath) {
        this.originPath = originPath;
    }

    @JsonProperty("modified")
    public String getModified() {
        return modified;
    }

    @JsonProperty("modified")
    public void setModified(String modified) {
        this.modified = modified;
    }

    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(String created) {
        this.created = created;
    }

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("comment_ids")
    public response.trash.CommentIds_ getCommentIds() {
        return commentIds;
    }

    @JsonProperty("comment_ids")
    public void setCommentIds(response.trash.CommentIds_ commentIds) {
        this.commentIds = commentIds;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("revision")
    public Long getRevision() {
        return revision;
    }

    @JsonProperty("revision")
    public void setRevision(Long revision) {
        this.revision = revision;
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
