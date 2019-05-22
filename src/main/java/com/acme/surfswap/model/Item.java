package com.acme.surfswap.model;

import com.acme.surfswap.enums.ItemAvailability;
import com.acme.surfswap.enums.ItemStatus;
import com.acme.surfswap.enums.OwnershipType;
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

    private ItemAvailability itemAvailability;
    private ItemStatus itemStatus;
    private OwnershipType ownershipType;
    private Integer rentRate;

}
