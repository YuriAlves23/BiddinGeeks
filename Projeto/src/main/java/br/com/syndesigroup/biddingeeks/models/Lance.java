package br.com.syndesigroup.biddingeeks.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_lance", sequenceName = "seq_lance", allocationSize = 1, initialValue = 1)
public class Lance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lance")
	private Long id;
	@ManyToOne
	private Usuario comprador;
	@ManyToOne
	private Produto produto;
	
	private LocalDateTime dataHora;
	private Double preco;

	public Lance() {

	}

	public Lance(Long id, Usuario comprador, Produto produto, LocalDateTime dataHora, Double preco) {
		super();
		this.id = id;
		this.comprador = comprador;
		this.produto = produto;
		this.dataHora = dataHora;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
