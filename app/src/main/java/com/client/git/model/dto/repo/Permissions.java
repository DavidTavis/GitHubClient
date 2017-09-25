
package com.client.git.model.dto.repo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Permissions {

    @SerializedName("admin")
    @Expose
    public boolean admin;
    @SerializedName("push")
    @Expose
    public boolean push;
    @SerializedName("pull")
    @Expose
    public boolean pull;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Permissions() {
    }

    /**
     * 
     * @param pull
     * @param admin
     * @param push
     */
    public Permissions(boolean admin, boolean push, boolean pull) {
        super();
        this.admin = admin;
        this.push = push;
        this.pull = pull;
    }

}
