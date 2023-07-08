package dal;

import model.Account;
import model.Patient;
import model.Role;
import org.junit.Before;
import org.junit.jupiter.api.Test;

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
    public void testGetAllAccountTrue() {
        AccountDB accountDB = new AccountDB();
        List<Account> accountList = accountDB.getAllAccount();
        assertNotNull(accountList);
        assertFalse(accountList.isEmpty());
        assertEquals(14, accountList.size());
    }
    @Test
    public void testGetAllAccountFasle() {
        AccountDB accountDB = new AccountDB();
        List<Account> accountList = accountDB.getAllAccount();
        assertNotNull(accountList);
        assertFalse(accountList.isEmpty());
        assertFalse(accountList.size()==4);
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


}