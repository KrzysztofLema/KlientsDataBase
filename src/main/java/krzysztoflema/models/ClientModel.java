package krzysztoflema.models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class ClientModel extends RecursiveTreeObject<ClientModel> {

    StringProperty name;
    StringProperty surname;
    StringProperty nip;
    StringProperty street;
    StringProperty city;
    StringProperty cityCode;
    StringProperty phoneNumber;
    StringProperty comments;

    public ClientModel(String name, String surname, String nip
// , String street, String city, String cityCode, String phoneNumber, String comments
 ) {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.nip = new SimpleStringProperty(nip);
//        this.street = new SimpleStringProperty(street);
//        this.city = new SimpleStringProperty(city);
//        this.cityCode = new SimpleStringProperty(cityCode);
//        this.phoneNumber = new SimpleStringProperty(phoneNumber);
//        this.comments = new SimpleStringProperty(comments);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getNip() {
        return nip.get();
    }

    public StringProperty nipProperty() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip.set(nip);
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getCityCode() {
        return cityCode.get();
    }

    public StringProperty cityCodeProperty() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode.set(cityCode);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getComments() {
        return comments.get();
    }

    public StringProperty commentsProperty() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments.set(comments);
    }
}

