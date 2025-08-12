package com.aj.spring.finalex.controller;

import com.aj.spring.finalex.entity.Reservation;
import com.aj.spring.finalex.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    @Autowired
    private ReservationService reservationService;

    // GET: Get all reservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAll();
    }

    // GET: Get reservation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable String id) {
        return reservationService.findById(id)
                .map(reservation -> ResponseEntity.ok().body(reservation))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Create a new reservation
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation saved = reservationService.save(reservation);
        return ResponseEntity.ok(saved);
    }
}