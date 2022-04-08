package org.generation.redesocial.service;

import java.util.Optional;

import org.generation.redesocial.model.Usuario;
import org.generation.redesocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	/*public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
		if (repo.findByUsuario(usuario.getUsuario()).isPresent()) {
			return Optional.empty();
		}
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		return Optional.of(repo.save(usuario));
	}
	
	private boolean compararSenhas (String senhaDigitada, String senhaDoBanco) {
		
	}
*/
}
