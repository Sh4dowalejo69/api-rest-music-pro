package cl.pro.music.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.pro.music.rest.api.entity.TransaccionVentaEntity;

public interface ITransaccionVentaRepository extends JpaRepository<TransaccionVentaEntity, Integer> {

}
