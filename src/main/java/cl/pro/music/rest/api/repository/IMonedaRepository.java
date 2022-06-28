package cl.pro.music.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.pro.music.rest.api.entity.MonedaEntity;

public interface IMonedaRepository extends JpaRepository<MonedaEntity, Integer>{

}
