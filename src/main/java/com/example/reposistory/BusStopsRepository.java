package com.example.reposistory;

import com.example.entity.BusStops;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusStopsRepository extends JpaRepository<BusStops, Long> {
}