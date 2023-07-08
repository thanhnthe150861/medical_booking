package model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
class DoctorTest {
    @Test
    public void testGetId() {
        Doctor doctor = new Doctor();
        doctor.setId(1);
        int id = doctor.getId();
        Assert.assertEquals(1, id);
    }
    @Test
    public void testSetId() {
        Doctor doctor = new Doctor();
        doctor.setId(1);
        Assert.assertEquals(1, doctor.getId());
    }
    @Test
    public void testGetUserName() {
        Doctor doctor = new Doctor();
        doctor.setUserName("john_doe");
        String userName = doctor.getUserName();
        Assert.assertEquals("john_doe", userName);
    }
    @Test
    public void testSetUserName() {
        Doctor doctor = new Doctor();
        doctor.setUserName("john_doe");
        Assert.assertEquals("john_doe", doctor.getUserName());
    }
    @Test
    public void testGetUrl() {
        Doctor doctor = new Doctor();
        doctor.setUrl("http://example.com");
        assertEquals("http://example.com", doctor.getUrl());
    }
    @Test
    public void testGetName() {
        Doctor doctor = new Doctor();
        doctor.setName("Dr. Smith");
        assertEquals("Dr. Smith", doctor.getName());
    }
    @Test
    public void testGetGender() {
        Doctor doctor = new Doctor();
        doctor.setGender("Male");
        assertEquals("Male", doctor.getGender());
    }
    @Test
    public void testGetDob() {
        Date dob = new Date(2000, 1, 1);
        Doctor doctor = new Doctor();
        doctor.setDob(dob);
        assertEquals(dob, doctor.getDob());
    }
    @Test
    public void testGetSpecialty() {
        Doctor doctor = new Doctor();
        doctor.setSpecialty("Cardiology");
        assertEquals("Cardiology", doctor.getSpecialty());
    }
    @Test
    public void testGetRankId() {
        Doctor doctor = new Doctor();
        doctor.setRankId(2);
        assertEquals(2, doctor.getRankId());
    }

}