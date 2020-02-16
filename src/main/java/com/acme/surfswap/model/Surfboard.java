package com.acme.surfswap.model;

import com.acme.surfswap.enums.ItemAvailability;
import com.acme.surfswap.enums.ItemStatus;
import com.acme.surfswap.enums.OwnershipType;
import com.acme.surfswap.enums.SurfboardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "surfboards")
public class Surfboard extends Item {
    @Enumerated(value = EnumType.STRING)
    private SurfboardType surfboardType;
    private String brand;
    private String model;
    private double length;
    private double width;
    private double thickness;
    private double volume;
    private boolean privateUse;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id")
    private Store store;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Surfboard(SurfboardType surfboardType, String brand, String model, double length, double width, double thickness, double volume, boolean privateUse) {
        this.surfboardType = surfboardType;
        this.brand = brand;
        this.model = model;
        this.length = length;
        this.width = width;
        this.thickness = thickness;
        this.volume = volume;
        this.privateUse = privateUse;
    }
}
