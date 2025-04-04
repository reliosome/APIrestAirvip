package com.airvip.APIrest.service;


import com.airvip.APIrest.classes.Aeroport;
import com.airvip.APIrest.classes.Avion;
import com.airvip.APIrest.repository.AeroportRepository;
import com.airvip.APIrest.repository.AvionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class AvionService {

    @Autowired
    private AvionRepository avionRepository;

    // GET : Récupérer tous les produits
    @GetMapping
    public List<Avion> getAllAvions() {
        return avionRepository.findAll();
    }

    // GET : Récupérer un produit par ID
    @GetMapping("/{id}")
    public Avion getAvionById(@PathVariable int id) {
        return avionRepository.findById(id).orElse(null);
    }

}
