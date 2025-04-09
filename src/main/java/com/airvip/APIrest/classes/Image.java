package com.airvip.APIrest.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Image", schema = "dbo")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int image_id;

    @Column(nullable = false)
    private String url;

    @Column(name = "order_index")
    private int orderIndex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avion_id", nullable = false)
    @JsonIgnore
    private Avion avion;

    public Image(){}

    public Image(int image_id, String url, int orderIndex, Avion avion) {
        this.image_id = image_id;
        this.url = url;
        this.orderIndex = orderIndex;
        this.avion = avion;
    }

    // Getters & Setters
    public int getImage_id() { return image_id; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public int getOrderIndex() { return orderIndex; }

    public void setOrderIndex(int orderIndex) { this.orderIndex = orderIndex; }

    public Avion getAvion() { return avion; }

    public void setAvion(Avion avion) { this.avion = avion; }
}
