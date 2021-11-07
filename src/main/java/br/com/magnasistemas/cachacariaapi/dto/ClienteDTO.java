package br.com.magnasistemas.cachacariaapi.dto;

import java.util.List;
import java.util.Objects;

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
	public int hashCode() {
		return Objects.hash(anoNascimento, cpf, endereco, nome, produtosDTO);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteDTO other = (ClienteDTO) obj;
		return anoNascimento == other.anoNascimento && Objects.equals(cpf, other.cpf)
				&& Objects.equals(endereco, other.endereco) && Objects.equals(nome, other.nome)
				&& Objects.equals(produtosDTO, other.produtosDTO);
	}

	@Override
	public String toString() {
		return "ClienteDTO [nome=" + nome + ", cpf=" + cpf + ", anoNascimento=" + anoNascimento + ", endereco="
				+ endereco + ", produtosDTO=" + produtosDTO + "]";
	}

}
