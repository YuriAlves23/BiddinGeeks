package br.com.syndesigroup.biddingeeks.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq_lance", sequenceName = "seq_lance", allocationSize = 1, initialValue = 1)
public class Lance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lance")
	private Long id;
	private int idUsuario;
	private int idProduto;
	private LocalDate dataHora;
	private Double preco;

	public Lance() {

	}

	public Lance(Long id, int idUsuario, int idProduto, LocalDate dataHora, Double preco) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.idProduto = idProduto;
		this.dataHora = dataHora;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public LocalDate getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDate dataHora) {
		this.dataHora = dataHora;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
