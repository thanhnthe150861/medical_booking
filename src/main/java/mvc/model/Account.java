package mvc.model;





public class Account {
    private String username;
    private String password;
    private String phone;
    private String email;
    private int isAdmin;

    private Boolean status;

    public Account() {
    }

    public Account(String username, String password, String phone, String email, int isAdmin, Boolean status) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.isAdmin = isAdmin;
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
