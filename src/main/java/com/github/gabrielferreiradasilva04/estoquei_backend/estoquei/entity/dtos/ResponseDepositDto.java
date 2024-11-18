package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.dtos;

import java.util.UUID;

public record ResponseDepositDto(UUID id, String title, String description, Boolean active) {

}
