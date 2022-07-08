package cl.pro.music.rest.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.pro.music.rest.api.service.IGetApiRestMusic;
import cl.pro.music.rest.api.viewmodel.dto.MonedaDTO;

@Service
public class GetApiRestMusicImpl implements IGetApiRestMusic{

	@Value("${url-api-mi-indicador-dolar}")
	private String urlApiRestMiIndicador;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public MonedaDTO getMonedaActual() {
		String ruta = new StringBuilder().append(urlApiRestMiIndicador).toString();
		ResponseEntity<MonedaDTO> moneda = null;
		try {
			moneda = restTemplate.getForEntity(ruta, MonedaDTO.class);

			if (moneda.getStatusCode() == HttpStatus.OK) {
				return moneda.getBody();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}
	}
	
}
