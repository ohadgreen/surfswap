package com.acme.surfswap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "store_admins")
public class StoreAdmin extends Person {
    public StoreAdmin(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName, phoneNumber);
    }

    @ManyToOne
    private Store store;
}
