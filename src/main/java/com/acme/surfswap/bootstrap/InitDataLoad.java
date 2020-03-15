package com.acme.surfswap.bootstrap;

import com.acme.surfswap.enums.ItemAvailability;
import com.acme.surfswap.enums.ItemStatus;
import com.acme.surfswap.enums.SurfboardType;
import com.acme.surfswap.model.*;
import com.acme.surfswap.repositories.TimeSlotRepository;
import com.acme.surfswap.services.OwnerService;
import com.acme.surfswap.services.StoreService;
import com.acme.surfswap.services.SurfboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class InitDataLoad implements CommandLineRunner
{
    private final StoreService storeService;
    private final OwnerService ownerService;
    private final SurfboardService surfboardService;
    private final TimeSlotRepository timeSlotRepository;

    public InitDataLoad(StoreService storeService, OwnerService ownerService, SurfboardService surfboardService, TimeSlotRepository timeSlotRepository) {
        this.storeService = storeService;
        this.ownerService = ownerService;
        this.surfboardService = surfboardService;
        this.timeSlotRepository = timeSlotRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        int storeCount = storeService.findAll().size();
        if (storeCount == 0) {
            initSurfboardsData();
        }

        boolean timeSlotsExist = timeSlotRepository.findAll().iterator().hasNext();
        if (!timeSlotsExist) {
            this.initTimeSlots();
        }
    }

    private void initSurfboardsData() {
        log.debug("initiating surfboards data");
        Store acadia = new Store("Acadia", "1 Hayam St.");
        StoreAdmin acadiaAdmin = new StoreAdmin("Dani", "Acadia", "333-4567");
        acadia.addStoreAdmin(acadiaAdmin);
        storeService.save(acadia);

        Owner owner1 = new Owner("John", "Doe", "1234-5678");
        Owner owner2 = new Owner("Alice", "Armon", "1234-9876");
        Owner owner3 = new Owner("bob", "Brown", "1234-9999");
        Owner owner4 = new Owner("Fake", "shake", "1234-9999");

//        Surfboard longboard = new Surfboard(ItemAvailability.AVAILABLE, ItemStatus.GOOD, 10, SurfboardType.LONG_BOARD, "Hurley", "Ingleby", 9.0, 22, 2.5, 52.9 );
//        Surfboard shortboard1 = new Surfboard(SurfboardType.SHORT_BOARD, "Al Merrick", "Happy", 5.6, 18.25, 2.2, 22.7 );
//        Surfboard softboard = new Surfboard(SurfboardType.SOFT_BOARD, "De Hui", "Soft", 8.0, 30.0, 5.0, 62.2 );

        List<Surfboard> surfboards = initSurfboards();
        for (Surfboard surfboard : surfboards) {
            surfboardService.save(surfboard);
        }
        owner1.addSurfboard(surfboards.get(0));
        acadia.addSurboard(surfboards.get(0));

        owner2.addSurfboard(surfboards.get(1));
        acadia.addSurboard(surfboards.get(1));

        owner3.addSurfboard(surfboards.get(2));
        acadia.addSurboard(surfboards.get(2));

        ownerService.save(owner1);
        ownerService.save(owner2);
        ownerService.save(owner3);

        storeService.save(acadia);

        Surfboard surfboard4 = surfboards.get(3);
        surfboard4.setOwner(owner4);
//        Surfboard savedBoard4 = surfboardService.save(surfboards.get(3));
//        System.out.println("savedBoard4 = " + savedBoard4);

//        owner4.addSurfboard(savedBoard4);
        ownerService.save(owner4);
    }

    private void initTimeSlots() {
        LocalDateTime startDateTime = LocalDateTime.of(2020, 2, 13, 0, 0, 0);
        System.out.println("initiating time slots from: " + startDateTime);

        for (int i = 0; i < 3; i++) {
            for (int h = 0; h < 24; h++) {
                LocalDateTime accumulateHourDateTime = startDateTime.plusDays(i).plusHours(h);
                TimeSlot timeSlot = TimeSlot.builder()
                        .localDateTime(accumulateHourDateTime)
                        .timeSlotStart(accumulateHourDateTime.toLocalTime())
                        .timeSlotEnd(accumulateHourDateTime.plusHours(1).toLocalTime())
                        .year(accumulateHourDateTime.getYear())
                        .month(accumulateHourDateTime.getMonthValue())
                        .dayOfMonth(accumulateHourDateTime.getDayOfMonth())
                        .dayOfWeekNum(accumulateHourDateTime.getDayOfWeek().getValue())
                        .hour(accumulateHourDateTime.getHour())
                        .build();

                timeSlotRepository.save(timeSlot);
            }
        }
    }

    private List<Surfboard> initSurfboards() {
        Surfboard shortBoard = Surfboard.builder()
                .itemAvailability(ItemAvailability.AVAILABLE)
                .itemStatus(ItemStatus.GOOD)
                .ratePerHour(20)
                .surfboardType(SurfboardType.SHORT_BOARD)
                .brand("Al Merrick")
                .model("Happy")
                .length(5.6)
                .width(18.25)
                .thickness(2.2)
                .volume(22.7)
                .build();

        Surfboard longBoard = Surfboard.builder()
                .itemAvailability(ItemAvailability.AVAILABLE)
                .itemStatus(ItemStatus.GOOD)
                .ratePerHour(30)
                .surfboardType(SurfboardType.LONG_BOARD)
                .brand("Hurley")
                .model("Ingleby")
                .length(9.0)
                .width(23.0)
                .thickness(2.5)
                .volume(53)
                .build();

        Surfboard funBoard = Surfboard.builder()
                .itemAvailability(ItemAvailability.AVAILABLE)
                .itemStatus(ItemStatus.GOOD)
                .ratePerHour(33)
                .surfboardType(SurfboardType.FUN_BOARD)
                .brand("ACME")
                .model("Hypto")
                .length(7.8)
                .width(23.0)
                .thickness(3.0)
                .volume(48.5)
                .build();

        Surfboard board4 = Surfboard.builder()
                .itemAvailability(ItemAvailability.AVAILABLE)
                .itemStatus(ItemStatus.GOOD)
                .ratePerHour(40)
                .surfboardType(SurfboardType.FUN_BOARD)
                .brand("ACME")
                .model("fake board")
                .length(7.8)
                .width(23.0)
                .thickness(3.0)
                .volume(48.5)
                .build();

        List<Surfboard> surfboardList = new ArrayList<>();
        surfboardList.add(shortBoard);
        surfboardList.add(funBoard);
        surfboardList.add(longBoard);
        surfboardList.add(board4);
        return surfboardList;
    }
}
