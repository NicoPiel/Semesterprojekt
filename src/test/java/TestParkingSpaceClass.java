import de.team38.semesterprojekt.carpark.category.Category;
import de.team38.semesterprojekt.carpark.parkingspace.ParkingSpace;
import de.team38.semesterprojekt.carpark.ticket.DefaultTicket;
import de.team38.semesterprojekt.carpark.ticket.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestParkingSpaceClass {
    Category category;
    Category category2;
    DefaultTicket ticket;
    DefaultTicket ticket2;
    ParkingSpace parkingSpace;
    @BeforeEach
    void setup(){
        category = new Category("Test", 1.4f);
        category = new Category("T2", 90f);
        ticket = new DefaultTicket("1", category, System.currentTimeMillis());
        ticket2 = new DefaultTicket("2", category2, System.currentTimeMillis());
        parkingSpace = new ParkingSpace(1,ticket, category);
    }
    @Test
    @DisplayName("Test Occupied in ParkingSpace")
    void testOccupied() {
        assertEquals(ticket.getTicketNumber(), parkingSpace.getOccupant().getTicketNumber());
        Ticket ticket1 = new DefaultTicket("2", category, System.currentTimeMillis());
        assertTrue(parkingSpace.setOccupant(ticket1));
        assertEquals(ticket1.getTicketNumber(), parkingSpace.getOccupant().getTicketNumber());
        ParkingSpace parkingSpace1 = new ParkingSpace(1, category);
        assertNull(parkingSpace1.getOccupant());
    }
    @Test
    @DisplayName("Test ParkingSpaceNumber")
    void testSpaceNumber(){
        assertEquals(1, parkingSpace.getParkingSpaceNumber());
        assertTrue(parkingSpace.setParkingSpaceNumber(2));
        assertFalse(parkingSpace.setParkingSpaceNumber(0));
        assertEquals(2, parkingSpace.getParkingSpaceNumber());
    }
    @Test
    @DisplayName("Test Categoryhandling")
    void testCategory(){

        assertEquals(category, parkingSpace.getCategory());
        Category category2 = new Category("Test2", 5.4f);
        assertTrue(parkingSpace.setCategory(category2));
        assertFalse(parkingSpace.setCategory(null));
        assertEquals(category2, parkingSpace.getCategory());
        assertNotEquals(parkingSpace, category2);
    }
    @Test
    @DisplayName("Test Occupant functions")
    void testOccupantFunctions() {
        assertEquals(ticket ,parkingSpace.getOccupant());
        assertTrue(parkingSpace.removeOccupant());
        assertFalse(parkingSpace.removeOccupant());
        assertTrue(parkingSpace.setOccupant(ticket));
        assertFalse(parkingSpace.setOccupant(null));
        ticket2.setTicketCategory(category2);
        assertFalse(parkingSpace.setOccupant(ticket2));
        assertEquals(ticket,parkingSpace.getOccupant());
        assertFalse(parkingSpace.setOccupant(ticket2));
    }
}
