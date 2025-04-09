package com.airvip.APIrest.DTO;

import com.airvip.APIrest.classes.Avion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ImageDTO {
    private int image_id;
    private String url;
    private int orderIndex;
    private int avion_id;

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public int getAvion_id() {
        return avion_id;
    }

    public void setAvion_id(int avion_id) {
        this.avion_id = avion_id;
    }
}
