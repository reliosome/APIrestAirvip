package com.airvip.APIrest.service;

import com.airvip.APIrest.classes.Aeroport;
import com.airvip.APIrest.classes.Avion;
import com.airvip.APIrest.classes.Image;
import com.airvip.APIrest.classes.Vol;
import com.airvip.APIrest.repository.ImageRepository;
import com.airvip.APIrest.repository.VolRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    AvionService avionService;

    @Autowired
    private ImageRepository imageRepository;

    public Image createImage(int image_id, String url, int orderIndex, int avion_id) {

        // Récupération des objets associés via les repositories
        Avion avion = avionService.getAvionById(avion_id);

        // Création de l'objet Vol
        Image img = new Image(image_id, url, orderIndex, avion);

        // Enregistrer le vol dans la base de données
        return imageRepository.save(img);
    }
}
