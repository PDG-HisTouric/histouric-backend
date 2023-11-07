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
public class Audio {
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "audio_id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "audio_uri", nullable = false, length = 1000)
    private String audioUri;

    @Column(name = "needs_url_gen", nullable = false)
    private boolean needsUrlGen;

    @OneToOne(mappedBy = "audio")
    private History history;

    public Audio cloneAudio() {
        return Audio.builder()
                .id(this.id)
                .audioUri(this.audioUri)
                .needsUrlGen(this.needsUrlGen)
                .history(this.history)
                .build();
    }
}
