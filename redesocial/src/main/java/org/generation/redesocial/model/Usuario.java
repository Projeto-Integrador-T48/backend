package org.generation.redesocial.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nome;

	@NotNull
	@Email
	private String usuario;

	@NotNull
	@Size(min = 4, max = 8)
	private String senha;

	private String foto;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getUsuario() {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return null;
=======
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
>>>>>>> 0767d1581ff7684f9e2583aba882b5a52ebdb588
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

<<<<<<< HEAD
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}	
=======
>>>>>>> 0767d1581ff7684f9e2583aba882b5a52ebdb588
}
