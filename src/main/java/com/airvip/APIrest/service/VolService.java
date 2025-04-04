package com.airvip.APIrest.service;


import com.airvip.APIrest.classes.Aeroport;
import com.airvip.APIrest.classes.Avion;
import com.airvip.APIrest.classes.Vol;
import com.airvip.APIrest.controleurs.AeroportController;
import com.airvip.APIrest.repository.AeroportRepository;
import com.airvip.APIrest.repository.AvionRepository;
import com.airvip.APIrest.repository.VolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VolService {

    @Autowired
    AeroportService aeroService;
    @Autowired
    AvionService avionService;

    @Autowired
    private VolRepository volRepo;

    public Vol createVol(double temps, String disponibilite, int nb_place, int depart_id, int arrive_id, int avion_id) {

        // Récupération des objets associés via les repositories

        Aeroport aeroportDepart = aeroService.getAeroportById(depart_id);
        Aeroport aeroportArrive = aeroService.getAeroportById(arrive_id);
        Avion avion = avionService.getAvionById(avion_id);

        // Création de l'objet Vol
        Vol vol = new Vol(temps, disponibilite, nb_place, aeroportDepart, aeroportArrive, avion);

        // Enregistrer le vol dans la base de données
        return volRepo.save(vol);
    }


    public List<Vol> getAllVols(){
        return volRepo.findAll();
    }
}

