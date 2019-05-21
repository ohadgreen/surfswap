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
@Entity
public class Item extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Person owner;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    private ItemAvailability itemAvailability;
    private ItemStatus itemStatus;
    private OwnershipType ownershipType;
    private Integer rentRate;

}
