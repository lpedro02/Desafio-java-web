package com.BeHoH.DesafioJavaWeb.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.BeHoH.DesafioJavaWeb.dtos.UsuarioNewDto;
import com.BeHoH.DesafioJavaWeb.models.dao.repositories.UsuarioRepository;
import com.BeHoH.DesafioJavaWeb.models.entities.Usuario;
import com.BeHoH.DesafioJavaWeb.services.UsuarioService;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioRepository usuariorepository;
	
		
	@Override
	public Usuario Insert(Usuario obj) {
		
		log.info("Inserindo uma Pessoa Fisica: {}", obj);
		
		obj = usuariorepository.save(obj);
			
		return obj;
		
	}

	@Override
	public List<Usuario> FindAll() {

		log.info("Listando a tabela  Pessoa Fisica {}");
		
		   return usuariorepository.findAll();
		 
	}

	@Override
	public Usuario SearchNome(String nome) throws ObjectNotFoundException {
		log.info("Buscando Pessoa Fisica pelo CPF {}", nome);
		
		
		Usuario obj = usuariorepository.findByNome(nome);
		
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! CPF: " + nome + ", Tipo: " + Usuario.class.getName(), null);
		}
		
		return obj;
	}
	
	@Override
	public Usuario DtotoEntity(UsuarioNewDto usuarionewdto) {
			
		Usuario usuario = new Usuario();
		
		usuarionewdto.setNome(usuario.getNome());
				
		return  usuario;
			
	}
	
	@Override
	public UsuarioNewDto EntitytoDto(Usuario usuario) {
			
			UsuarioNewDto usuarionewdto = new UsuarioNewDto();
		
			usuarionewdto.setNome(usuario.getNome());
							
			return usuarionewdto;
		
	}
	
	@Override
	public List<UsuarioNewDto> List_UsuariotoList_UsuarioNewDto(List<Usuario> list_usuarios) {
			
		List<UsuarioNewDto>list_usuarionewdtos = new ArrayList<UsuarioNewDto>();
			
			for(Usuario usuario : list_usuarios ) {
			
				UsuarioNewDto usuarionewdto = new UsuarioNewDto();
				
				usuarionewdto.setNome(usuario.getNome());
				
				list_usuarionewdtos.add(usuarionewdto);
				
			}
					
			return list_usuarionewdtos;
	}
	
	
	
}
