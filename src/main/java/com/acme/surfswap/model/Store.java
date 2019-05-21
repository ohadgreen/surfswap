package com.acme.surfswap.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Store extends BaseEntity {
    private String name;
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
    private Set<Item> items = new HashSet<>();

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
