import de.team38.semesterprojekt.carpark.category.Category;
import de.team38.semesterprojekt.carpark.parkingspace.ParkingSpace;
import de.team38.semesterprojekt.carpark.parkingspace.ParkingSpaces;
import de.team38.semesterprojekt.carpark.ticket.DefaultTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestParkingSpacesClass {
    Category category = new Category("T", 1.4f );
    Category category1 = new Category("T1", 1.8f );
    Category category2 = new Category("T2", 1.9f );
    ParkingSpace p = new ParkingSpace(7, category);
    ParkingSpace p1 = new ParkingSpace(8, category);
    DefaultTicket ticket1;
    DefaultTicket ticket2;
    DefaultTicket ticket4;
    @BeforeEach
    void setup(){
        ParkingSpaces.resetParkingSpaces();
        ticket1 = new DefaultTicket("1", category, System.currentTimeMillis());
        ticket2 = new DefaultTicket("2", category, System.currentTimeMillis());
        DefaultTicket ticket3 = new DefaultTicket("3", category1, System.currentTimeMillis());
        ticket4 = new DefaultTicket("4", category2, System.currentTimeMillis());
        ParkingSpaces.addParkingSpace(new ParkingSpace(1, category));
        ParkingSpaces.addParkingSpace(new ParkingSpace(2, ticket1, category));
        ParkingSpaces.addParkingSpace(new ParkingSpace(3, ticket2, category));
        ParkingSpaces.addParkingSpace(new ParkingSpace(4, ticket3, category1));
        ParkingSpaces.addParkingSpace(new ParkingSpace(5, category));
        ParkingSpaces.addParkingSpace(new ParkingSpace(6, category));
        ParkingSpaces.addParkingSpace(p);
    }
    @Test
    @DisplayName("Testing adding and removing")
    void testAddRemoveSpace(){
        assertFalse(ParkingSpaces.addParkingSpace(p));
        assertFalse(ParkingSpaces.removeParkingSpace(p1));
        assertTrue(ParkingSpaces.addParkingSpace(p1));
        assertTrue(ParkingSpaces.removeParkingSpace(p));
    }

    @Test
    @DisplayName("Testing counting functions")
    void testCounting(){
        assertEquals(7, ParkingSpaces.countSpaces());
        assertEquals(4, ParkingSpaces.countFreeParkingSpaces());
        assertEquals(4, ParkingSpaces.countFreeParkingSpacesByCategory(category));
        assertEquals(6, ParkingSpaces.countSpacesByCategory(category));
        assertEquals(1, ParkingSpaces.countSpacesByCategory(category1));
        assertEquals(0, ParkingSpaces.countFreeParkingSpacesByCategory(category1));
        assertEquals(3, ParkingSpaces.countOccupiedParkingSpaces());
        assertEquals(2, ParkingSpaces.countOccupiedParkingSpacesByCategory(category));
    }
    @Test
    @DisplayName("Testing findByID")
    void testFindByID(){
        Optional<ParkingSpace> p1 = ParkingSpaces.findParkingSpaceByID(7);
        assertTrue(p1.isPresent());
        assertEquals(p,ParkingSpaces.findParkingSpaceByID(7).get());
    }
    /*@Test
    @DisplayName("Testing find Occupant")
    void testFindOccupant(){
        p.setOccupant(ticket1);
        assertEquals(ticket1, p.getOccupant());
        assertTrue(ParkingSpaces.occupantAlreadyUsingParkSpace(ticket4));
        /*assertFalse(ParkingSpaces.occupantAlreadyUsingParkSpace(ticket4));
        assertTrue(ParkingSpaces.occupantAlreadyUsingParkSpace(ticket2));


    }*/
}
