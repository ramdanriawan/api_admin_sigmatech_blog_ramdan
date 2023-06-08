package com.bikinaplikasi.karirku.entity.OrderFacilities;

import com.bikinaplikasi.karirku.entity.Order.Order;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "order_facilities")
@DiscriminatorColumn(

)
@DiscriminatorValue(value = "orderFacilities")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorOptions(force = true)
public class OrderFacilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "order_id")
    @JsonProperty("order_id")
    protected Integer orderId;

    @JsonProperty("facilities_id")
    @Column(name = "facilities_id")
    protected Integer facilitiesId;

    @Column(name = "created_at", insertable = false, updatable = false)
    protected Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "updated_at")
    protected Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "deleted_at")
    protected Timestamp deletedAt;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Order order;

}
