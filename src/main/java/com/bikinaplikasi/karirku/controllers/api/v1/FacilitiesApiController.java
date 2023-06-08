package com.bikinaplikasi.karirku.controllers.api.v1;

import com.bikinaplikasi.karirku.entity.ResponseJson;
import com.bikinaplikasi.karirku.entity.Facilities.Facilities;
import com.bikinaplikasi.karirku.repository.FacilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/v1/facilities")
public class FacilitiesApiController {
    @Autowired
    FacilitiesRepository facilitiesRepository;

    @GetMapping
    public Object index(HttpServletRequest request, @RequestParam Integer page, @RequestParam Integer limit) {

        Page<Facilities> facilitiess = facilitiesRepository.findAll(PageRequest.of(page - 1, limit, Sort.by(Sort.Order.desc("id"))));

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(true, "Found", facilitiess));
    }

}
