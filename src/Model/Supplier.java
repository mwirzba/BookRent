package Model;

public class Supplier {
    private String Name;
    private String City;
    private String Street;
    private String Number;

    public Supplier(String name, String city, String street, String number) {
        Name = name;
        City = city;
        Street = street;
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }


}
