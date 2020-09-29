package com.BeHoH.DesafioJavaWeb.controllers;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.BeHoH.DesafioJavaWeb.dtos.EventoNewDto;
import com.BeHoH.DesafioJavaWeb.dtos.EventoResponseDto;
import com.BeHoH.DesafioJavaWeb.dtos.UsuarioNewDto;
import com.BeHoH.DesafioJavaWeb.models.entities.Evento;
import com.BeHoH.DesafioJavaWeb.models.entities.Usuario;
import com.BeHoH.DesafioJavaWeb.services.EventoService;
import com.BeHoH.DesafioJavaWeb.services.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@RestController
@RequestMapping({"/apievento"})
@Api(value = "API REST Evento")
@CrossOrigin(origins = "*")
public class EventoController {
	
private static final Logger log = LoggerFactory.getLogger(EventoController.class);
	
	@Autowired
	private EventoService eventoservice;
	
	@Autowired
	private UsuarioService usuarioservice;

	@PostMapping
	@ApiOperation(value="Inserir um Evento")
	public ResponseEntity<Void> insert(@RequestBody EventoNewDto eventonewdto) {
						
		Evento obj = eventoservice.DtotoEntity(eventonewdto);
					
		log.info("Cadastrando um Evento: {}", obj.toString());
		
		try{eventoservice.Insert(obj);
					
		}
		
		catch(NullPointerException erro){
						
			erro.printStackTrace();
		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
				(obj.getId()).toUri();
				
		return ResponseEntity.created(uri).build();
		
	}
	
	@ApiOperation(value="Buscar todos os Eventos")
	@GetMapping("/listar")
	ResponseEntity<List<EventoResponseDto>> findall() {
		
		log.info("entrei na função listar do controller" );
		
		List<EventoResponseDto>list_eventos_responsedto = eventoservice.List_EventotoList_EventResponseDto(eventoservice.FindAll());
				
		return ResponseEntity.ok().body(list_eventos_responsedto);
		
	}
	
	
	@GetMapping("/{nome}")
	@ApiOperation(value="Inserir um usuario em um Evento")
	public ResponseEntity<Void>  Update(@PathVariable String nome, String nome_usuario) throws ObjectNotFoundException{
	 
		Evento evento = eventoservice.Update(nome, nome_usuario);
	
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{nome}").buildAndExpand(evento.getId()).toUri();
	return ResponseEntity.created(uri).build();

	}
	
}
