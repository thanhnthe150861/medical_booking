package mvc.model;


import java.sql.Date;

public class Doctor {
    private int id;
    private String userName;
    private String url;
    private String name;
    private String gender;
    private Date dob;
    private int specialty;
    private int rankId;
    
    private Account account = new Account();
    private Specialty specialtys = new Specialty();
    private Rank ranks = new Rank();

    public Doctor() {
    }

    public Doctor(int id, String userName, String url, String name, String gender, Date dob, int specialty, int rankId, Account account, Specialty specialtys, Rank ranks) {
        this.id = id;
        this.userName = userName;
        this.url = url;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.specialty = specialty;
        this.rankId = rankId;
        this.account = account;
        this.specialtys = specialtys;
        this.ranks = ranks;
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

    public int getSpecialty() {
        return specialty;
    }

    public void setSpecialty(int specialty) {
        this.specialty = specialty;
    }

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Specialty getSpecialtys() {
        return specialtys;
    }

    public void setSpecialtys(Specialty specialtys) {
        this.specialtys = specialtys;
    }

    public Rank getRanks() {
        return ranks;
    }

    public void setRanks(Rank ranks) {
        this.ranks = ranks;
    }
}
