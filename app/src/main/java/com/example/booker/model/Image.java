package com.example.booker.model;

import android.net.Uri;

public class Image {
    private String name;
    private String extension;
    private Uri url;

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Image() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getUrl() {
        return url;
    }

    public void setUrl(Uri url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Image{" +
                "name='" + name + '\'' +
                ", extension='" + extension + '\'' +
                ", url=" + url +
                '}';
    }
}
