package com.airvip.APIrest.repository;
import com.airvip.APIrest.classes.Vol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface VolRepository extends JpaRepository<Vol,Integer> {
    // Tu peux ajouter des méthodes personnalisées ici
}

