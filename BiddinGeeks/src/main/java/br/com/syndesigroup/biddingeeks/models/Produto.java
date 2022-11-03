package br.com.syndesigroup.biddingeeks.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1, initialValue = 1)
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
	private Long id;
	private String nome;
	private String descricao;
	private boolean disponivel;
	private Double lanceMinimo;
	private LocalDate dataInicio;
	private LocalDate dataTermino;
	
	@ManyToOne
	private Usuario usuario;
	
	public Produto() {
		
	}

	public Produto(Long id, String nome, String descricao, boolean disponivel, Double lanceMinimo, LocalDate dataInicio,
			LocalDate dataTermino, Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.disponivel = disponivel;
		this.lanceMinimo = lanceMinimo;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.usuario = usuario;
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Double getLanceMinimo() {
		return lanceMinimo;
	}

	public void setLanceMinimo(Double lanceMinimo) {
		this.lanceMinimo = lanceMinimo;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDate dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
