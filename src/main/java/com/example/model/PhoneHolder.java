package com.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class PhoneHolder {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 64, nullable = false)
    private String fullName;

    @OneToMany(mappedBy = "phoneHolder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones = new ArrayList<>();


    public PhoneHolder() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void addPhone(Phone phone) {
        phones.add(phone);
        phone.setPhoneHolder(this);
    }

    public void removePhone(Phone phone) {
        phones.remove(phone);
        phone.setPhoneHolder(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneHolder)) return false;
        PhoneHolder phoneHolder = (PhoneHolder) o;
        return getFullName().equals(phoneHolder.getFullName()) && phones.equals(phoneHolder.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFullName(), phones);
    }
}
