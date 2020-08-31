import de.team38.semesterprojekt.carpark.category.Category;
import de.team38.semesterprojekt.carpark.ticket.DefaultTicket;
import de.team38.semesterprojekt.carpark.ticket.SubTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTicket {
    Category category1 = new Category("Cat1", 8f);
    Category category2 = new Category("Cat2", 12f);

    DefaultTicket ticket1;
    DefaultTicket ticket2;
    DefaultTicket ticket3;
    SubTicket sticket1;
    SubTicket sticket2;

    long systemtime;

    @BeforeEach
    void setup(){
        systemtime = System.currentTimeMillis();
        ticket1 = new DefaultTicket("X1", category1);
        ticket2 = new DefaultTicket("X2", category2, System.currentTimeMillis());
        ticket3 = new DefaultTicket("X3", category1, systemtime);


        sticket1 = new SubTicket("T1", category1, "020202", "DD");
        sticket2 = new SubTicket("T2", category1, "020201", "DD3");
    }

    @Test
    @DisplayName("Test Timeset functions")
    void testTimeSetter(){
        assertEquals(systemtime, ticket3.getEnterTimeStamp());
        assertTrue(ticket1.setEnterTimeStamp(systemtime));
        assertFalse(ticket1.setEnterTimeStamp(0));
        assertEquals(systemtime, ticket1.getEnterTimeStamp());
        assertEquals(0,ticket1.getExitTimeStamp());
        assertTrue(ticket1.setExitTimeStamp(systemtime));
        assertFalse(ticket1.setExitTimeStamp(0));
        assertEquals(systemtime, ticket1.getExitTimeStamp());

        ticket1.setPayed(true);
        assertTrue(ticket1.isPayed());

        assertNotEquals(ticket2, ticket1);
        assertEquals(ticket1, ticket1);
    }
    @Test
    @DisplayName("Test SubTicket functions")
    void testSubTickets() {
        assertEquals("DD",sticket1.getCustomerID());
        assertEquals("020202", sticket1.getValidUntil());
        assertTrue(sticket1.setCustomerID("D1Test"));
        assertFalse(sticket1.setCustomerID(null));
        assertEquals("D1Test",sticket1.getCustomerID());
        assertTrue(sticket1.renew("020210"));
        assertFalse(sticket1.renew(null));
        assertEquals("020210", sticket1.getValidUntil());
        assertNotEquals(sticket1, sticket2);
        assertEquals(sticket1, sticket1);

    }
    @Test
    @DisplayName("Test ticket functions")
    void testTicketFunctions(){
        assertEquals("X1", ticket1.getTicketNumber());
        assertTrue(ticket1.setTicketNumber("XD4"));
        assertEquals("XD4", ticket1.getTicketNumber());
        assertFalse(ticket1.setTicketNumber(null));
    }
    @Test
    @DisplayName("Testing Price calc")
    void testPriceCalc(){

    }
}
