package com.aj.spring.finalex.service;


import com.aj.spring.finalex.entity.Reservation;
import com.aj.spring.finalex.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepo;

    public Reservation save(Reservation reservation) {
        return reservationRepo.save(reservation);
    }

    public List<Reservation> getAll() {
        return reservationRepo.findAll();
    }
    public Optional<Reservation> findById(String id) {
        return reservationRepo.findById(id);
    }
}