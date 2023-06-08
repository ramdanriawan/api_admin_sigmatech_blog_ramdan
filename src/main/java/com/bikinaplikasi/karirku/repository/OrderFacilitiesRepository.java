package com.bikinaplikasi.karirku.repository;

import com.bikinaplikasi.karirku.entity.OrderFacilities.OrderFacilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderFacilitiesRepository extends JpaRepository<OrderFacilities, Integer> {
    List<OrderFacilities> findAll();
}
