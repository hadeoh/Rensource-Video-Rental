package com.rensource.videorental.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "Orders")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends Audit implements Serializable {

    @Column(nullable = false)
    private String title;

    @Column(name = "rented_by", nullable = false)
    private String rentedBy;

    @Column(name = "rent_days", nullable = false)
    private Integer rentDays;

    @Column(name = "rent_amount", nullable = false)
    private BigDecimal rentAmount;

}
