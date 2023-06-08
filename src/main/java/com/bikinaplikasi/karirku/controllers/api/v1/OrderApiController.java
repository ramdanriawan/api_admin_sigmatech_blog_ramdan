package com.bikinaplikasi.karirku.controllers.api.v1;

import com.bikinaplikasi.karirku.StoreError;
import com.bikinaplikasi.karirku.entity.OrderFacilities.OrderFacilities;
import com.bikinaplikasi.karirku.entity.ResponseJson;
import com.bikinaplikasi.karirku.entity.Order.Order;
import com.bikinaplikasi.karirku.entity.Order.OrderCreateDto;
import com.bikinaplikasi.karirku.repository.FacilitiesRepository;
import com.bikinaplikasi.karirku.repository.OrderFacilitiesRepository;
import com.bikinaplikasi.karirku.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/order")
public class OrderApiController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderFacilitiesRepository orderFacilitiesRepository;

    @PostMapping
    public Object store(@Valid @RequestBody OrderCreateDto order, BindingResult bindingResult, HttpServletRequest httpRequest) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseJson(false, "Errors", new StoreError(bindingResult.getAllErrors()).getStoreErrorMessage()));
        }

        try {
            OrderCreateDto orderSave = orderRepository.save(order);

            order.getOrderFacilities().forEach((item) -> {
                item.setOrderId(orderSave.getId());
            });

            orderFacilitiesRepository.saveAll(order.getOrderFacilities());

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(true, "Saving successfully", orderSave));
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(false, "Errors", e.getMostSpecificCause().getLocalizedMessage()));
        }
    }
}
