package com.bikinaplikasi.karirku.entity.Order;

import com.bikinaplikasi.karirku.entity.OrderFacilities.OrderFacilities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(

)
@Table(name = "order_table")
@DiscriminatorValue(value = "orderCreateDto")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class OrderCreateDto extends Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    @NotNull
    @JsonProperty("user_id")
    private String userId;

    @Column(name = "room_id")
    @NotNull
    @JsonProperty("room_id")
    private String roomId;

    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonProperty("order_facilities")
    private List<OrderFacilities> orderFacilities = new ArrayList<>();
}
