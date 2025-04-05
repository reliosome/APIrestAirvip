package com.airvip.APIrest.repository;
import com.airvip.APIrest.classes.Avion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AvionRepository extends JpaRepository<Avion,Integer> {
    // Tu peux ajouter des méthodes personnalisées ici
    @Query(value = "SELECT * FROM avion WHERE avion_id = :id", nativeQuery = true)
    Avion findAvionById(@Param("id") Integer id);
}

