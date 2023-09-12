package com.pdg.histouric.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class BICImage {

    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id", nullable = false, unique = true)
    private String imageId;

    @Column(name = "image_uri", nullable = false, unique = true)
    private String imageUri;

    @ManyToOne
    @JoinColumn(name = "bic_id")
    private BIC bic;
}
