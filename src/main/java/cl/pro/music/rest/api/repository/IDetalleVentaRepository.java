package cl.pro.music.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.pro.music.rest.api.entity.DetalleVentaEntity;

public interface IDetalleVentaRepository extends JpaRepository<DetalleVentaEntity, Integer>{

}
