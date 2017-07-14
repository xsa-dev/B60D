package ru.b60d.model.record_worker;

public class DBItem {
    private String login;
    private String password;
    private int points;

    public DBItem() {
    }

    public DBItem(String login, int points , String password) {
        this.login = login;
        this.password = password;
        this.points = points;
    }

    public DBItem(String login, int points) {
        this.login = login;
        this.password = "";
        this.points = points;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
