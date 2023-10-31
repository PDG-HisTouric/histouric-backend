package com.pdg.histouric.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Video {
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "video_id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "video_uri", nullable = false, unique = true, length = 1000)
    private String videoUri;

    @Column(name = "needs_url_gen", nullable = false)
    private boolean needsUrlGen;

    @ManyToOne
    @JoinColumn(name = "history_id", nullable = false)
    private History history;

    public Video cloneVideo() {
        return Video.builder()
                .id(this.id)
                .videoUri(this.videoUri)
                .needsUrlGen(this.needsUrlGen)
                .history(this.history)
                .build();
    }
}
