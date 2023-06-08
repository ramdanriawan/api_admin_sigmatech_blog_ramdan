package com.bikinaplikasi.karirku.controllers.api.v1;

import com.bikinaplikasi.karirku.entity.ResponseJson;
import com.bikinaplikasi.karirku.entity.Room.Room;
import com.bikinaplikasi.karirku.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/v1/room")
public class RoomApiController {
    @Autowired
    RoomRepository roomRepository;

    @GetMapping
    public Object index(HttpServletRequest request, @RequestParam Integer page, @RequestParam Integer limit) {

        Page<Room> rooms = roomRepository.findAll(PageRequest.of(page - 1, limit, Sort.by(Sort.Order.desc("id"))));

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(true, "Found", rooms));
    }
}
