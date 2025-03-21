package com.airvip.APIrest.security;

import com.airvip.APIrest.classes.Utilisateur;
import com.airvip.APIrest.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UtilisateurDetailsService implements UserDetailsService {
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByAdresseCourriel(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec cet email: " + email));

        return new org.springframework.security.core.userdetails.User(
                utilisateur.getAdresse_courriel(),
                utilisateur.getMot_de_passe(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().toUpperCase()))
        );
    }
}