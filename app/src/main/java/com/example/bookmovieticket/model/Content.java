package com.example.bookmovieticket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Content  implements Serializable {
    @SerializedName("content")
    @Expose
    List<Movie> content;

    public Content(List<Movie> content) {
        this.content = content;
    }

    public Content() {
    }

    public List<Movie> getContent() {
        return content;
    }

    public void setContent(List<Movie> content) {
        this.content = content;
    }
}
