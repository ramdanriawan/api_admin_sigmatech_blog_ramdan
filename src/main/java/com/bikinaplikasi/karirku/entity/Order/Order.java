package com.bikinaplikasi.karirku.entity.Order;

import com.bikinaplikasi.karirku.entity.OrderFacilities.OrderFacilities;
import com.bikinaplikasi.karirku.entity.User.UserCreateDto;
import com.bikinaplikasi.karirku.entity.Room.Room;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_table")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(

)
@DiscriminatorValue(value = "Order")
@DiscriminatorOptions(force = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    protected String userId;

    @Column(name = "room_id")
    @JsonProperty("room_id")
    protected String roomId;

    @Column(name = "created_at")
    protected Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "updated_at")
    protected Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "deleted_at")
    protected Timestamp deletedAt;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserCreateDto user;

    @OneToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Room room;

    @JsonManagedReference
    @OneToMany(mappedBy = "order")
    private List<OrderFacilities> orderFacilities;
}
