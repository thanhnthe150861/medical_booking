package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private int id;
    private String userName;
    private String url;
    private String name;
    private  String gender;
    private Date dob;
    private int rankId;
    private Rank ranks = new Rank();

    public Client() {
    }

    public Client(int id, String userName, String url, String name, String gender, Date dob, int rankId, Rank ranks) {
        this.id = id;
        this.userName = userName;
        this.url = url;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.rankId = rankId;
        this.ranks = ranks;
    }

    public Rank getRanks() {
        return ranks;
    }

    public void setRanks(Rank ranks) {
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

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }
}
