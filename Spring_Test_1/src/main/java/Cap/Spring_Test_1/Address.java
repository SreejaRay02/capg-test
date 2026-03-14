package Cap.Spring_Test_1;



public class Address {

    private int id;
    private String city;
    private String state;
    private String country;
    private int pincode;

    public Address() {}

    public Address(int id, String city, String state, String country, int pincode) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public int getPincode() { return pincode; }

    public void setPincode(int pincode) { this.pincode = pincode; }

}
