
package com.client.git.model.dto.org;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrganizationDTO {

    @SerializedName("total_count")
    @Expose
    public int totalCount;
    @SerializedName("incomplete_results")
    @Expose
    public boolean incompleteResults;
    @SerializedName("items")
    @Expose
    public List<Item> items = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OrganizationDTO() {
    }

    /**
     * 
     * @param items
     * @param totalCount
     * @param incompleteResults
     */
    public OrganizationDTO(int totalCount, boolean incompleteResults, List<Item> items) {
        super();
        this.totalCount = totalCount;
        this.incompleteResults = incompleteResults;
        this.items = items;
    }

}
