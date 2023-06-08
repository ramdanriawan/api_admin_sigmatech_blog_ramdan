package com.bikinaplikasi.karirku.entity.Room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn()
@Table(name = "room")
@DiscriminatorValue(value = "room")
@DiscriminatorOptions(force = true)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "no")
    protected String no;


    @Column(name = "created_at")
    protected Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "updated_at")
    protected Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "deleted_at")
    protected Timestamp deletedAt;
}
