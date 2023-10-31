package com.pdg.histouric.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RouteBICHistoryPK implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "route_id", nullable = false)
    private UUID routeId;

    @Embedded
    private BICHistoryPK bicHistoryPK;

}
