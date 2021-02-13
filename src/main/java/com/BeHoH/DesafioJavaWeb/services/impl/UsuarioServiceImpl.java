package com.BeHoH.DesafioJavaWeb.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BeHoH.DesafioJavaWeb.dtos.UsuarioNewDto;
import com.BeHoH.DesafioJavaWeb.dtos.responses.UsuarioResponseDto;
import com.BeHoH.DesafioJavaWeb.dtos.responses.UsuarioResponseListDto;
import com.BeHoH.DesafioJavaWeb.models.dao.repositories.UsuarioRepository;
import com.BeHoH.DesafioJavaWeb.models.entities.Evento;
import com.BeHoH.DesafioJavaWeb.models.entities.Usuario;
import com.BeHoH.DesafioJavaWeb.services.EventoService;
import com.BeHoH.DesafioJavaWeb.services.UsuarioService;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioRepository usuariorepository;
	
	@Autowired
	private EventoService eventoService;
			
	@Override
	public Usuario Insert(Usuario obj) {
		
		log.info("Cadastrando um Usuário: {}", obj);
		
		obj = usuariorepository.save(obj);
			
		return obj;
		
	}

	@Override
	public List<Usuario> FindAll() {

		log.info("Listando todos os Usuarios{}");
		
		   return usuariorepository.findAll();
		 
	}

	@Override
	public Usuario DtotoEntity(UsuarioNewDto usuarionewdto) {
			
		Usuario usuario = new Usuario();
		
		usuario.setNome(usuarionewdto.getNome());
		
		return  usuario;
			
	}
	
	@Override
	public UsuarioNewDto EntitytoDto(Usuario usuario) {
			
			UsuarioNewDto usuarionewdto = new UsuarioNewDto();
		
			usuarionewdto.setNome(usuario.getNome());
							
			return usuarionewdto;
		
	}
	
	@Override
	public Usuario Search(String nome) throws ObjectNotFoundException {
		
		Usuario usuario = usuariorepository.findByNome(nome);
		
		if (nome == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! CPF: " + nome + ", Tipo: " + Evento.class.getName(), null);
		}

		return usuario;
	}
	
	@Override
	public List<UsuarioResponseDto> listUsuariotolistUsuarioResponseDto(List<Usuario> list_usuarios) {
			
		List<UsuarioResponseDto>listUsuarioResponseDtos = new ArrayList<UsuarioResponseDto>();
			
			for(Usuario usuario : list_usuarios ) {
			
				UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto();
				
				usuarioResponseDto.setNome(usuario.getNome());
				
				listUsuarioResponseDtos.add(usuarioResponseDto);
				
			}
					
			return listUsuarioResponseDtos;
	}
	
	@Override
	public List<UsuarioResponseListDto> listUsuariotolistUsuarioResponseListDto(List<Usuario> list_usuarios) {
			
		List<UsuarioResponseListDto>listUsuarioResponseDtos = new ArrayList<UsuarioResponseListDto>();
			
			for(Usuario usuario : list_usuarios ) {
			
				UsuarioResponseListDto usuarioResponseListDto = new UsuarioResponseListDto();
				
				usuarioResponseListDto.setNome(usuario.getNome());
				usuarioResponseListDto.setLista_eventos(eventoService.List_EventotoList_EventResponseDto(usuario.getEventos()));
				listUsuarioResponseDtos.add(usuarioResponseListDto);
				
			}
					
			return listUsuarioResponseDtos;
			
	}

}
