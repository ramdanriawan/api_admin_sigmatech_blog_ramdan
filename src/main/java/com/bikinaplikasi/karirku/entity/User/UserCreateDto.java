package com.bikinaplikasi.karirku.entity.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto extends  User {

    @NotNull
    @Column(name = "name")
    private String name;

    @Email
    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @Size(min = 10, max = 13)
    @NotNull
    @Column(name = "phone", unique = true)
    private String phone;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "level", insertable = false, updatable = false)
    private String level = "Karyawan";

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "updated_at")
    private Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "deleted_at", insertable = false)
    private Timestamp deletedAt;

}
