package org.generation.redesocial.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.redesocial.model.Usuario;
import org.generation.redesocial.model.UsuarioLogin;
import org.generation.redesocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (repo.findByUsuario(usuario.getUsuario()).isPresent()) {
			return Optional.empty();
		}

		usuario.setSenha(criptografarSenha(usuario.getSenha()));

		return Optional.of(repo.save(usuario));
	}

	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user) {

		Optional<Usuario> usuario = repo.findByUsuario(user.get().getUsuario());

		if (usuario.isPresent()) {
			if (compararSenhas(user.get().getSenha(), usuario.get().getSenha())) {
				user.get().setId(usuario.get().getId());
				user.get().setNome(usuario.get().getNome());
				user.get().setFoto(usuario.get().getFoto());
				user.get().setToken(geradorBasicToken(user.get().getUsuario(), user.get().getSenha()));
				user.get().setSenha(usuario.get().getSenha());

				return user;
			}
		}

		return Optional.empty();
	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
		if (repo.findById(usuario.getId()).isPresent()) {
			Optional<Usuario> buscarUsuario = repo.findByUsuario(usuario.getUsuario());
			if (buscarUsuario.isPresent()) {
				if (buscarUsuario.get().getId() != usuario.getId())

					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario já existe!", null);
			}

			usuario.setSenha(criptografarSenha(usuario.getSenha()));

			return Optional.of(repo.save(usuario));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario não encontrado!", null);

	}

	private boolean compararSenhas(String senhaDigitada, String senhaDoBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.matches(senhaDigitada, senhaDoBanco);
	}

	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(senha);
	}

	private String geradorBasicToken(String usuario, String senha) {

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));

		return "Basic " + new String(tokenBase64);

	}
}
