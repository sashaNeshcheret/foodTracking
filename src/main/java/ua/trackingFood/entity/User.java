package ua.trackingFood.entity;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String mailAddress;


    public User(){
    }

    public User(String login, String password, String name, String surname, String mail_adress) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.mailAddress = mail_adress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail_adress() {
        return mailAddress;
    }

    public void setMail_adress(String mail_adress) {
        this.mailAddress = mail_adress;
    }
}




