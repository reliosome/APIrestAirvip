package com.airvip.APIrest.controleurs;


import com.airvip.APIrest.DTO.VolDTO;
import com.airvip.APIrest.classes.Vol;

import com.airvip.APIrest.repository.VolRepository;
import com.airvip.APIrest.service.VolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vols")
public class VolController {

    @Autowired
    private VolService volService; // Injection du service VolService

    @Autowired
    private VolRepository volRepository;

    // GET : Récupérer tous les produits
    @GetMapping
    public List<Vol> getAllVols() {
        return volRepository.findAll();
    }

    // GET : Récupérer un produit par ID
    @GetMapping("/{id}")
    public ResponseEntity<Vol> getVolById(@PathVariable int id) {
        Optional<Vol> vol = volRepository.findById(id);
        return vol.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST : Ajouter un vol
    @PostMapping
    public ResponseEntity<Vol> createVol(@RequestBody VolDTO volDTO) {
        // Utiliser le service pour créer le vol
        Vol vol = volService.createVol(
                volDTO.getTemps(),
                volDTO.getDisponibilite(),
                volDTO.getNb_place(),
                volDTO.getDepart_id(),
                volDTO.getArrive_id(),
                volDTO.getAvion_id()
        );

        // Retourner une réponse HTTP 201 (Created) avec le vol créé
        return ResponseEntity.status(HttpStatus.CREATED).body(vol);
    }

    // PUT : Mettre à jour un produit
    @PutMapping("/{id}")
    public ResponseEntity<Vol> updateVol(@PathVariable int id, @RequestBody VolDTO updatedVol) {

        Vol volChanger = volService.createVol(
                updatedVol.getTemps(),
                updatedVol.getDisponibilite(),
                updatedVol.getNb_place(),
                updatedVol.getDepart_id(),
                updatedVol.getArrive_id(),
                updatedVol.getAvion_id()

        );
        return volRepository.findById(id).map( vol -> {
            vol.setAeroportArrive(volChanger.getAeroportArrive());
            vol.setAeroportDepart(volChanger.getAeroportDepart());
            vol.setAvion(volChanger.getAvion());
            vol.setDisponibilite(volChanger.getDisponibilite());
            vol.setTemps(volChanger.getTemps());
            vol.setNb_place(volChanger.getNb_place());
            return ResponseEntity.ok(volRepository.save(vol));
        }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    // DELETE : Supprimer un produit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVol(@PathVariable int id) {
        if (!volRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        volRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
