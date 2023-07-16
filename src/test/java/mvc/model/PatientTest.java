package mvc.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
class PatientTest {
    @Test
    public void testGetId() {
        Patient patient = new Patient();
        patient.setId(1);
        assertEquals(1, patient.getId());
    }
    @Test
    public void testGetUserName() {
        Patient patient = new Patient();
        patient.setUserName("john123");
        assertEquals("john123", patient.getUserName());
    }
    @Test
    public void testGetUrl() {
        Patient patient = new Patient();
        patient.setUrl("http://example.com");
        assertEquals("http://example.com", patient.getUrl());
    }
    @Test
    public void testGetName() {
        Patient patient = new Patient();
        patient.setName("John Doe");
        assertEquals("John Doe", patient.getName());
    }
    @Test
    public void testGetGender() {
        Patient patient = new Patient();
        patient.setGender("Male");
        assertEquals("Male", patient.getGender());
    }
    @Test
    public void testGetDob() {
        Patient patient = new Patient();
        Date dob = Date.valueOf("1990-01-01");
        patient.setDob(dob);
        assertEquals(dob, patient.getDob());
    }
    @Test
    public void testGetRankId() {
        Patient patient = new Patient();
        patient.setRankId(2);
        assertEquals(2, patient.getRankId());
    }
    @Test
    public void testGetRanks() {
        Rank rank = new Rank();
        Patient patient = new Patient();
        patient.setRanks(rank);
        assertEquals(rank, patient.getRanks());
    }
}