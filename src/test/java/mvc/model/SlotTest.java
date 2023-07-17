
        package mvc.model;

        import static org.junit.jupiter.api.Assertions.*;
        import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.*;

class SlotTest {
    @Test
    public void testGetId() {
        Slot slot = new Slot(1, "Slot 1");
        assertEquals(1, slot.getId());
    }
    @Test
    public void testSetId() {
        Slot slot = new Slot();
        slot.setId(2);
        assertEquals(2, slot.getId());
    }
    @Test
    public void testGetName() {
        Slot slot = new Slot(3, "Slot 3");
        assertEquals("Slot 3", slot.getName());
    }
    @Test
    public void testSetName() {
        Slot slot = new Slot();
        slot.setName("Slot 4");
        assertEquals("Slot 4", slot.getName());
    }
}