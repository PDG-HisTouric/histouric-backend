package com.pdg.histouric.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBICForRouteDTO {

    @NotNull(message = "BIC id cannot be null")
    UUID bicId;

    @NotNull(message = "History id cannot be null")
    UUID historyId;

}
