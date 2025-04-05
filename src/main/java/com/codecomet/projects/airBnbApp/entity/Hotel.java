package com.codecomet.projects.airBnbApp.entity;

import com.codecomet.projects.airBnbApp.util.StringListConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "hotel_seq")
    @SequenceGenerator(name = "hotel_seq", sequenceName = "generic_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String city;

    @Lob
    @Column(name = "photos", columnDefinition = "CLOB")
    @Convert(converter = StringListConverter.class)
    private List<String> photos;

    @Lob
    @Column(name = "amenities", columnDefinition = "CLOB")
    @Convert(converter = StringListConverter.class)
    private List<String> amenities;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Embedded
    private HotelContactInfo contactInfo;

    @Column(nullable = false)
    private Boolean active;


}
