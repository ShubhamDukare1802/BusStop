package com.example.controller;

import com.example.entity.Bus;
import com.example.entity.BusStops;
import com.example.entity.Stop;
import com.example.reposistory.BusRepository;
import com.example.reposistory.BusStopsRepository;
import com.example.reposistory.StopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/busstops")
public class BusStopsController {

    private BusRepository busRepository;
    private StopRepository stopRepository;
    private BusStopsRepository busStopsRepository;

    public BusStopsController(BusRepository busRepository, StopRepository stopRepository,BusStopsRepository busStopsRepository) {
        this.busRepository = busRepository;
        this.stopRepository = stopRepository;
        this.busStopsRepository = busStopsRepository;
    }

    // http://localhost:8080/api/v1/busstops/allocate?busId=1&stopId=2
    @PostMapping
    public ResponseEntity<BusStops> allocateBusRoutes(
           @RequestParam long busId,
           @RequestParam long stopId,
           @RequestBody BusStops busStops
    ) {
        Bus bus = busRepository.findById(busId).get();
        Stop stop = stopRepository.findById(stopId).get();

        busStops.setBus(bus);
        busStops.setStop(stop);
        BusStops savedEntity = busStopsRepository.save(busStops);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }
}
