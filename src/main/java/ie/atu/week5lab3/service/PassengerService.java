package ie.atu.week5lab3.service;


import ie.atu.week5lab3.model.Passenger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    private final List<Passenger> store = new ArrayList<>();


    public List<Passenger> findAll() {

        return new ArrayList<>(store);

    }

    public Optional<Passenger> findById(String id) {

        for (Passenger p : store) {
            if(p.getPassengerID().equals(id)) {
                return Optional.of(p);
            }
        }

        return Optional.empty();
    }


    public Passenger create(Passenger passenger) {

        if(findById(passenger.getPassengerID()).isPresent()) {

            throw new IllegalArgumentException("Passenger with ID " + passenger.getPassengerID() + " already exists");


        }

        store.add(passenger);
        return passenger;

    }

    public Passenger update(Passenger passenger) {

        if(findById(passenger.getPassengerID()).isPresent()) {

            store.set(store.indexOf(findById(passenger.getPassengerID()).get()), passenger);
            return passenger;



        }

        throw new IllegalArgumentException("Passenger with ID " + passenger.getPassengerID() + " does not exist");
    }




}
