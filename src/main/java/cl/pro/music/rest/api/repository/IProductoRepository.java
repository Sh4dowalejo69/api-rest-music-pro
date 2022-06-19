package cl.pro.music.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.pro.music.rest.api.entity.ProductoEntity;

public interface IProductoRepository extends JpaRepository<ProductoEntity, Integer> {


}
