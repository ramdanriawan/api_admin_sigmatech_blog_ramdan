package com.bikinaplikasi.karirku.entity.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@DiscriminatorValue(value = "user")
@DiscriminatorOptions(force = true)
public class User {
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Expose
    @Column(name = "name")
    protected String name;

    @Expose
    @Column(name = "email", unique = true)
    protected String email;

    @Expose
    @Column(name = "password")
    protected String password;

    @Expose
    @Column(name = "level")
    protected String level = "Karyawan";

    @Expose
    @Column(name = "created_at")
    @JsonProperty("created_at")
    protected Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

    @Expose
    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    protected Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());

    @Expose
    @Column(name = "deleted_at")
    @JsonProperty("deleted_at")
    protected Timestamp deletedAt;

    @Expose
    @Transient
    private String token;
}
