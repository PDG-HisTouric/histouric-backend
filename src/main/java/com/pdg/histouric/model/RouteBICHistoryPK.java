package com.pdg.histouric.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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

    @Column(name = "bic_id", nullable = false)
    private UUID bicId;

    @Column(name = "history_id", nullable = false)
    private UUID historyId;

}
