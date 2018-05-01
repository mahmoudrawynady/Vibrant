package instance;

import java.io.Serializable;

/**
 * Created by PH-Data™ 01221240053 on 24/02/2018.
 */

public class Patient implements Serializable {
    private String Name;
    private String phoneNumber;
    private String City;
    private String Country;
    private String Street;
    private boolean User;
    private boolean Patient;
    private boolean Adult;
    private boolean Children;

    public Patient() {
        this.Adult = true;
        this.Children = false;
        this.Patient = false;
        this.User = true;
    }

    public void setChildren(boolean children) {
        Children = children;
    }

    public boolean isChildren() {
        return Children;
    }

    public void setAdult(boolean adult) {
        Adult = adult;
    }

    public boolean isAdult() {
        return Adult;
    }

    public void setUser(boolean user) {
        User = user;
    }

    public boolean isPatient() {
        return Patient;
    }

    public boolean isUser() {
        return User;
    }

    public void setPatient(boolean patient) {
        Patient = patient;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getStreet() {
        return Street;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCity() {
        return City;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCountry() {
        return Country;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


}

