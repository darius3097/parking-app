package com.example.parkingapp.model.dtos;

import com.example.parkingapp.model.CityEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {
    private Long id;
    private String name;

    @NotEmpty
    private String code;

    public static CityEntity convertToEntity(CityDTO cityDTO){
        return CityEntity.builder()
                .id(cityDTO.getId())
                .code(cityDTO.getCode())
                .name(cityDTO.getName())
                .build();
    }

    public static CityDTO convertToDTO(CityEntity cityEntity){
        return CityDTO.builder()
                .id(cityEntity.getId())
                .code(cityEntity.getCode())
                .name(cityEntity.getName())
                .build();
    }
}
