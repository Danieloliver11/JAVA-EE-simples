package br.com.magnasistemas.cachacariaapi.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class MarcaDTO {

	private String nome;

	private LocalDate fundacao;

	private List<ProdutoDTO> produtoDTO;

	public MarcaDTO() {
		super();
	}

	public MarcaDTO(String nome, LocalDate fundacao, List<ProdutoDTO> produtoDTO) {
		super();

		this.nome = nome;
		this.fundacao = fundacao;
		this.produtoDTO = produtoDTO;
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

	public List<ProdutoDTO> getProdutoDTO() {
		return produtoDTO;
	}

	public void setProdutoDTO(List<ProdutoDTO> produtoDTO) {
		this.produtoDTO = produtoDTO;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fundacao, nome, produtoDTO);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarcaDTO other = (MarcaDTO) obj;
		return Objects.equals(fundacao, other.fundacao) && Objects.equals(nome, other.nome)
				&& Objects.equals(produtoDTO, other.produtoDTO);
	}

	@Override
	public String toString() {
		return "MarcaDTO [nome=" + nome + ", fundacao=" + fundacao + ", produtoDTO=" + produtoDTO + "]";
	}

}
