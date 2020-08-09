package com.endava.homework.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {

    private Integer id;
    private String address;
    private String city;
    private String firstName;
    private String lastName;
    private String telephone;
    private String pet;
    private List pets;

    public List getPets() {
        return pets;
    }

    public void setPets(List pets) {
        this.pets = pets;
    }

    public Owner() {
    }

    public Owner(String address, String city, String firstName, String lastName, String telephone, String pet) {
        this.address = address;
        this.city = city;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.pet = pet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(address, owner.address) &&
                Objects.equals(city, owner.city) &&
                Objects.equals(firstName, owner.firstName) &&
                Objects.equals(lastName, owner.lastName) &&
                Objects.equals(telephone, owner.telephone) &&
                Objects.equals(pet, owner.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, firstName, lastName, telephone, pet);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", pet='" + pet + '\'' +
                '}';
    }
}
