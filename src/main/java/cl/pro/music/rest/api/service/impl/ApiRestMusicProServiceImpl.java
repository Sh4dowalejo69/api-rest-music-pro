package cl.pro.music.rest.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.pro.music.rest.api.entity.ProductoEntity;
import cl.pro.music.rest.api.repository.IProductoRepository;
import cl.pro.music.rest.api.service.IApiRestMusicProService;
import cl.pro.music.rest.api.viewmodel.dto.ProductoDTO;

@Service
public class ApiRestMusicProServiceImpl implements IApiRestMusicProService{
	
	@Autowired
	private IProductoRepository iProductoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public List<ProductoDTO> obtenerListadoCategoria() {
		List<ProductoEntity> listaProducto = iProductoRepository.findAll();
		return listaProducto.stream().map(producto -> modelMapper.map(producto, ProductoDTO.class)).collect(Collectors.toList());
	}


	@Override
	public ProductoDTO obtenerProductoPorId(Integer id) {
		
		ProductoEntity producto = iProductoRepository.findById(id).orElse(null);
	
		System.out.println(producto);
		
		
		return modelMapper.map(producto,ProductoDTO.class);
	}
	
 
}
