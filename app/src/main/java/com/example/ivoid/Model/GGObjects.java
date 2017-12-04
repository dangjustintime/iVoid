package com.example.ivoid.Model;

import com.google.gson.annotations.SerializedName;
import com.example.ivoid.MainActivity;

/**
 * Created by alexcoalla on 12/4/17.
 */

public class GGObjects {

  //  SerializedName("id")    These line are causeing errors
    private String iD;

  //  SerializedName("patch")
    private String[] pat;

    public void setPatch(String[] P1) {this.pat = P1;}
    public String[] getPatch() {return pat;}

    public void setID(String I1) {this.iD = I1;}
    public String getiD() {return iD;}

}
