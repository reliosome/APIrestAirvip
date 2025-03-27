package com.airvip.APIrest.service;


import com.airvip.APIrest.classes.Aeroport;
import com.airvip.APIrest.repository.AeroportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class AeroportService {

    @Autowired
    private AeroportRepository aeroRepository;


    // GET : Récupérer tous les produits
    @GetMapping
    public List<Aeroport> getAllAeroports() {
        return aeroRepository.findAll();
    }

    // GET : Récupérer un produit par ID
    @GetMapping("/{id}")
    public Aeroport getAeroportById(@PathVariable int id) {
        Aeroport aeroport = aeroRepository.findById(id).orElse(null);
        return aeroport;//.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST : Ajouter un produit
    @PostMapping
    public Aeroport createAeroport(@RequestBody Aeroport aeroport) {
        return aeroRepository.save(aeroport);
    }

    // PUT : Mettre à jour un produit
    @PutMapping("/{id}")
    public ResponseEntity<Aeroport> updateAeroport(@PathVariable int id, @RequestBody Aeroport updatedAeroport) {
        return aeroRepository.findById(id).map(aeroport -> {
            aeroport.setCode_IATA(updatedAeroport.getCode_IATA());
            aeroport.setVille(updatedAeroport.getVille());
            aeroport.setPays(updatedAeroport.getPays());
            aeroport.setDistance_montreal(updatedAeroport.getDistance_montreal());
            return ResponseEntity.ok(aeroRepository.save(aeroport));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE : Supprimer un produit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAeroport(@PathVariable int id) {
        if (!aeroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        aeroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
