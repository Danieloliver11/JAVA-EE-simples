package br.com.magnasistemas.cachacariaapi.dto;

public class ProdutoDTO {

	private long id;

	private String nome;

	private double preco;

	private String sabor;
	
	

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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}

	@Override
	public String toString() {
		return "ProdutoDTO [id=" + id + ", nome=" + nome + ", preco=" + preco + ", sabor=" + sabor + "]";
	}
	
	

}
