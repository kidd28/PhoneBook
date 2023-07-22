package com.example.phonebook;

public class ContactModel {
    String Name, Address, Phone, Id;
    public ContactModel(){}

    ContactModel(String name, String address,String phone ,String id){
        this.Name = name;
        this.Address = address;
        this.Phone = phone;
        this.Id = id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
