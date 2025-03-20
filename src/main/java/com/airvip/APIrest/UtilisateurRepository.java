package com.airvip.APIrest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer>{
    //Optional<Utilisateur> findByAdresseCourriel(String adresse_courriel);

   // boolean existsByAdresseCourriel(String adresse_courriel);

   // void deleteByAdresseCourriel(String adresse_courriel);
}
