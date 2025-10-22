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
    public ResponseEntity<?> getAll(@PathVariable String id) {

        Optional<Passenger> maybe = passengerService.findById(id);

        if (maybe.isPresent()) {
            return ResponseEntity.ok(maybe.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Passenger with ID " + id + " does not exist");
        }
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Passenger passenger) {

        Passenger created = passengerService.create(passenger);

        return ResponseEntity
                .created(URI.create(("/api/passenger/") + created.getPassengerID()))
                .body("Passenger with ID " + created.getPassengerID() + " has been created");

    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Passenger passenger, @PathVariable String id) {

        if(passengerService.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Passenger with ID: " + id + " could not found.");
        }else{
            Passenger updated = passengerService.update(passenger);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Passenger with ID: " + id + " has been updated.");

        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {

        if(passengerService.findById(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Passenger with ID: " + id + " could not found.");
        }else{
            Passenger passenger = passengerService.findById(id).get();
            Passenger deleted = passengerService.delete(passenger);

            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Passenger with ID: " + id + " has been deleted.");
        }


    }
}