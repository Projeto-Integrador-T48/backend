package org.generation.redesocial.security;

import java.util.Optional;

import org.generation.redesocial.model.Usuario;
import org.generation.redesocial.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName)  throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(userName);
	
	usuario.orElseThrow(() -> new UsernameNotFoundException(userName + "Este usuario não foi encontrado"));
	
	return usuario.map(UserDetailsImpl::new).get();
	
	}
}
