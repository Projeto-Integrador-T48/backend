package org.generation.redesocial.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.generation.redesocial.model.Usuario;
import org.generation.redesocial.model.UsuarioLogin;
import org.generation.redesocial.repository.UsuarioRepository;
import org.generation.redesocial.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private UsuarioService service;

	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(repo.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Usuario> getById (@PathVariable Long id){
		return repo.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping ("/nome/{nome}")
	public ResponseEntity<List<Usuario>> getByName (@PathVariable String nome){
		return ResponseEntity.ok(repo.findAllByNomeContainingIgnoreCase(nome));
		
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@Valid @RequestBody Usuario usuario) {
		return service.cadastrarUsuario(usuario).map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> Autentication(@Valid @RequestBody Optional<UsuarioLogin> user) {
		return service.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> atualizarUsuario(@Valid @RequestBody Usuario usuario) {
		return service.atualizarUsuario(usuario).map(resp -> ResponseEntity.status(HttpStatus.OK).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

}

