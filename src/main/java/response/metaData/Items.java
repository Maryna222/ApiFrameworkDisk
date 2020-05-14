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
        "antivirus_status",
        "size",
        "comment_ids",
        "name",
        "exif",
        "created",
        "resource_id",
        "modified",
        "mime_type",
        "file",
        "path",
        "media_type",
        "sha256",
        "type",
        "md5",
        "revision"
})
public class Items {

    @JsonProperty("antivirus_status")
    private String antivirusStatus;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("comment_ids")
    private CommentIds commentIds;
    @JsonProperty("name")
    private String name;
    @JsonProperty("exif")
    private Exif exif;
    @JsonProperty("created")
    private String created;
    @JsonProperty("resource_id")
    private String resourceId;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("mime_type")
    private String mimeType;
    @JsonProperty("file")
    private String file;
    @JsonProperty("path")
    private String path;
    @JsonProperty("media_type")
    private String mediaType;
    @JsonProperty("sha256")
    private String sha256;
    @JsonProperty("type")
    private String type;
    @JsonProperty("md5")
    private String md5;
    @JsonProperty("revision")
    private Long revision;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("antivirus_status")
    public String getAntivirusStatus() {
        return antivirusStatus;
    }

    @JsonProperty("antivirus_status")
    public void setAntivirusStatus(String antivirusStatus) {
        this.antivirusStatus = antivirusStatus;
    }

    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    @JsonProperty("comment_ids")
    public CommentIds getCommentIds() {
        return commentIds;
    }

    @JsonProperty("comment_ids")
    public void setCommentIds(CommentIds commentIds) {
        this.commentIds = commentIds;
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
    public Exif getExif() {
        return exif;
    }

    @JsonProperty("exif")
    public void setExif(Exif exif) {
        this.exif = exif;
    }

    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(String created) {
        this.created = created;
    }

    @JsonProperty("resource_id")
    public String getResourceId() {
        return resourceId;
    }

    @JsonProperty("resource_id")
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @JsonProperty("modified")
    public String getModified() {
        return modified;
    }

    @JsonProperty("modified")
    public void setModified(String modified) {
        this.modified = modified;
    }

    @JsonProperty("mime_type")
    public String getMimeType() {
        return mimeType;
    }

    @JsonProperty("mime_type")
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @JsonProperty("file")
    public String getFile() {
        return file;
    }

    @JsonProperty("file")
    public void setFile(String file) {
        this.file = file;
    }

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("media_type")
    public String getMediaType() {
        return mediaType;
    }

    @JsonProperty("media_type")
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @JsonProperty("sha256")
    public String getSha256() {
        return sha256;
    }

    @JsonProperty("sha256")
    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("md5")
    public String getMd5() {
        return md5;
    }

    @JsonProperty("md5")
    public void setMd5(String md5) {
        this.md5 = md5;
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