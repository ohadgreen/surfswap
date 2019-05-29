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

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Surfboard> surfboards = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "stores_store_admins", joinColumns = @JoinColumn(name = "store_id"), inverseJoinColumns = @JoinColumn(name = "store_admin_id"))
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
