package PassengerServiceTest;

import ie.atu.week5lab3.model.Passenger;
import ie.atu.week5lab3.service.PassengerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerServiceTest {

    private PassengerService passengerService;

    @BeforeEach
    void setup() {

        passengerService = new PassengerService();

    }


    @Test
    void createThenFindById() {
        Passenger p = Passenger.builder()
                .passengerID("P1")
                .name("Emre")
                .email("emre.atu.ie")
                .build();

        passengerService.create(p);

        Optional<Passenger> found = passengerService.findById("P1");
        assertTrue(found.isPresent());
        assertEquals("P1", found.get().getPassengerID());

    }


    @Test
    void duplicateIdThrows(){
        passengerService.create(Passenger.builder()
                .passengerID("P2")
                .name("Emre")
                .email("emre.atu.ie")
                .build());

        assertThrows(IllegalArgumentException.class, ()->
                passengerService.create(Passenger.builder()
                        .passengerID("P2")
                        .name("EmreDup")
                        .email("emredup.atu.ie")
                        .build()));
    }

    @Test
    void updateSuccess(){

        Passenger p = passengerService.create(Passenger.builder()
                .passengerID("P1")
                .name("Emre")
                .email("emre.atu.ie")
                .build());


        Passenger updated = new Passenger("P1", "Emre Updated", "emre.atu.ie");

        passengerService.update(updated);

        Passenger found = passengerService.findById("P1").get();

        assertEquals("Emre Updated", found.getName());
        assertEquals("emre.atu.ie", found.getEmail());


    }


    @Test
    void updateFail(){

        List<Passenger> passengers = new ArrayList<Passenger>();

        Passenger p = passengerService.create(Passenger.builder()
                .passengerID("P1")
                .name("Emre")
                .email("emre.atu.ie")
                .build());

        assertThrows(IllegalArgumentException.class,
                ()-> passengerService.update(new Passenger("P3", "EmreUpdated", "emre.atu.ie")));


    }

    @Test
    void deleteSuccess(){


        Passenger p = passengerService.create(Passenger.builder()
                .passengerID("P1")
                .name("Emre")
                .email("emre.atu.ie")
                .build());

        passengerService.delete(p);


    }


    @Test
    void deleteFail(){


        Passenger p = passengerService.create(Passenger.builder()
                .passengerID("P1")
                .name("Emre")
                .email("emre.atu.ie")
                .build());

        assertThrows(IllegalArgumentException .class , ()->
                passengerService.delete(new Passenger("P3", "EmreUpdated", "emre.atu.ie")));


    }

}
