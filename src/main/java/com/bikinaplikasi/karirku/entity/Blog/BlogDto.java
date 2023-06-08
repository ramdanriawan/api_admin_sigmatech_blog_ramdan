package com.bikinaplikasi.karirku.entity.Blog;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blog")
public class BlogDto extends Blog {

    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "body")
    private String body;

    @NotNull
    @Column(name = "author", updatable = false)
    private Integer author;
}
