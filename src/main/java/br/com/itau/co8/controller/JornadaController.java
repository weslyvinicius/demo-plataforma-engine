package br.com.itau.co8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.co8.dto.RequestCompleteDto;
import br.com.itau.co8.dto.ResponseStartJornadaDto;
import br.com.itau.co8.service.JornadaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/jornadas")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JornadaController {

	private final JornadaService jornadaService;

	@PostMapping("{nomeJornada}/start")
	public ResponseStartJornadaDto start(@PathVariable String nomeJornada,
            @RequestBody(required = false) RequestCompleteDto requestCompleteDTO) {

		return jornadaService.startJornada(nomeJornada, requestCompleteDTO);

	}

}
