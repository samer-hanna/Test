package com.example.retrofitjson.model.entry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Summary {
    @SerializedName("label")
    @Expose
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "label='" + label + '\'' +
                '}';
    }
}
