package com.bikinaplikasi.karirku.repository;

import com.bikinaplikasi.karirku.entity.Facilities.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilitiesRepository extends JpaRepository<Facilities, Integer> {
}
