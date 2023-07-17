package mvc.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AccountTest {
    @Test
    public void testGetStatusPositive() {
        Account account = new Account();
        account.setStatus(true);
        assertTrue(account.getStatus());
    }
    @Test
    public void testSetStatusPositive() {
        Account account = new Account();
        account.setStatus(true);
        assertTrue(account.getStatus());
    }
    @Test
    public void testGetIsAdmin() {
        Account account = new Account();
        account.setIsAdmin(1);
        assertEquals(1, account.getIsAdmin());
    }
    @Test
    public void testGetUsername() {
        Account account = new Account();
        account.setUsername("John");
        assertEquals("John", account.getUsername());
    }
    @Test
    public void testGetPhone() {
        Account account = new Account();
        account.setPhone("1234567890");
        assertEquals("1234567890", account.getPhone());
    }
    @Test
    public void testSetPhone() {
        Account account = new Account();
        account.setPhone("1234567890");
        assertEquals("1234567890", account.getPhone());
    }
    @Test
    public void testGetPassword() {
        Account account = new Account();
        account.setPassword("password123");
        assertEquals("password123", account.getPassword());
    }
    @Test
    public void testSetPassword() {
        Account account = new Account();
        account.setPassword("password123");
        assertEquals("password123", account.getPassword());
    }
    @Test
    public void testGetEmail() {
        Account account = new Account();
        account.setEmail("test@example.com");
        assertEquals("test@example.com", account.getEmail());
    }
    @Test
    public void testSetEmail() {
        Account account = new Account();
        account.setEmail("test@example.com");
        assertEquals("test@example.com", account.getEmail());
    }
}