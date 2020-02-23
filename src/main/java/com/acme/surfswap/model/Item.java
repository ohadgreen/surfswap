package com.acme.surfswap.model;

import com.acme.surfswap.enums.ItemAvailability;
import com.acme.surfswap.enums.ItemStatus;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class Item extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Enumerated(EnumType.STRING)
    private ItemAvailability itemAvailability;
    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;
    private double ratePerHour;

    public Item(ItemAvailability itemAvailability, ItemStatus itemStatus, Integer ratePerHour) {
        this.itemAvailability = itemAvailability;
        this.itemStatus = itemStatus;
        this.ratePerHour = ratePerHour;
    }
}
