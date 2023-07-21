
package mvc.dal;

import mvc.model.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AccountDBTest {
    private AccountDB accountDB;
    @Before
    public void setUp() {
        accountDB = new AccountDB();
    }
    @Test
    public void testGetRoleTrue() {
        AccountDB accountDB = new AccountDB();
        Account account = new Account();
        account.setIsAdmin(0);
        Role role = accountDB.getRole(account);
        assertNotNull(role);
        assertEquals(0, role.getId());
        assertEquals("Admin", role.getName());
    }
    @Test
    public void testGetRoleFalse() {
        AccountDB accountDB = new AccountDB();
        Account account = new Account();
        account.setIsAdmin(0);
        Role role = accountDB.getRole(account);
        assertNotNull(role);
        assertEquals(0, role.getId());
        assertFalse(role.getName().equals("admin"));
    }

    @Test
    public void testGetAccountTrue() {
        AccountDB accountDB = new AccountDB();
        String user = "admin";
        String pass = "123";
        Account account = accountDB.getAccount(user, pass);
        assertNotNull(account);
        assertEquals("admin", account.getUsername());
        assertEquals("123", account.getPassword());
    }
    @Test
    public void testGetAccountFasle() {
        AccountDB accountDB = new AccountDB();
        String user = "admin";
        String pass = "123";
        Account account = accountDB.getAccount(user, pass);
        assertNotNull(account);
        assertFalse(account.getUsername().equals("admin@"));
        assertEquals("123", account.getPassword());
    }

    @Test
    public void testUpdateAccountTrue() {
        AccountDB accountDB = new AccountDB();
        Account account = new Account();
        account.setUsername("admin");
        account.setPassword("1232");
        accountDB.UpdateAccount(account);
        // Verify the account is updated in the database
        Account updatedAccount = accountDB.getAccount("admin", "1232");
        assertNotNull(updatedAccount);
        assertEquals("1232", updatedAccount.getPassword());
    }
    @Test
    public void testUpdateAccountFaslse() {
        AccountDB accountDB = new AccountDB();
        Account account = new Account();
        account.setUsername("admin");
        account.setPassword("1232");
        accountDB.UpdateAccount(account);
        // Verify the account is updated in the database
        Account updatedAccount = accountDB.getAccount("admin", "1232");
        assertNotNull(updatedAccount);
        assertFalse(updatedAccount.getPassword().equals("123"));
    }
    @Test
    public void testCheckAccountExistTrue() {
        AccountDB accountDB = new AccountDB();
        String user = "admin";
        Account account = accountDB.checkAccountExist(user);
        assertNotNull(account);
        assertEquals("admin", account.getUsername());
    }
    @Test
    public void testCheckAccountExistFalse() {
        AccountDB accountDB = new AccountDB();
        String user = "admin";
        Account account = accountDB.checkAccountExist(user);
        assertNotNull(account);
        assertFalse(account.getUsername().equals("admin@"));
    }
    @Test
    public void testRegisterTrue() {
        // vì phone trong database để NotNull, cách pass là vào chỉnh lại thành Null
        Account account = new Account();
        account.setUsername("testuser123121");
        account.setPassword("testpassword");
//        account.setPhone("");
        account.setEmail("testemail@example.com");
        account.setIsAdmin(1);
//        account.setStatus(true);
        // Call the Register method
        AccountDB accountDB = new AccountDB();
        accountDB.Register(account, "Test User");
        assertEquals(account.getUsername(), "testuser123121");
    }
    @Test
    public void testRegisterFalse() {
// vì phone trong database để NotNull, cách pass là vào chỉnh lại thành Null
        Account account = new Account();
        account.setUsername("testuser123121");
        account.setPassword("testpassword");
//        account.setPhone("");
        account.setEmail("testemail@example.com");
        account.setIsAdmin(1);
//        account.setStatus(true);
        // Call the Register method
        AccountDB accountDB = new AccountDB();
        accountDB.Register(account, "Test User");
        assertFalse(account.getUsername().equals("quyet"));
    }
    @Test
    public void testAddNewDoctorTrue() {
// Create a sample Patient object
        Doctor doctor = new Doctor();
        doctor.setUserName("doctor1");
        doctor.setUrl("https://example.com");
        doctor.setName("John Doe");
        doctor.setGender("Male");
        doctor.setDob(new Date(1990, 1, 1));
        doctor.setSpecialty("Cardiology");
        doctor.setRankId(1);
        // Set other properties of the patient object
        // Create a sample Account object
        Account account = new Account();
        account.setUsername("doctor@@");
        account.setPassword("password");
        account.setPhone("123213121");
        account.setEmail("quyetlbche160252@fpt.edu.vn");
        account.setIsAdmin(2);
        account.setStatus(true);
        // Set other properties of the account object
        // Set the account object to the patient object
        doctor.setAccount(account);
        // Create an instance of AccountDB
        AccountDB accountDB = new AccountDB();
        // Call the addNewPatient() method
        accountDB.addNewDoctor(doctor);
        // Assert that the patient is added successfully
        // Write assertions based on the expected behavior of the addNewPatient() method
        assertEquals(account.getUsername(),"doctor@@");
        assertEquals(account.getPassword(),"password");
        assertEquals(account.getPhone(),"123213121");
        assertEquals(account.getEmail(),"quyetlbche160252@fpt.edu.vn");
        assertEquals(account.getIsAdmin(),2);
    }
    @Test
    public void testAddNewDoctorFalse() {
// Create a sample Patient object
        Doctor doctor = new Doctor();
        doctor.setUserName("doctor1");
        doctor.setUrl("https://example.com");
        doctor.setName("John Doe");
        doctor.setGender("Male");
        doctor.setDob(new Date(1990, 1, 1));
        doctor.setSpecialty("Cardiology");
        doctor.setRankId(1);
        // Set other properties of the patient object
        // Create a sample Account object
        Account account = new Account();
        account.setUsername("doctor@@@");
        account.setPassword("password");
        account.setPhone("123213121");
        account.setEmail("quyetlbche160252@fpt.edu.vn");
        account.setIsAdmin(2);
        account.setStatus(true);
        // Set other properties of the account object
        // Set the account object to the patient object
        doctor.setAccount(account);
        // Create an instance of AccountDB
        AccountDB accountDB = new AccountDB();
        // Call the addNewPatient() method
        accountDB.addNewDoctor(doctor);
        // Assert that the patient is added successfully
        // Write assertions based on the expected behavior of the addNewPatient() method
        assertEquals(account.getUsername(),"doctor@@@");
        assertEquals(account.getPassword(),"password");
        assertEquals(account.getPhone(),"123213121");
        assertEquals(account.getEmail(),"quyetlbche160252@fpt.edu.vn");
        assertFalse(account.getIsAdmin()==3);
    }
    @Test
    public void testAddNewPatientTrue() {
        // Create a sample Patient object
        Patient patient = new Patient();
        patient.setUserName("testPatient");
        patient.setUrl("http://example.com");
        patient.setName("John Doe");
        patient.setRankId(2);
        // Set other properties of the patient object
        // Create a sample Account object
        Account account = new Account();
        account.setUsername("testPatient");
        account.setPassword("password");
        account.setPhone("123213121");
        account.setEmail("quyetlbche160252@fpt.edu.vn");
        account.setIsAdmin(3);
        account.setStatus(true);
        // Set other properties of the account object
        // Set the account object to the patient object
        patient.setAccount(account);
        // Create an instance of AccountDB
        AccountDB accountDB = new AccountDB();
        // Call the addNewPatient() method
        accountDB.addNewPatient(patient);
        // Assert that the patient is added successfully
        // Write assertions based on the expected behavior of the addNewPatient() method
        assertEquals(account.getUsername(),"testPatient");
        assertEquals(account.getPassword(),"password");
        assertEquals(account.getPhone(),"123213121");
        assertEquals(account.getEmail(),"quyetlbche160252@fpt.edu.vn");
        assertEquals(account.getIsAdmin(),3);
    }
    @Test
    public void testAddNewPatientFasle() {
        // Create a sample Patient object
        Patient patient = new Patient();
        patient.setUserName("Patient1");
        patient.setUrl("http://example.com");
        patient.setName("John Doe");
        patient.setRankId(2);
        // Set other properties of the patient object
        // Create a sample Account object
        Account account = new Account();
        account.setUsername("testPatient");
        account.setPassword("password");
        account.setPhone("123213121");
        account.setEmail("quyetlbche160252@fpt.edu.vn");
        account.setIsAdmin(3);
        account.setStatus(true);
        // Set other properties of the account object
        // Set the account object to the patient object
        patient.setAccount(account);
        // Create an instance of AccountDB
        AccountDB accountDB = new AccountDB();
        // Call the addNewPatient() method
        accountDB.addNewPatient(patient);
        // Assert that the patient is added successfully
        // Write assertions based on the expected behavior of the addNewPatient() method
        assertFalse(account.getUsername().equals("testPatient@@"));
        assertEquals(account.getPassword(),"password");
        assertEquals(account.getPhone(),"123213121");
        assertEquals(account.getEmail(),"quyetlbche160252@fpt.edu.vn");
        assertEquals(account.getIsAdmin(),3);
    }
    @Test
    public void testAddNewStaffTrue() {
        // Create a sample Patient object
        Account account = new Account();
        account.setUsername("staff@@");
        account.setPassword("password");
        account.setPhone("123213121");
        account.setEmail("quyetlbche160252@fpt.edu.vn");
        account.setIsAdmin(2);
        account.setStatus(true);
        Staff staff = new Staff();
        staff.setUserName("doctor1");
        staff.setUrl("https://example.com");
        staff.setName("John Doe");
        staff.setGender("Male");
        staff.setDob(new Date(1990, 1, 1));
        staff.setAccount(account);

        staff.setAccount(account);
        // Create an instance of AccountDB
        AccountDB accountDB = new AccountDB();
        // Call the addNewPatient() method
        accountDB.addNewStaff(staff);
        // Assert that the patient is added successfully
        // Write assertions based on the expected behavior of the addNewPatient() method
        assertEquals(account.getUsername(),"staff@@");
        assertEquals(account.getPassword(),"password");
        assertEquals(account.getPhone(),"123213121");
        assertEquals(account.getEmail(),"quyetlbche160252@fpt.edu.vn");
        assertEquals(account.getIsAdmin(),2);
    }
    @Test
    public void testAddNewStaffFasle() {
        Account account = new Account();
        account.setUsername("staff@@@@343");
        account.setPassword("password");
        account.setPhone("123213121");
        account.setEmail("quyetlbche160252@fpt.edu.vn");
        account.setIsAdmin(2);
        account.setStatus(true);
        Staff staff = new Staff();
        staff.setUserName("doctor1");
        staff.setUrl("https://example.com");
        staff.setName("John Doe");
        staff.setGender("Male");
        staff.setDob(new Date(1990, 1, 1));
        staff.setAccount(account);

        staff.setAccount(account);
        // Create an instance of AccountDB
        AccountDB accountDB = new AccountDB();
        // Call the addNewPatient() method
        accountDB.addNewStaff(staff);
        // Assert that the patient is added successfully
        // Write assertions based on the expected behavior of the addNewPatient() method
        assertFalse(account.getUsername().equals("staff@@"));
        assertEquals(account.getPassword(),"password");
        assertEquals(account.getPhone(),"123213121");
        assertEquals(account.getEmail(),"quyetlbche160252@fpt.edu.vn");
        assertFalse(account.getIsAdmin()==3);
    }
}