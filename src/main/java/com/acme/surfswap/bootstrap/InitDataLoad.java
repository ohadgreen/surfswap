package com.acme.surfswap.bootstrap;

import com.acme.surfswap.enums.ItemAvailability;
import com.acme.surfswap.enums.ItemStatus;
import com.acme.surfswap.enums.SurfboardType;
import com.acme.surfswap.model.*;
import com.acme.surfswap.repositories.CustomerRepository;
import com.acme.surfswap.repositories.ReservationRepository;
import com.acme.surfswap.repositories.TimeSlotRepository;
import com.acme.surfswap.services.OwnerService;
import com.acme.surfswap.services.StoreService;
import com.acme.surfswap.services.SurfboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;
    @Value("${com.acme.surfswap.message:default mode}")
    private String message;

    public InitDataLoad(StoreService storeService, OwnerService ownerService, SurfboardService surfboardService, TimeSlotRepository timeSlotRepository, CustomerRepository customerRepository, ReservationRepository reservationRepository) {
        this.storeService = storeService;
        this.ownerService = ownerService;
        this.surfboardService = surfboardService;
        this.timeSlotRepository = timeSlotRepository;
        this.customerRepository = customerRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("data init env: " + message);
        int storeCount = storeService.findAll().size();
        if (storeCount == 0) {
            initSurfboardsData();
        }

        boolean timeSlotsExist = timeSlotRepository.findAll().iterator().hasNext();
        if (!timeSlotsExist) {
            this.initTimeSlots();
        }

        boolean reservationsExist = reservationRepository.findAll().iterator().hasNext();
        if (!reservationsExist) {
            initReservations();
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

        List<Surfboard> surfboards = initSurfboards();

        Surfboard surfboard1 = surfboards.get(0);
        surfboard1.setOwner(owner1);
        owner1.addSurfboard(surfboard1);

        Surfboard surfboard2 = surfboards.get(1);
        surfboard2.setOwner(owner2);
        owner2.addSurfboard(surfboard2);

        Surfboard surfboard3 = surfboards.get(2);
        Surfboard surfboard4 = surfboards.get(3);
        surfboard3.setOwner(owner3);
        surfboard4.setOwner(owner3);
        owner3.addSurfboard(surfboard3);
        owner3.addSurfboard(surfboard4);

        acadia.addSurfboard(surfboard1);
        acadia.addSurfboard(surfboard2);
        acadia.addSurfboard(surfboard3);
        acadia.addSurfboard(surfboard4);

        ownerService.save(owner1);
        ownerService.save(owner2);
        ownerService.save(owner3);
        ownerService.save(owner4);

        storeService.save(acadia);
    }

    private void initReservations() {
        Surfboard surfboard1 = this.surfboardService.findById(1L);
        Customer john = Customer.builder().firstName("John").lastName("Florence").phoneNumber("123456").build();

        Reservation initReservation = new Reservation();
        initReservation.setCustomer(john);
        initReservation.setSurfboard(surfboard1);
        initReservation.setActualStartTime(LocalDateTime.now());

        customerRepository.save(john);
        reservationRepository.save(initReservation);
    }

    private void initTimeSlots() {
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
        System.out.println("initiating time slots from: " + now);

        for (int i = 0; i <= 7; i++) {
            for (int h = 0; h < 24; h++) {
                LocalDateTime accumulateHourDateTime = now.plusDays(i).plusHours(h);
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
