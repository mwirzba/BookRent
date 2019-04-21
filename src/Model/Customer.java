package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Customer {

    private  String Id;
    private  String password;
    private  String login;

    public Customer(String id, String login) {
        Id = id;
        this.login = login;
    }

    public String getId() {
        return Id;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public Customer(){}
}
