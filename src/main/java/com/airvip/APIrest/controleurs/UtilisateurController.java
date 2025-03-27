package com.airvip.APIrest.controleurs;
import com.airvip.APIrest.classes.Utilisateur;
import com.airvip.APIrest.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilisateurs")

public class UtilisateurController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UtilisateurRepository userRepository;

    // GET : Récupérer tous les utilisateurs
    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return userRepository.findAll();
    }

    // GET : Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable int id) {
        Optional<Utilisateur> utilisateur = userRepository.findById(id);
        return utilisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST : Ajouter un utilisateur
    @PostMapping
    public ResponseEntity<?> createUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
        if (userRepository.existsByAdresseCourriel(utilisateur.getAdresse_courriel())) {
            return ResponseEntity.badRequest().body("Un utilisateur avec cet email existe déjà.");
        }

        if (!utilisateur.getRole().equalsIgnoreCase("admin") && !utilisateur.getRole().equalsIgnoreCase("client")) {
            return ResponseEntity.badRequest().body("Le rôle doit être 'admin' ou 'client'.");
        }

        utilisateur.setMot_de_passe(passwordEncoder.encode(utilisateur.getMot_de_passe())); // Hachage du mot de passe
        Utilisateur savedUser = userRepository.save(utilisateur);
        return ResponseEntity.ok(savedUser);
    }

    // PUT : Mettre à jour un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable int id, @Valid @RequestBody Utilisateur updatedUtilisateur) {
        return userRepository.findById(id).map(utilisateur -> {
            utilisateur.setAdresse_courriel(updatedUtilisateur.getAdresse_courriel());
            utilisateur.setPrenom(updatedUtilisateur.getPrenom());
            utilisateur.setNom(updatedUtilisateur.getNom());

            // Si un nouveau mot de passe est fourni, on le hache avant de le sauvegarder
            if (!updatedUtilisateur.getMot_de_passe().isBlank()) {
                utilisateur.setMot_de_passe(passwordEncoder.encode(updatedUtilisateur.getMot_de_passe()));
            }

            return ResponseEntity.ok(userRepository.save(utilisateur));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE : Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable int id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
