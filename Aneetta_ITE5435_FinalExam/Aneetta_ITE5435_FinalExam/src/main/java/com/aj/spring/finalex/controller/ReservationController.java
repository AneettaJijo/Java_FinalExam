package com.aj.spring.finalex.controller;

import com.aj.spring.finalex.entity.Customer;
import com.aj.spring.finalex.entity.Payment;
import com.aj.spring.finalex.entity.Reservation;
import com.aj.spring.finalex.service.ReservationService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ReservationController {

    private static final Logger log = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationService service;

    @GetMapping("/reservation/new")
    public String showForm(Model model) {
        Reservation reservation = new Reservation();
        reservation.setCustomer(new Customer());
        reservation.setPayment(new Payment());
        model.addAttribute("reservation", reservation);
        return "reservation";
    }

    @PostMapping("/reservation/save")
    public String saveReservation(@Valid Reservation reservation, BindingResult result, Model model) {

        //  Log if there are binding errors
        if (result.hasErrors()) {
            log.error("Validation failed: {}", result.toString());
            result.getAllErrors().forEach(err -> log.error("Error: {}", err.toString()));
            return "reservation"; // Stay on form
        }

        //  Set defaults
        if (reservation.getStatus() == null) {
            reservation.setStatus("Pending");
        }
        if (reservation.getReservationDate() == null) {
            reservation.setReservationDate(new java.util.Date());
        }

        // Save to DB
        try {
            log.info("Saving reservation: {}", reservation);
            service.save(reservation);
            log.info("Saved successfully with ID: {}", reservation.getId());
        } catch (Exception e) {
            log.error("Failed to save reservation", e);
            model.addAttribute("error", "Failed to save: " + e.getMessage());
            return "reservation";
        }

        return "redirect:/reservation/thankyou";
    }

    @GetMapping("/reservation/thankyou")
    public String thankYou() {
        return "thankYou";
    }
}