package com.codecomet.projects.airBnbApp.entity;

import com.codecomet.projects.airBnbApp.util.StringListConverter;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "room_seq")
    @SequenceGenerator(name = "room_seq", sequenceName = "generic_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id",nullable = false)
    private Hotel hotel;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal basePrice;

    @Lob
    @Column(name = "photos", columnDefinition = "CLOB")
    @Convert(converter = StringListConverter.class)
    private Set<String> photos;

    @Lob
    @Column(name = "amenities", columnDefinition = "CLOB")
    @Convert(converter = StringListConverter.class)
    private Set<String> amenities;

    @Column(nullable = false)
    private Integer totalCount;

    @Column(nullable = false)
    private Integer capacity;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;



}
