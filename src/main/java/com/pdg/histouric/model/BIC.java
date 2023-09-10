package com.pdg.histouric.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class BIC {

    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bic_id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "bic_name", nullable = false)
    private String name;

    @Column(name = "bic_description", nullable = false)
    private String description;

    @Column(name = "bic_latitude", nullable = false)
    private double latitude;

    @Column(name = "bic_longitude", nullable = false)
    private double longitude;

    @Column(name = "bic_exists", nullable = false)
    private boolean existss;

    @OneToMany
    @JoinTable(
            name = "bic_nickname",
            joinColumns = @JoinColumn(name = "bic_id"),
            inverseJoinColumns = @JoinColumn(name = "nickname_id")
    )
    private List<Nickname> nicknames;

    @OneToMany
    @JoinTable(
            name = "bic_image",
            joinColumns = @JoinColumn(name = "bic_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<HistouricImage> images;
}