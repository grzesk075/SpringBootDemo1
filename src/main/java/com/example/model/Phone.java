package com.example.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Phone {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    @Column(unique = true, nullable = false)
    private String number;

    @ManyToOne
    private PhoneHolder phoneHolder;


    public Phone() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneHolder getPhoneHolder() {
        return phoneHolder;
    }

    public void setPhoneHolder(PhoneHolder phoneHolder) {
        this.phoneHolder = phoneHolder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;
        Phone phone = (Phone) o;
        return getNumber().equals(phone.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber());
    }
}
