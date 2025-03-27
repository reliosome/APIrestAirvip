package com.airvip.APIrest.controleurs;


import com.airvip.APIrest.repository.AvionRepository;
import com.airvip.APIrest.classes.Avion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avions")
public class AvionController {

    @Autowired
    private AvionRepository avionRepo;


    @GetMapping
    public List<Avion> getAllAvion(){
        return avionRepo.findAll();
    }

    // GET : Récupérer un avion par ID
    @GetMapping("/{id}")
    public ResponseEntity<Avion> getAvionById(@PathVariable int id) {
        Optional<Avion> avion = avionRepo.findById(id);
        return avion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    // POST : Ajouter un avion
    @PostMapping
    public Avion createAvion(@RequestBody Avion avion) {
        return avionRepo.save(avion);
    }



    // PUT : Mettre à jour un avion
    @PutMapping("/{id}")
    public ResponseEntity<Avion> updateAvion(@PathVariable int id, @RequestBody Avion updatedAvion) {
        return avionRepo.findById(id).map(avion -> {
            avion.setCapacite(updatedAvion.getCapacite());
            avion.setImage(updatedAvion.getImage());
            avion.setModele(updatedAvion.getModele());
            return ResponseEntity.ok(avionRepo.save(avion));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    // DELETE : Supprimer un avion
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvion(@PathVariable int id) {
        if (!avionRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        avionRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }









}
