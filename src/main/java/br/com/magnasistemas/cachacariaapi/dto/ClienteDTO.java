package br.com.magnasistemas.cachacariaapi.dto;

import java.util.List;

public class ClienteDTO {

	private String nome;

	private String cpf;

	private int anoNascimento;

	private String endereco;

	private List<ProdutoDTO> produtosDTO;

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(String nome, String cpf, int anoNascimento, String endereco, List<ProdutoDTO> produtosDTO) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.anoNascimento = anoNascimento;
		this.endereco = endereco;
		this.produtosDTO = produtosDTO;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getAnoNascimento() {
		return anoNascimento;
	}

	public void setAnoNascimento(int anoNascimento) {
		this.anoNascimento = anoNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<ProdutoDTO> getProdutosDTO() {
		return produtosDTO;
	}

	public void setProdutosDTO(List<ProdutoDTO> produtosDTO) {
		this.produtosDTO = produtosDTO;
	}

	@Override
	public String toString() {
		return "ClienteDTO [nome=" + nome + ", cpf=" + cpf + ", anoNascimento=" + anoNascimento + ", endereco="
				+ endereco + ", produtosDTO=" + produtosDTO + "]";
	}

}
