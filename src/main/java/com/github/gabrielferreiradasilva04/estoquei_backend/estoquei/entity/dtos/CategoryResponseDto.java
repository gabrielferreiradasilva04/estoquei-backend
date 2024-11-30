package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record CategoryResponseDto(UUID id, String description, LocalDate registrationDate, LocalDateTime updateDate, Boolean active) {

}
