
package mvc.model;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class StaffTest {
    private Staff staff;

    @Test
    public void testGetId() {
        assertEquals(1, staff.getId());
    }
    @Test
    public void testSetId() {
        staff.setId(2);
        assertEquals(2, staff.getId());
    }
    @Test
    public void testGetUserName() {
        Staff staff = new Staff();
        staff .setUserName("JohnDoe");
        assertEquals( staff.getUserName(), "JohnDoe");
    }
    @Test
    public void testSetUserName() {
        Staff staff = new Staff();
        staff .setUserName("JohnDoe");
        assertEquals( staff.getUserName(), "JohnDoe");
    }
    @Test
    public void testGetUrl() {
        Staff staff = new Staff();
        staff.setUrl("https://example.com");
        assertEquals("https://example.com", staff.getUrl());
    }
    @Test
    public void testSetUrl() {
        Staff staff = new Staff();
        staff.setUrl("https://example.com");
        assertEquals("https://example.com", staff.getUrl());
    }
    @Test
    public void testGetName() {
        Staff staff = new Staff();
        staff.setName("John Doe");
        assertEquals("John Doe", staff.getName());
    }
    @Test
    public void testSetName() {
        Staff staff = new Staff();
        staff.setName("John Doe");
        assertEquals("John Doe", staff.getName());
    }
    @Test
    public void testGetGender() {
        Staff staff = new Staff();
        staff.setGender("Male");
        assertEquals("Male", staff.getGender());
    }
    @Test
    public void testSetGender() {
        Staff staff = new Staff();
        staff.setGender("Male");
        assertEquals("Male", staff.getGender());
    }
    @Test
    public void testGetDob() {
        Staff staff = new Staff();
        Date dob = new Date(22-04-2023);
        staff.setDob(dob);
        assertEquals(dob, staff.getDob());
    }
    @Test
    public void testSetDob() {
        Staff staff = new Staff();
        Date dob = new Date(22-04-2023);
        staff.setDob(dob);
        assertEquals(dob, staff.getDob());
    }

}