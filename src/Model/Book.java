package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Book {

    private  String Id;
    private  String Title;
    private  String Author;

    public String getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setAuthor(String author) {
        Author = author;
    }


    public Book(String id, String title,String author) {
        Id = id;
        this.Title = title;
        Author = author;
    }

    public Book(){}
}
