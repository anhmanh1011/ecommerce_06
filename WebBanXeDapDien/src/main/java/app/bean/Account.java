package app.bean;

public class Account {
    private Integer id;
    private String email;
    private String passWord;
    private String phoneNumber;
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", passWord='" + passWord + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                '}';
    }
}
