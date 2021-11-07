package br.com.magnasistemas.cachacariaapi.dto;

import java.util.Objects;

public class ProdutoDTO {

	private String nome;

	private double preco;

	private String sabor;

	private ClienteDTO cliente;

	private MarcaDTO marca;

	public ProdutoDTO() {

	}

	public ProdutoDTO(String nome, double preco, String sabor, ClienteDTO clienteDTO, MarcaDTO marca) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.sabor = sabor;
		this.cliente = clienteDTO;
		this.marca = marca;
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

	public ClienteDTO getClienteDTO() {
		return cliente;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.cliente = clienteDTO;
	}

	public MarcaDTO getMarca() {
		return marca;
	}

	public void setMarca(MarcaDTO marca) {
		this.marca = marca;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, marca, nome, preco, sabor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoDTO other = (ProdutoDTO) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(marca, other.marca)
				&& Objects.equals(nome, other.nome)
				&& Double.doubleToLongBits(preco) == Double.doubleToLongBits(other.preco)
				&& Objects.equals(sabor, other.sabor);
	}

	@Override
	public String toString() {
		return "ProdutoDTO [nome=" + nome + ", preco=" + preco + ", sabor=" + sabor + ", clienteDTO=" + cliente
				+ ", marca=" + marca + "]";
	}

}
