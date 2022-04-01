package org.generation.redesocial.controller;

import java.util.List;

import org.generation.redesocial.model.Postagem;
import org.generation.redesocial.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/postagens")
@CrossOrigin(origins = "*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repo;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll ()
	{
		return ResponseEntity.ok(repo.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Postagem> getById (@PathVariable Long id){
		return repo.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping ("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo (@PathVariable String titulo){
		return ResponseEntity.ok(repo.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repo.save(postagem));
	}
	
	@DeleteMapping ("/{id}")
	public void delete (@PathVariable Long id) {
		repo.deleteById(id);
	}
	
	

}
