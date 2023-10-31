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
public class RouteTheme {
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "route_theme_id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "route_theme_name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "theme")
    private List<Route> routes;
}
