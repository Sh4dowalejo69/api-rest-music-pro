package cl.pro.music.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.pro.music.rest.api.entity.VentaEntity;

public interface IVentaRepository extends JpaRepository<VentaEntity, Integer> {

}
