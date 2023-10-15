package com.pdg.histouric.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class HistoryImage {
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id", nullable = false, unique = true)
    private UUID imageId;

    @Column(name = "image_uri", nullable = false, unique = true, length = 500)
    private String imageUri;

    @Column(name = "start_time", nullable = false)
    private int startTime;

    @Column(name = "needs_url_gen", nullable = false)
    private boolean needsUrlGen;

    @ManyToOne
    @JoinColumn(name = "history_id", nullable = false)
    private History history;
}
