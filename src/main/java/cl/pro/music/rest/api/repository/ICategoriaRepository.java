package cl.pro.music.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.pro.music.rest.api.entity.CategoriaEntity;


public interface ICategoriaRepository extends JpaRepository<CategoriaEntity,Long>{

}
