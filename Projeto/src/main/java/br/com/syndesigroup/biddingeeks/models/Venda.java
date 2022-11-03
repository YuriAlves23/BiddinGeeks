package br.com.syndesigroup.biddingeeks.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_venda", sequenceName = "seq_venda", allocationSize = 1, initialValue = 1)
public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_venda")
	private Long id;

	@OneToOne
	private Produto produto;

	@ManyToOne
	private Usuario comprador;
	
	private LocalDateTime dataHora;
	private Double preco;

	public Venda() {

	}

	public Venda(Long id, Produto produto, Usuario comprador, LocalDateTime dataHora, Double preco) {
		super();
		this.id = id;
		this.produto = produto;
		this.comprador = comprador;
		this.dataHora = dataHora;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Usuario getComprador() {
		return comprador;
	}

	public void setComprador(Usuario comprador) {
		this.comprador = comprador;
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
