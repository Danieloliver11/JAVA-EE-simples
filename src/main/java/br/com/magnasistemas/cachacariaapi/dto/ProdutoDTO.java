package br.com.magnasistemas.cachacariaapi.dto;

public class ProdutoDTO {

	

	private String nome;

	private double preco;

	private String sabor;
	
	
	public ProdutoDTO() {
		
	}
	public ProdutoDTO(String nome, double preco, String sabor) {
		super();
		
		this.nome = nome;
		this.preco = preco;
		this.sabor = sabor;
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
		return "ProdutoDTO nome=" + nome + ", preco=" + preco + ", sabor=" + sabor + "]";
	}
	
	

}
