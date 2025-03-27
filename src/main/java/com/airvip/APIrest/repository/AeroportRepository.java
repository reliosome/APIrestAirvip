package com.airvip.APIrest.repository;
import com.airvip.APIrest.classes.Aeroport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AeroportRepository extends JpaRepository<Aeroport,Integer> {
    // Tu peux ajouter des méthodes personnalisées ici
    @Query(value = "SELECT * FROM aeroport WHERE id_aeroport = :id", nativeQuery = true)
    Aeroport findAeroportById(@Param("id") Integer id);

}

