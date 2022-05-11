package org.generation.redesocial.repository;

import java.util.List;
import java.util.Optional;

import org.generation.redesocial.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long>{
	
	public Optional<Tema> findByCategoria (String cateegoria);
	
	public List<Tema> findAllByCategoriaContainingIgnoreCase(String categoria);
}
