package com.airvip.APIrest.controleurs;

import com.airvip.APIrest.DTO.ImageDTO;
import com.airvip.APIrest.classes.Aeroport;
import com.airvip.APIrest.classes.Avion;
import com.airvip.APIrest.classes.Image;
import com.airvip.APIrest.classes.Vol;
import com.airvip.APIrest.repository.AeroportRepository;
import com.airvip.APIrest.repository.AvionRepository;
import com.airvip.APIrest.repository.ImageRepository;
import com.airvip.APIrest.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173\", allowCredentials = \"true")
@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;


    // GET : Récupérer tous les produits
    @GetMapping
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    // GET : Récupérer un produit par ID
    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable int id) {
        Optional<Image> img = imageRepository.findById(id);
        return img.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST : Ajouter un produit
    @PostMapping
    public ResponseEntity<Image> createImage(@RequestBody ImageDTO imageDTO) {

        Image img =  imageService.createImage(
                imageDTO.getImage_id(),
                imageDTO.getUrl(),
                imageDTO.getOrderIndex(),
                imageDTO.getAvion_id()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(img);
    }
}
