package com.airvip.APIrest.controleurs;
import com.airvip.APIrest.classes.Utilisateur;
import com.airvip.APIrest.repository.UtilisateurRepository;
import com.airvip.APIrest.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;

import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:5173\", allowCredentials = \"true")
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

    //POST : S'inscrire
    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Utilisateur utilisateur) {
        if (userRepository.existsByAdresseCourriel(utilisateur.getAdresse_courriel())) {
            return ResponseEntity.badRequest().body("Un utilisateur avec cet email existe déjà.");
        }

        if (!utilisateur.getRole().equalsIgnoreCase("admin") && !utilisateur.getRole().equalsIgnoreCase("client")) {
            return ResponseEntity.badRequest().body("Le rôle doit être 'admin' ou 'client'.");
        }

//        // Hachage du mot de passe avant l'enregistrement
//        utilisateur.setMot_de_passe(passwordEncoder.encode(utilisateur.getMot_de_passe()));

        System.out.println("Mot de passe avant encodage : " + utilisateur.getMot_de_passe());
        String encodedPassword = passwordEncoder.encode(utilisateur.getMot_de_passe());
        System.out.println("Mot de passe après encodage : " + encodedPassword);
        utilisateur.setMot_de_passe(encodedPassword);


        Utilisateur savedUser = userRepository.save(utilisateur);
        return ResponseEntity.status(201).body(savedUser);
    }

    @Autowired
    private JwtUtil jwtUtil;

    //POST : Se connecter
    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@RequestBody Utilisateur utilisateur) {
        Optional<Utilisateur> user = userRepository.findByAdresseCourriel(utilisateur.getAdresse_courriel());

        if (user.isEmpty() || !passwordEncoder.matches(utilisateur.getMot_de_passe(), user.get().getMot_de_passe())) {
            return ResponseEntity.status(401).body("Adresse courriel ou mot de passe incorrect.");
        }

        String token = jwtUtil.generateToken(user.get().getAdresse_courriel());
        System.out.println("Connexion réussie!");
        return ResponseEntity.ok().body(Map.of("token", token));
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
