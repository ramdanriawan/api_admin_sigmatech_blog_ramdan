package com.bikinaplikasi.karirku.entity.Blog;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DiscriminatorFormula;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blog")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorOptions(force = true)
@DiscriminatorColumn
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "title")
    protected String title;

    @Column(name = "body")
    protected String body;

    @Column(name = "author", updatable = false)
    protected Integer author;

    @JsonProperty("created_at")
    @Column(name = "created_at", updatable = false)
    protected Timestamp createdAt;

    @JsonProperty("updated_at")
    @Column(name = "updated_at")
    protected Timestamp updatedAt;

    @JsonProperty("deleted_at")
    @Column(name = "deleted_at", insertable = false)
    protected Timestamp deletedAt;
}
