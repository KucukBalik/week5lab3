package PassengerServiceTest;

import ie.atu.week5lab3.model.Passenger;
import ie.atu.week5lab3.service.PassengerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}
