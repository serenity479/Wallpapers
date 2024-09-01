package com.example.wallpapers;

import java.util.ArrayList;

public class SearchModel {

    ArrayList<ImageModel> photos;

    public SearchModel(ArrayList<ImageModel> models) {
        this.photos = models;
    }

    public ArrayList<ImageModel> getModels() {
        return photos;
    }

    public void setModels(ArrayList<ImageModel> models) {
        this.photos = models;
    }
}
