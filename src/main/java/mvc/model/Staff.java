package mvc.model;

import java.sql.Date;

public class Staff {
    private int id;
    private String userName;
    private String url;
    private String name;
    private  String gender;
    private Date dob;
    private  Account account = new Account();

    public Staff() {
    }

    public Staff(int id, String userName, String url, String name, String gender, Date dob, Account account) {
        this.id = id;
        this.userName = userName;
        this.url = url;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
