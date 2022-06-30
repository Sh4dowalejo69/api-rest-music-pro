package cl.pro.music.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.pro.music.rest.api.entity.CompradorEntity;

public interface ICompradorRepository extends JpaRepository<CompradorEntity, Integer> {

}
