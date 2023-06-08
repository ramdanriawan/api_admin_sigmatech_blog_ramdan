package com.bikinaplikasi.karirku.controllers.api.v1;

import com.bikinaplikasi.karirku.StoreError;
import com.bikinaplikasi.karirku.entity.Blog.Blog;
import com.bikinaplikasi.karirku.entity.Blog.BlogDeleteDto;
import com.bikinaplikasi.karirku.entity.Blog.BlogDto;
import com.bikinaplikasi.karirku.entity.ResponseJson;
import com.bikinaplikasi.karirku.entity.User.User;
import com.bikinaplikasi.karirku.exceptions.AuthorizedException;
import com.bikinaplikasi.karirku.helpers.Helper;
import com.bikinaplikasi.karirku.repository.BlogRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/blog")
public class BlogApiController {
    @Autowired
    BlogRepository blogRepository;

    @GetMapping
    public Object index(HttpServletRequest request, @RequestParam Integer page, @RequestParam Integer limit, @RequestParam String sortBy) {

        try {
            User user = Helper.checkIfUserHassBeenAuthorized(request);

            Page<Blog> blogs = blogRepository.findAll(PageRequest.of(page - 1, limit, Sort.by(Sort.Order.desc(sortBy))));

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(true, "Found", blogs));
        } catch (AuthorizedException a) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseJson(false, "Errors", a.getMessage()));
        }
    }

    @GetMapping("{id}")
    public Object show(HttpServletRequest request, @PathVariable Integer id) {

        try {
            User user = Helper.checkIfUserHassBeenAuthorized(request);

            Optional<Blog> blogs = blogRepository.findById(id);

            if (!blogRepository.findById(id).isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseJson(false, "Errors", "Blog not found!"));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(true, "Found", blogs));
        } catch (AuthorizedException a) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseJson(false, "Errors", a.getMessage()));
        }

    }

    @PostMapping
    public Object store(@Valid @RequestBody BlogDto blog, BindingResult bindingResult, HttpServletRequest request) {

        try {
            User user = Helper.checkIfUserHassBeenAuthorized(request);
            if (bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseJson(false, "Errors", new StoreError(bindingResult.getAllErrors()).getStoreErrorMessage()));
            }

            if(blogRepository.countByTitleAndAuthor(blog.getTitle(), user.getId()) > 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseJson(false, "Errors", "Title has been usage!"));
            }

            blog.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            blog.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
            BlogDto blogSave = blogRepository.save(blog);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(true, "Saving successfully", blogSave));
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(false, "Errors", e.getMostSpecificCause().getLocalizedMessage()));
        } catch (AuthorizedException a) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseJson(false, "Errors", a.getMessage()));
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Object update(@Valid @RequestBody BlogDto blog, BindingResult bindingResult, HttpServletRequest request) {

        try {
            User user = Helper.checkIfUserHassBeenAuthorized(request);

            if (bindingResult.hasErrors()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseJson(false, "Errors", new StoreError(bindingResult.getAllErrors()).getStoreErrorMessage()));
            }

            Optional<Blog> blogFind = blogRepository.findById(blog.getId());

            if (!blogFind.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseJson(false, "Errors", "Blog not found!"));
            }

            blog.setCreatedAt(blogFind.get().getCreatedAt());
            blog.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
            BlogDto blogSave = blogRepository.save(blog);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(true, "Update successfully", blogSave));
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(false, "Errors", e.getMostSpecificCause().getLocalizedMessage()));
        } catch (AuthorizedException a) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseJson(false, "Errors", a.getMessage()));
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Object delete(@Valid @RequestBody BlogDeleteDto blog, HttpServletRequest request) {

        try {
            User user = Helper.checkIfUserHassBeenAuthorized(request);

            Optional<Blog> blogFind = blogRepository.findById(blog.getId());

            if (!blogFind.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseJson(false, "Errors", "Blog not found!"));
            }

            blogRepository.deleteById(blog.getId());

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(true, "Successfully", null));
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseJson(false, "Errors", e.getMostSpecificCause().getLocalizedMessage()));
        } catch (AuthorizedException a) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseJson(false, "Errors", a.getMessage()));
        }
    }
}
