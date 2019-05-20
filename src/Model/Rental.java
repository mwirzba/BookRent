package Model;

public class Rental {
    private  String dateReturned;

    public String getDateRented() {
        return dateRented;
    }

    public void setDateRented(String dateRented) {
        this.dateRented = dateRented;
    }

    private  String dateRented;

    public String getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public Rental(String dateRented,String dateReturned,String title)
    {
        this.dateRented =dateRented;
        this.dateReturned =  dateReturned;
        this.title = title;
    }
   public  Rental(){}

}
