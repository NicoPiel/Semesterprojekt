import de.team38.semesterprojekt.carpark.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCategoryClass {
    Category category;
    @BeforeEach
    public void setup(){
        category = new Category("Test", 1.5f);
    }
    @Test
    @DisplayName("Test if Category can be created")
    public void testCreate(){
        category = new Category("TestTest", 10.0f);
        assertEquals("TestTest", category.getName());
    }

    @Test
    @DisplayName("Test if name can be changed")
    public void testName(){
        assertEquals("Test",category.getName());
        assertTrue(category.setName("Test1"));
        assertEquals("Test1", category.getName());
        assertFalse(category.setName(null));
        assertEquals("Test1", category.getName());
    }
    @Test
    @DisplayName("Test if price can be changed")
    public void testPrice(){
        assertEquals(1.5f, category.getPricePerHour());
        assertTrue(category.setPricePerHour(2.0f));
        assertEquals(2.0f, category.getPricePerHour());
        assertFalse(category.setPricePerHour(-10.0f));
        assertEquals(2.0f, category.getPricePerHour());
        assertTrue(category.setPricePerHour(0.0f));
        assertEquals(0.0f, category.getPricePerHour());
    }
}
