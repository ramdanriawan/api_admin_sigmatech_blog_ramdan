package com.bikinaplikasi.karirku.controllers.api.v1;

import com.bikinaplikasi.karirku.StoreError;
import com.bikinaplikasi.karirku.entity.ResponseJson;
import com.bikinaplikasi.karirku.entity.User.User;
import com.bikinaplikasi.karirku.entity.User.UserCreateDto;
import com.bikinaplikasi.karirku.helpers.Helper;
import com.bikinaplikasi.karirku.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserApiController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("login")
    public Object login(HttpServletRequest request, @RequestParam String email, @RequestParam(required = false) String password) {

        User user = userRepository.findUserByEmail(email);

        if (user == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseJson(true, "User Not Found", null));
        }

        if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseJson(false, "Wrong Password", user));
        }

        String token = Helper.createJwtToken(user);
        user.setToken(token);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(true, "User Found", user));
    }

    @PostMapping("register")
    public Object register(@Valid @RequestBody UserCreateDto user, BindingResult bindingResult, HttpServletRequest httpRequest) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseJson(false, "Errors", new StoreError(bindingResult.getAllErrors()).getStoreErrorMessage()));
        }

        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            User userSave = userRepository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(true, "Register successfully", userSave));
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(false, "Errors", e.getMostSpecificCause().getLocalizedMessage()));
        }
    }
}
