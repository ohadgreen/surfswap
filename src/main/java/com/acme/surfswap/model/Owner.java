package com.acme.surfswap.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "owners")
public class Owner extends Person {
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Surfboard> surfboards = new HashSet<>();

    public Owner(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName, phoneNumber);
    }

    public Owner addSurfboard(Surfboard surfboard) {
        surfboard.setOwner(this);
        this.surfboards.add(surfboard);
        return this;
    }
}
