package dal;

import static org.junit.jupiter.api.Assertions.*;

import model.Account;
import model.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class PatientDBContextTest {
    @Mock
    private PatientDBContext patientDBContext;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void getPatient() {
        Account account = new Account();
        account.setUsername("test");
        Patient patient = patientDBContext.getPatient(account);
        assertNotNull(patient);
        assertEquals(account.getUsername(), patient.getUserName());
    }

}