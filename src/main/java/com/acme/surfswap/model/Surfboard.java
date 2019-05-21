package com.acme.surfswap.model;

import com.acme.surfswap.enums.ItemAvailability;
import com.acme.surfswap.enums.ItemStatus;
import com.acme.surfswap.enums.OwnershipType;
import com.acme.surfswap.enums.SurfboardType;
import lombok.*;

import javax.persistence.Entity;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Surfboard extends Item {
    private SurfboardType surfboardType;
    private String brand;
    private String model;
    private double length;
    private double width;
    private double thickness;
    private double volume;

    public Surfboard(SurfboardType surfboardType, String brand, String model, double length, double width, double thickness, double volume) {
        this.surfboardType = surfboardType;
        this.brand = brand;
        this.model = model;
        this.length = length;
        this.width = width;
        this.thickness = thickness;
        this.volume = volume;
    }
}
