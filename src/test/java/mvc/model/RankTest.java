package mvc.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class RankTest {
    @Test
    public void testGetId() {
        Rank rank = new Rank(1, "A");
        int id = rank.getId();
        assertEquals(1, id);
    }
    @Test
    public void testSetId() {
        Rank rank = new Rank();
        rank.setId(2);
        assertEquals(2, rank.getId());
    }
    @Test
    public void testGetName() {
        Rank rank = new Rank(3, "B");
        String name = rank.getName();
        assertEquals("B", name);
    }
    @Test
    public void testSetName() {
        Rank rank = new Rank();
        rank.setName("C");
        assertEquals("C", rank.getName());
    }
}