package com.airvip.APIrest.controleurs;

import com.airvip.APIrest.classes.Reservation;
import com.airvip.APIrest.classes.Utilisateur;
import com.airvip.APIrest.classes.Vol;
import com.airvip.APIrest.repository.ReservationRepository;
import com.airvip.APIrest.repository.UtilisateurRepository;
import com.airvip.APIrest.repository.VolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private VolRepository volRepository;

    // Get all reservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Get reservations by vol ID
    @GetMapping("/vol/{volId}")
    public List<Reservation> getReservationsByVolId(@PathVariable int volId) {
        return reservationRepository.findByVolId(volId);
    }

    // Create a new reservation using email instead of ID
    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody Map<String, Object> body) {
        String email = (String) body.get("email");
        int volId = (int) body.get("volId");

        Optional<Utilisateur> utilisateur = utilisateurRepository.findByAdresseCourriel(email);
        Optional<Vol> vol = volRepository.findById(volId);

        if (utilisateur.isEmpty() || vol.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur ou Vol non trouvé.");
        }

        Vol selectedVol = vol.get();

        selectedVol.setDisponibilite("Non Disponible");
        volRepository.save(selectedVol);

        Reservation reservation;
        if (body.containsKey("dateReservation")) {
            String dateStr = (String) body.get("dateReservation");
            LocalDateTime dateReservation = LocalDateTime.parse(dateStr);
            reservation = new Reservation(utilisateur.get(), selectedVol, dateReservation);
        } else {
            reservation = new Reservation(utilisateur.get(), selectedVol);
        }

        Reservation saved = reservationRepository.save(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Get reservations by utilisateur ID (if needed elsewhere)
    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<Reservation>> getReservationsByUtilisateurId(@PathVariable int utilisateurId) {
        List<Reservation> reservations = reservationRepository.findByUtilisateurId(utilisateurId);
        return ResponseEntity.ok(reservations);
    }

    // Delete a reservation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable int id) {
        if (!reservationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        /*
        List<Reservation> selectedRes = getReservationsByVolId(id);
        Vol selectedVol = selectedRes.getVol();
        selectedVol.setDisponibilite("Disponible");
        volRepository.save(selectedVol);

         */

        reservationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
