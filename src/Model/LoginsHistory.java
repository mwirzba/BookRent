package Model;

public class LoginsHistory {

    private  String Id;
    private  String login;
    private  String LoggedDate;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLoggedDate() {
        return LoggedDate;
    }

    public void setLoggedDate(String loggedDate) {
        LoggedDate = loggedDate;
    }

    public LoginsHistory(String login,String LoggedDate) {
        this.login = login;
        this.LoggedDate = LoggedDate;
    }

    public LoginsHistory(){}
}
