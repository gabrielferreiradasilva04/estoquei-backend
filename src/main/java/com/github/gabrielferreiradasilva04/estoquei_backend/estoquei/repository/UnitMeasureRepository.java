package com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gabrielferreiradasilva04.estoquei_backend.estoquei.entity.UnitMeasureEntity;

public interface UnitMeasureRepository extends JpaRepository<UnitMeasureEntity, UUID>{

}
