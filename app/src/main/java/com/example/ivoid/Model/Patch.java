package com.example.ivoid.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Justin Dang on 12/7/2017.
 */

public class Patch {
    @SerializedName("patch")
    private String patch;
    public String getPatch() { return patch; }
    public void setPatch(String patch) { this.patch = patch; }
}
