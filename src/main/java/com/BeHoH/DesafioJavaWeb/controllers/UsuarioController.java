package com.BeHoH.DesafioJavaWeb.controllers;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.BeHoH.DesafioJavaWeb.dtos.UsuarioNewDto;
import com.BeHoH.DesafioJavaWeb.models.entities.Usuario;
import com.BeHoH.DesafioJavaWeb.services.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@RestController
@RequestMapping({"/api"})
@Api(value = "API REST PFisica")
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	
private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioservice;
	


	@PostMapping
	@ApiOperation(value="Inseri um Usuario")
	public ResponseEntity<Void>  insert(@RequestBody UsuarioNewDto usuarionewdto) {
						
		Usuario obj = usuarioservice.DtotoEntity(usuarionewdto);
		
		log.info("Adicionando Pessoa Fisica: {}", obj.toString());
		
		try{usuarioservice.Insert(obj);
			
		
		}
		
		catch(NullPointerException erro){
						
			erro.printStackTrace();
		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
				(obj.getId()).toUri();
				
		return ResponseEntity.created(uri).build();
		
	}
	
	@ApiOperation(value="busca todos as Pessoas Fisicas")
	@GetMapping("/lista")
	ResponseEntity<List<UsuarioNewDto>> findall() {
		
		log.info("entrei na função listar do controller" );
		
		List<UsuarioNewDto>list_usuarios_newdto = usuarioservice.List_UsuariotoList_UsuarioNewDto(usuarioservice.FindAll());
				
		return ResponseEntity.ok().body(list_usuarios_newdto);
		
	}
	
}
