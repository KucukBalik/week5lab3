package ie.atu.week5lab3.controller;


import ie.atu.week5lab3.model.Passenger;
import ie.atu.week5lab3.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getAll(@PathVariable String id) {

        Optional<Passenger> maybe = passengerService.findById(id);

        if (maybe.isPresent()) {
            return ResponseEntity.ok(maybe.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Passenger> create(@Valid @RequestBody Passenger passenger) {

        Passenger created = passengerService.create(passenger);

        return ResponseEntity
                .created(URI.create(URI.create("/api/passenger/") + created.getPassengerID()))
                .body(created);

    }


    @PutMapping("/{id}")
    public ResponseEntity<Passenger> update(@Valid @RequestBody Passenger passenger, @PathVariable String id) {

        if(passengerService.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Passenger updated = passengerService.update(passenger);

        return ResponseEntity.ok(updated);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Passenger> delete(@PathVariable String id) {

        if(passengerService.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Passenger passenger = passengerService.findById(id).get();
        Passenger deleted = passengerService.delete(passenger);

        return ResponseEntity.ok(deleted);

    }
}