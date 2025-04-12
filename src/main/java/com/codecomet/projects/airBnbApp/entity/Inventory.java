package com.codecomet.projects.airBnbApp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
        uniqueConstraints = @UniqueConstraint(
        name = "unique_hotel_room_date",
        columnNames = {"hotel_id","room_id","inventory_date"}
) )
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "inventory_seq")
    @SequenceGenerator(name = "inventory_seq", sequenceName = "generic_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id",nullable = false)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id",nullable = false)
    private Room room;

    @Column(name = "inventory_date", nullable = false)
    private LocalDate inventoryDate;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0" )
    private Integer bookedCount;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0" )
    private Integer reservedCount;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0" )
    private Integer totalCount;

    @Column(nullable = false,precision = 5,scale = 2)
    private BigDecimal surgeFactor;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal price; //basePrice8 surgeFactor

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Boolean closed;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;





}
