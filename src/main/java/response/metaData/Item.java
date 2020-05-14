package response.metaData;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {

    @JsonProperty("ItemFolder")
    private ItemFolder ItemFolder;
    @JsonProperty("itemFile")
    private Items itemFile;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ItemFolder")
    public ItemFolder getItemFolder() {
        return ItemFolder;
    }

    @JsonProperty("ItemFolder")
    public void setItemFolder(ItemFolder ItemFolder) {
        this.ItemFolder = ItemFolder;
    }

    @JsonProperty("itemFile")
    public Items getItemFile() {
        return itemFile;
    }

    @JsonProperty("itemFile")
    public void setItemFile(Items itemFile) {
        this.itemFile = itemFile;
    }

}
