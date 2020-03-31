package com.acme.surfswap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owners")
@EqualsAndHashCode(callSuper = true)
public class Owner extends Person {
    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private Set<Surfboard> surfboards = new HashSet<>();

    @Builder
    public Owner(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName, phoneNumber);
    }

    public Owner addSurfboard(Surfboard surfboard) {
        surfboard.setOwner(this);
        this.surfboards.add(surfboard);
        return this;
    }
}
