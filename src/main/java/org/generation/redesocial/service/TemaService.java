package org.generation.redesocial.service;

import java.util.Optional;

import org.generation.redesocial.model.Tema;
import org.generation.redesocial.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemaService {
	
	@Autowired
	private TemaRepository repo;
	
	public Optional<Tema> cadastrarTema(Tema tema){
		if(repo.findAllByCategoriaContainingIgnoreCase(tema.getCategoria()).isEmpty()) {
			return Optional.of(repo.save(tema));
		}
		
		return Optional.empty();
	}
	
	public Optional<Tema> atualizarTema(Tema tema){
		if (repo.findByCategoria(tema.getCategoria()).isPresent()) {
			return Optional.empty();
		}
		
		return Optional.of(repo.save(tema));
	}

}
