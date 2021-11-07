package br.com.magnasistemas.cachacariaapi.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Marca implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nome;

	private LocalDate fundacao;

	@OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
	private List<Produto> produtos;

	public Marca() {
		super();
	}

	public Marca(long id, String nome, LocalDate fundacao, List<Produto> produtos) {
		super();
		this.id = id;
		this.nome = nome;
		this.fundacao = fundacao;
		this.produtos = produtos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getFundacao() {
		return fundacao;
	}

	public void setFundacao(LocalDate fundacao) {
		this.fundacao = fundacao;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fundacao, id, nome, produtos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Marca other = (Marca) obj;
		return Objects.equals(fundacao, other.fundacao) && id == other.id && Objects.equals(nome, other.nome)
				&& Objects.equals(produtos, other.produtos);
	}

	@Override
	public String toString() {
		return "Marca [id=" + id + ", nome=" + nome + ", fundacao=" + fundacao + ", produtos=" + produtos + "]";
	}

}
