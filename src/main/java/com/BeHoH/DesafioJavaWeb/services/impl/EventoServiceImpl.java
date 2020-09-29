package com.BeHoH.DesafioJavaWeb.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BeHoH.DesafioJavaWeb.dtos.EventoNewDto;
import com.BeHoH.DesafioJavaWeb.dtos.EventoResponseDto;
import com.BeHoH.DesafioJavaWeb.models.dao.repositories.EventoRepository;
import com.BeHoH.DesafioJavaWeb.models.dao.repositories.UsuarioRepository;
import com.BeHoH.DesafioJavaWeb.models.entities.Evento;
import com.BeHoH.DesafioJavaWeb.models.entities.Usuario;
import com.BeHoH.DesafioJavaWeb.services.EventoService;
import com.BeHoH.DesafioJavaWeb.services.UsuarioService;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class EventoServiceImpl implements EventoService {

private static final Logger log = LoggerFactory.getLogger(EventoServiceImpl.class);

	@Autowired
	UsuarioService usuarioservice;

	@Autowired
	private UsuarioRepository usuariorepository;

	@Autowired
	private EventoRepository eventorepository;
	
	@Override
	public Evento Insert(Evento obj) {
		
		log.info("Cadastrando um Evento {}", obj);
		
		obj = eventorepository.save(obj);
			
		return obj;
	}

	@Override
	public List<Evento> FindAll() {
		
		log.info("Listando todos os Eventos{}");
		
		return eventorepository.findAll();
		
	}
		
	@Override
	public Evento DtotoEntity(EventoNewDto eventonewdto) {
			
		Evento evento = new Evento();
		
		evento.setNome(eventonewdto.getNome());
		evento.setVagas(eventonewdto.getVagas());
		evento.setData_inicio(eventonewdto.getData_inicio());
		evento.setData_fim(eventonewdto.getData_fim());
		
		return  evento;
			
	}
	
	@Override
	public EventoResponseDto EntitytoResponseDto(Evento evento) {
			
		EventoResponseDto eventoresponsedto = new EventoResponseDto();
		
		eventoresponsedto.setNome(evento.getNome());
		eventoresponsedto.setVagas(evento.getVagas());
		eventoresponsedto.setData_inicio(DatetoString(evento.getData_inicio()));
		eventoresponsedto.setData_fim((DatetoString(evento.getData_fim())));
		
		return  eventoresponsedto;
		
	}
	
	@Override
	public List<EventoResponseDto> List_EventotoList_EventResponseDto(List<Evento> list_eventos) {
			
		List<EventoResponseDto>list_eventoresponsedtos = new ArrayList<EventoResponseDto>();
			
			for(Evento evento : list_eventos ) {
			
				EventoResponseDto eventoresponsedto = new EventoResponseDto();
				
				eventoresponsedto.setNome(evento.getNome());
				eventoresponsedto.setVagas(evento.getVagas());
				eventoresponsedto.setData_inicio(DatetoString(evento.getData_inicio()));
				eventoresponsedto.setData_fim((DatetoString(evento.getData_fim())));
								
				list_eventoresponsedtos.add(eventoresponsedto);
				
			}
					
			return list_eventoresponsedtos;
	}
	
	@Override
	public String DatetoString(Date data) {
		
		SimpleDateFormat DateFor = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String stringDate = DateFor.format(data);
		System.out.println("Date Format with dd-MM-yyyy hh:mm:ss : "+stringDate);
		
		return stringDate;
		
	}
	
	@Override
	public Date StringtoDate(String dataRecebida) throws ParseException {
		SimpleDateFormat formato = null;
		try {
			formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		Date dataFormatada = formato.parse(dataRecebida); 
		
		return dataFormatada;
	}
	/*
	
	public ResponseEntity<Void> alterar(@PathVariable String cpf, @RequestBody PFisicaNewDto objnewDto) throws ObjectNotFoundException {
		System.out.println("" + objnewDto.toString());
		
		PFisica pfobj = pfservice.DtotoEntity(objnewDto);
		System.out.println("ATRIBUTOS DA ENTIDADE \n" + pfobj.toString());
		pfobj = pfservice.Update(pfobj, cpf);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pfobj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		}
*/
	
	@Override
	public Evento Update(String nome,String nome_usuario ) throws ObjectNotFoundException {
		
		Evento eventoobjreturn = new Evento();
		
		Evento evento =	Search(nome);
		boolean listavazia = ListaCheia(evento);
		
		if(listavazia==true) {
			
			Usuario usuario = Verificarusuariolista(evento.getUsuarios(), nome_usuario);
			
			List<Usuario> usuarios = new ArrayList<Usuario>();
			usuarios = evento.getUsuarios();
			usuarios.add(usuario);
			evento.setUsuarios(usuarios);
			
		eventoobjreturn = eventorepository.save(evento);
					
		}
		
		return eventoobjreturn;
		
	}
	
	@Override
	public Usuario Verificarusuariolista(List<Usuario> usuarios, String nome) {
		
		Usuario usuario_result = new Usuario ();
		for (Usuario usuario : usuarios ) {
			if(usuario.getNome().equals(nome)) {
				usuario_result = usuario;
			}
			else {
				
					log.info("esse usuario não exite na lista{}");
									
			}
		}

		return 	usuario_result;
		
	}
	
	@Override
	public boolean ListaCheia(Evento evento) {
		
		boolean listavazia;
		List<Usuario>usuarios = evento.getUsuarios();
		
		if(evento.getVagas()>usuarios.size()) {
			
			listavazia = true;
			
		}
		
		else {
			
			listavazia=false;
		
		}
		
		return listavazia;
		
	}
	
	@Override
	public Evento Search(String nome) throws ObjectNotFoundException {
		
		Evento evento = eventorepository.findByNome(nome);
		
		if (evento == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! CPF: " + nome + ", Tipo: " + Evento.class.getName(), null);
		}

		return evento;
	}
	
	@Override
	public Iterable<Usuario> InsertUsuariotoEvento(long id, Usuario usuario) {
		Evento evento = eventorepository.findById(id);
		Iterable<Usuario> usuarios = usuariorepository.findByEvento(evento);
		return usuarios;
	}


}
