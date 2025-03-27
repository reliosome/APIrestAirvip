package com.airvip.APIrest.controleurs;

import com.airvip.APIrest.classes.Avion;
import com.airvip.APIrest.classes.Image;
import com.airvip.APIrest.repository.AvionRepository;
import com.airvip.APIrest.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avions")
public class AvionController {

    @Autowired
    private AvionRepository avionRepo;

    @Autowired
    private ImageRepository imageRepo;

    // GET all avions
    @GetMapping
    public List<Avion> getAllAvion() {
        return avionRepo.findAll();
    }

    // GET avion by id
    @GetMapping("/{id}")
    public ResponseEntity<Avion> getAvionById(@PathVariable int id) {
        Optional<Avion> avion = avionRepo.findById(id);
        return avion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST create new avion with image URLs
    @PostMapping
    public ResponseEntity<Avion> createAvion(@RequestBody Avion avion) {
        // Ensure images are linked correctly
        if (avion.getImages() != null) {
            for (int i = 0; i < avion.getImages().size(); i++) {
                Image img = avion.getImages().get(i);
                img.setAvion(avion);
                img.setOrderIndex(i);
            }
        }
        return ResponseEntity.ok(avionRepo.save(avion));
    }

    // PUT update avion and images
    @PutMapping("/{id}")
    public ResponseEntity<Avion> updateAvion(@PathVariable int id, @RequestBody Avion updatedAvion) {
        return avionRepo.findById(id).map(avion -> {
            avion.setModele(updatedAvion.getModele());
            avion.setCapacite(updatedAvion.getCapacite());

            // Clear previous images (optional - depends on your logic)
            imageRepo.deleteAll(avion.getImages());

            // Set new images
            List<Image> newImages = new ArrayList<>();
            if (updatedAvion.getImages() != null) {
                for (int i = 0; i < updatedAvion.getImages().size(); i++) {
                    Image img = updatedAvion.getImages().get(i);
                    img.setAvion(avion);
                    img.setOrderIndex(i);
                    newImages.add(img);
                }
            }
            avion.setImages(newImages);

            return ResponseEntity.ok(avionRepo.save(avion));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE avion
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvion(@PathVariable int id) {
        if (!avionRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        avionRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
