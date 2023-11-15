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
    private UUID id;

    @Column(name = "image_uri", nullable = false, length = 1000)
    private String imageUri;

    @Column(name = "start_time", nullable = false)
    private int startTime;

    @Column(name = "needs_url_gen", nullable = false)
    private boolean needsUrlGen;

    @ManyToOne
    @JoinColumn(name = "history_id", nullable = false)
    private History history;

    public HistoryImage cloneHistoryImage() {
        return HistoryImage.builder()
                .id(this.id)
                .imageUri(this.imageUri)
                .startTime(this.startTime)
                .needsUrlGen(this.needsUrlGen)
                .history(this.history)
                .build();
    }
}
