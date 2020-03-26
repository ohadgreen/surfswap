package com.acme.surfswap.model;

import com.acme.surfswap.enums.ItemAvailability;
import com.acme.surfswap.enums.ItemStatus;
import com.acme.surfswap.enums.SurfboardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "surfboards")
public class Surfboard extends Item {
    @Enumerated(EnumType.STRING)
    private SurfboardType surfboardType;
    private String brand;
    private String model;
    private double length;
    private double width;
    private double thickness;
    private double volume;
    private boolean privateUse;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @JsonIgnore
    @OneToMany(mappedBy = "surfboard")
    private Set<Reservation> reservations = new HashSet<>();

    @Builder
    public Surfboard(ItemAvailability itemAvailability, ItemStatus itemStatus, Integer ratePerHour,  SurfboardType surfboardType, String brand, String model, double length, double width, double thickness, double volume, boolean privateUse) {
        super(itemAvailability, itemStatus, ratePerHour);
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
