package br.com.crux.to.filtro;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.crux.dao.dto.EmpresaDTO;
import br.com.crux.infra.adapter.LocalDateTimeAdapter;
import br.com.crux.to.ComboProgramaTO;
import br.com.crux.to.ComboProjetoTO;
import br.com.crux.to.PlanosContasTO;

public class FiltroMovimentacoesTO {

	private EmpresaDTO empresa;
	
	private ComboProgramaTO programa;
	
	private ComboProjetoTO projeto;
	
	private BigDecimal valor;
	
	private BigDecimal valorCategoria;
	
	private String numeroDocumento;
	
	private PlanosContasTO contaCredito;
	
	private PlanosContasTO contaDebito;
	
	private PlanosContasTO contaAdicional;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataInicioDoc;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataFimDoc;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataVencimento;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataInicioMov;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataFimMov;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataInicioPag;
	
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime dataFimPag;
	
	private Long idUnidade;
	
	private Long idInstituicao;

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	public ComboProgramaTO getPrograma() {
		return programa;
	}

	public void setPrograma(ComboProgramaTO programa) {
		this.programa = programa;
	}

	public ComboProjetoTO getProjeto() {
		return projeto;
	}

	public void setProjeto(ComboProjetoTO projeto) {
		this.projeto = projeto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public PlanosContasTO getContaCredito() {
		return contaCredito;
	}

	public void setContaCredito(PlanosContasTO contaCredito) {
		this.contaCredito = contaCredito;
	}

	public PlanosContasTO getContaDebito() {
		return contaDebito;
	}

	public void setContaDebito(PlanosContasTO contaDebito) {
		this.contaDebito = contaDebito;
	}

	public PlanosContasTO getContaAdicional() {
		return contaAdicional;
	}

	public void setContaAdicional(PlanosContasTO contaAdicional) {
		this.contaAdicional = contaAdicional;
	}

	public LocalDateTime getDataInicioDoc() {
		return dataInicioDoc;
	}

	public void setDataInicioDoc(LocalDateTime dataInicioDoc) {
		this.dataInicioDoc = dataInicioDoc;
	}

	public LocalDateTime getDataFimDoc() {
		return dataFimDoc;
	}

	public void setDataFimDoc(LocalDateTime dataFimDoc) {
		this.dataFimDoc = dataFimDoc;
	}

	public LocalDateTime getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDateTime getDataInicioMov() {
		return dataInicioMov;
	}

	public void setDataInicioMov(LocalDateTime dataInicioMov) {
		this.dataInicioMov = dataInicioMov;
	}

	public LocalDateTime getDataFimMov() {
		return dataFimMov;
	}

	public void setDataFimMov(LocalDateTime dataFimMov) {
		this.dataFimMov = dataFimMov;
	}

	public LocalDateTime getDataInicioPag() {
		return dataInicioPag;
	}

	public void setDataInicioPag(LocalDateTime dataInicioPag) {
		this.dataInicioPag = dataInicioPag;
	}

	public LocalDateTime getDataFimPag() {
		return dataFimPag;
	}

	public void setDataFimPag(LocalDateTime dataFimPag) {
		this.dataFimPag = dataFimPag;
	}

	public Long getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Long idUnidade) {
		this.idUnidade = idUnidade;
	}

	public BigDecimal getValorCategoria() {
		return valorCategoria;
	}

	public void setValorCategoria(BigDecimal valorCategoria) {
		this.valorCategoria = valorCategoria;
	}

	public Long getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	
}
