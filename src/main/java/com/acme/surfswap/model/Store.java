package com.acme.surfswap.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "stores")
public class Store extends BaseEntity {
    private String name;
    private String address;

    @OneToMany(mappedBy = "store")
    private Set<Surfboard> surfboards = new HashSet<>();

    @OneToMany(mappedBy = "store")
    private Set<StoreAdmin> storeAdmins = new HashSet<>();

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Store addSurboard(Surfboard surfboard) {
        surfboard.setStore(this);
        this.surfboards.add(surfboard);
        return this;
    }

    public Store addStoreAdmin(StoreAdmin storeAdmin) {
        storeAdmin.setStore(this);
        this.storeAdmins.add(storeAdmin);
        return this;
    }
}
