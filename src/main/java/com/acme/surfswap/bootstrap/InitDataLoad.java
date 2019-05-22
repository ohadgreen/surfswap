package com.acme.surfswap.bootstrap;

import com.acme.surfswap.enums.SurfboardType;
import com.acme.surfswap.model.Owner;
import com.acme.surfswap.model.Store;
import com.acme.surfswap.model.Surfboard;
import com.acme.surfswap.services.OwnerService;
import com.acme.surfswap.services.StoreService;
import com.acme.surfswap.services.SurfboardService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDataLoad implements CommandLineRunner
{
    private final StoreService storeService;
    private final OwnerService ownerService;
    private final SurfboardService surfboardService;

    public InitDataLoad(StoreService storeService, OwnerService ownerService, SurfboardService surfboardService) {
        this.storeService = storeService;
        this.ownerService = ownerService;
        this.surfboardService = surfboardService;
    }

    @Override
    public void run(String... args) throws Exception {
        int storeCount = storeService.findAll().size();
        if (storeCount == 0) {
            initData();
        }
    }

    private void initData() {
        Store acadia = new Store("Acadia", "1 Hayam St.");
        storeService.save(acadia);

        Owner owner1 = new Owner("John", "Doe", "1234-5678");
        Owner owner2 = new Owner("Alice", "Armon", "1234-9876");

        Surfboard longboard = new Surfboard(SurfboardType.LONG_BOARD, "Hurley", "Ingleby", 9.0, 22, 2.5, 52.9 );
        longboard.setOwner(owner1);
        longboard.setStore(acadia);
        Surfboard shortboard1 = new Surfboard(SurfboardType.SHORT_BOARD, "Al Merrick", "Happy", 5.6, 18.25, 2.2, 22.7 );
        shortboard1.setOwner(owner2);
        shortboard1.setStore(acadia);

        owner1.getSurfboards().add(longboard);
        owner2.getSurfboards().add(shortboard1);

        ownerService.save(owner1);
        ownerService.save(owner2);

        acadia.getSurfboards().add(longboard);
        acadia.getSurfboards().add(shortboard1);
        storeService.save(acadia);
    }
}
