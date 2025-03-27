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
    AvionRepository avionRepos;

    @Autowired
    private VolRepository volRepo;

    public Vol createVol(double temps, String disponibilite, int nb_place, int depart_id, int arrive_id, int avion_id) {
        // Calcul du prix
        double prix = (temps < 2) ? 500 : 80 * temps;

        // Récupération des objets associés via les repositories
        depart_id = 1;
        arrive_id = 2;
        avion_id = 8;
        Aeroport aeroportDepart = aeroService.getAeroportById(depart_id);//.orElseThrow(() -> new NoSuchElementException("Aéroport départ non trouvé"));
        Aeroport aeroportArrive = aeroService.getAeroportById(arrive_id);//.orElseThrow(() -> new NoSuchElementException("Aéroport arrivée non trouvé"));
        Avion avion = avionRepos.findById(avion_id).orElseThrow(() -> new NoSuchElementException("Avion non trouvé"));

        // Création de l'objet Vol
        Vol vol = new Vol(temps, disponibilite, nb_place, aeroportDepart, aeroportArrive, avion);

        // Enregistrer le vol dans la base de données
        return volRepo.save(vol);
    }


    public List<Vol> getAllVols(){
        return volRepo.findAll();
    }
}

