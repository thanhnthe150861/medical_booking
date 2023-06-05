package model;

public class Client {
    private int id;
    private String username;
    private String name;
    private int rank_id;

    public Client() {
    }

    public Client(int id, String username, String name, int rank_id) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.rank_id = rank_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank_id() {
        return rank_id;
    }

    public void setRank_id(int rank_id) {
        this.rank_id = rank_id;
    }
}
