
        package mvc.model;

        import static org.junit.jupiter.api.Assertions.*;

        import org.junit.jupiter.api.Test;
        import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    @Test
    public void testGetId() {
        Role role = new Role(1, "Admin");
        assertEquals(1, role.getId());
    }

    @Test
    public void testGetName() {
        Role role = new Role(1, "Admin");
        assertEquals("Admin", role.getName());
    }

    @Test
    public void testSetId() {
        Role role = new Role();
        role.setId(2);
        assertEquals(2, role.getId());
    }

    @Test
    public void testSetName() {
        Role role = new Role();
        role.setName("User");
        assertEquals("User", role.getName());
    }
}