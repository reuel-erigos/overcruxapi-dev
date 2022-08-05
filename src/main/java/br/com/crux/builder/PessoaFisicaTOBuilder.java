package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCondicoesMoradiaCmd;
import br.com.crux.cmd.GetGrausInstrucaoCmd;
import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.dao.dto.FornecedorColaboradorDTO;
import br.com.crux.dao.dto.PessoaFisicaDTO;
import br.com.crux.entity.CondicoesMoradia;
import br.com.crux.entity.Escola;
import br.com.crux.entity.GrausInstrucao;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.entity.SerieEscolar;
import br.com.crux.to.ComboPessoaFisicaTO;
import br.com.crux.to.FornecedorColaboradorTO;
import br.com.crux.to.PessoaFisicaTO;

@Component
public class PessoaFisicaTOBuilder {

	@Autowired private GrausInstrucaoTOBuilder grausInstrucaoTOBuilder;
	@Autowired private CondicoesMoradiaTOBuilder condicoesMoradiaTOBuilder;
	@Autowired private EscolaTOBuilder escolaTOBuilder;
	@Autowired private SerieEscolarTOBuilder serieEscolarTOBuilder;
	@Autowired private GetGrausInstrucaoCmd getGrausInstrucaoCmd;
	@Autowired private GetCondicoesMoradiaCmd getCondicoesMoradiaCmd;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private ArquivoMetadadosTOBuilder arquivoMetadadosTOBuilder;
	
	
	public PessoaFisica build(PessoaFisicaTO p) {
		PessoaFisica retorno = new PessoaFisica();

		retorno.setId(p.getId());
		retorno.setNome(p.getNome());
		retorno.setNomeSocial(p.getNomeSocial());
		retorno.setOrgaoCi(p.getOrgaoCi());
		retorno.setClassificadorMotivoNaoTrab(p.getClassificadorMotivoNaoTrab());
		retorno.setDescricaoPessoaFisicaAtendidoOrgaoRede(p.getDescricaoPessoaFisicaAtendidoOrgaoRede());
		retorno.setBairro(p.getBairro());
		retorno.setCidadeNaturalidade(p.getCidadeNaturalidade());
		retorno.setCidade(p.getCidade());
		retorno.setCondicaoMoradia(p.getCondicaoMoradia());
		retorno.setCor(p.getCor());
		retorno.setCursoEscola(p.getCursoEscola());
		retorno.setEmail(p.getEmail());
		retorno.setEndereco(p.getEndereco());
		if(p.getEscola() != null) {
			retorno.setEscola(new Escola());
			retorno.getEscola().setId(p.getEscola().getId());
		}
		if(p.getSerieEscolar() != null) {
			retorno.setSerieEscolar(new SerieEscolar());
			retorno.getSerieEscolar().setId(p.getSerieEscolar().getId());
		}
		retorno.setEscolaridade(p.getEscolaridade());
		retorno.setEstadoCivil(p.getEstadoCivil());
		retorno.setFormaIngressoEntidade(p.getFormaIngressoEntidade());
		retorno.setMedicamentosControlados(p.getMedicamentosControlados());
		retorno.setMotivoNaoTrab(p.getMotivoNaoTrab());
		retorno.setNivelEscolaridade(p.getNivelEscolaridade());
		retorno.setOutrosBenSoc(p.getOutrosBenSoc());
		retorno.setPeriodoEscola(p.getPeriodoEscola());
		retorno.setPontoReferencia(p.getPontoReferencia());
		retorno.setProblemaSaude(p.getProblemaSaude());
		retorno.setProfissao(p.getProfissao());
		retorno.setRedeApSocRelev(p.getRedeApSocRelev());
		retorno.setRedeApoioSocial(p.getRedeApoioSocial());
		retorno.setSexo(p.getSexo());
		retorno.setSituacaoTrabalho(p.getSituacaoTrabalho());
		retorno.setTurno(p.getTurno());
		retorno.setDataNascimento(p.getDataNascimento());
		retorno.setNomeEmpresaTrabalho(p.getNomeEmpresaTrabalho());
		retorno.setNomeMae(p.getNomeMae());
		retorno.setNomePai(p.getNomePai());
		retorno.setCep(p.getCep());
		retorno.setIdentidade(p.getIdentidade());
		retorno.setCpf(p.getCpf());

		retorno.setQtPessoasResidemFamilia(p.getQtPessoasResidemFamilia());
		
		if(StringUtils.isEmpty(p.getCpf())) {
			p.setSemCpf(true);
		}else {
			p.setSemCpf(false);
		}
		
		retorno.setCts(p.getCts());
		retorno.setCelular(p.getCelular());
		retorno.setNis(p.getNis());
		retorno.setSerieCtps(p.getSerieCtps());
		retorno.setSessaoTitulo(p.getSessaoTitulo());
		retorno.setTelefoneComercial(p.getTelefoneComercial());
		retorno.setTelefoneResidencial(p.getTelefoneResidencial());
		retorno.setTituloEleitor(p.getTituloEleitor());
		retorno.setZonaTitulo(p.getZonaTitulo());
		retorno.setUfCi(p.getUfCi());
		retorno.setUfEndereco(p.getUf());
		retorno.setUfNascimento(p.getUfNascimento());
		retorno.setStatusAtendidoOrgaoRede(p.getStatusAtendidoOrgaoRede());
		retorno.setOrigemRendaFamiliar(p.getOrigemRendaFamiliar());

		Optional.ofNullable(p.getAutorizaEmail()).ifPresent(autoriza -> {
			retorno.setAutorizaEmail(autoriza.equals("true") ? "S" : "N");
		});

		retorno.setBeneficiarioBolsaFamilia(p.getBeneficiarioBolsaFamilia());
		retorno.setObservacoes(p.getObservacoes());
		retorno.setValorAluguel(p.getValorAluguel());
		retorno.setValorBolsaFamilia(p.getValorBolsaFamilia());
		
		if(Objects.nonNull(p.getMetadados())) {
			retorno.setMetadados(arquivoMetadadosTOBuilder.build(p.getMetadados()));
		}
		
		Optional.ofNullable(p.getGrausInstrucao()).ifPresent(grau -> {
			if (Objects.nonNull(grau.getId())) {
				GrausInstrucao grauInstrucao = getGrausInstrucaoCmd.getById(grau.getId());
				retorno.setGrausInstrucao(grauInstrucao);
			}

		});

		Optional.ofNullable(p.getCondicoesMoradia()).ifPresent(condicoes -> {
			if (Objects.nonNull(condicoes.getId())) {
				CondicoesMoradia condicoesMoradia = getCondicoesMoradiaCmd.getById(condicoes.getId());
				retorno.setCondicoesMoradia(condicoesMoradia);
			}
			
		});

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());
		
		if(Objects.nonNull(p.getEhDeficiente())) {
			retorno.setEhDeficiente(p.getEhDeficiente().equalsIgnoreCase("S") ? true : false);
		}
		
		if(Objects.nonNull(p.getCursandoNivelSuperior())) {
			retorno.setCursandoNivelSuperior(p.getCursandoNivelSuperior().equalsIgnoreCase("S") ? true : false);
		}
		
		retorno.setDescricaoDeficiencia(p.getDescricaoDeficiencia());
		retorno.setTipoSangue(p.getTipoSangue());
		retorno.setRaca(p.getRaca());
		retorno.setNumeroReservista(p.getNumeroReservista());
		retorno.setRegiaoMilitarReservista(p.getRegiaoMilitarReservista());
		retorno.setUfRegiaoMilitar(p.getUfRegiaoMilitar());
		retorno.setNumeroCNH(p.getNumeroCNH());
		retorno.setCategoriaCNH(p.getCategoriaCNH());
		retorno.setNumeroPisPasep(p.getNumeroPisPasep());
		retorno.setUfCTS(p.getUfCTS());
		retorno.setDataEmissaoCI(p.getDataEmissaoCI());
		retorno.setVencimentoCNH(p.getVencimentoCNH());
		
		retorno.setIdInstituicao(getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId());
		retorno.setEmailProfissional(p.getEmailProfissional());
		retorno.setCelular2(p.getCelular2());
		retorno.setFoneRecado(p.getFoneRecado());
		retorno.setSemCpf(p.getSemCpf());
		
		return retorno;
	}

	public PessoaFisicaTO buildTO(PessoaFisica p) {
		PessoaFisicaTO retorno = new PessoaFisicaTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setNome(p.getNome());
		retorno.setNomeSocial(p.getNomeSocial());
		retorno.setOrgaoCi(p.getOrgaoCi());
		retorno.setClassificadorMotivoNaoTrab(p.getClassificadorMotivoNaoTrab());
		retorno.setDescricaoPessoaFisicaAtendidoOrgaoRede(p.getDescricaoPessoaFisicaAtendidoOrgaoRede());
		retorno.setBairro(p.getBairro());
		retorno.setCidadeNaturalidade(p.getCidadeNaturalidade());
		retorno.setCidade(p.getCidade());
		retorno.setCondicaoMoradia(p.getCondicaoMoradia());
		retorno.setCor(p.getCor());
		retorno.setCursoEscola(p.getCursoEscola());
		retorno.setEmail(p.getEmail());
		retorno.setEndereco(p.getEndereco());
		if(p.getEscola() != null) {
			retorno.setEscola(escolaTOBuilder.buildTO(p.getEscola()));
		}
		if(p.getSerieEscolar() != null) {
			retorno.setSerieEscolar(serieEscolarTOBuilder.buildTO(p.getSerieEscolar()));
		}
		retorno.setEscolaridade(p.getEscolaridade());
		retorno.setEstadoCivil(p.getEstadoCivil());
		retorno.setFormaIngressoEntidade(p.getFormaIngressoEntidade());
		retorno.setMedicamentosControlados(p.getMedicamentosControlados());
		retorno.setMotivoNaoTrab(p.getMotivoNaoTrab());
		retorno.setNivelEscolaridade(p.getNivelEscolaridade());
		retorno.setOutrosBenSoc(p.getOutrosBenSoc());
		retorno.setPeriodoEscola(p.getPeriodoEscola());
		retorno.setPontoReferencia(p.getPontoReferencia());
		retorno.setProblemaSaude(p.getProblemaSaude());
		retorno.setProfissao(p.getProfissao());
		retorno.setRedeApSocRelev(p.getRedeApSocRelev());
		retorno.setRedeApoioSocial(p.getRedeApoioSocial());
		retorno.setSexo(p.getSexo());
		retorno.setSituacaoTrabalho(p.getSituacaoTrabalho());
		retorno.setTurno(p.getTurno());
		retorno.setDataNascimento(p.getDataNascimento());
		retorno.setNomeEmpresaTrabalho(p.getNomeEmpresaTrabalho());
		retorno.setNomeMae(p.getNomeMae());
		retorno.setNomePai(p.getNomePai());
		retorno.setCep(p.getCep());
		retorno.setIdentidade(p.getIdentidade());
		retorno.setCpf(p.getCpf());
		retorno.setCts(p.getCts());
		retorno.setCelular(p.getCelular());
		retorno.setNis(p.getNis());
		retorno.setSerieCtps(p.getSerieCtps());
		retorno.setSessaoTitulo(p.getSessaoTitulo());
		retorno.setTelefoneComercial(p.getTelefoneComercial());
		retorno.setTelefoneResidencial(p.getTelefoneResidencial());
		retorno.setTituloEleitor(p.getTituloEleitor());
		retorno.setZonaTitulo(p.getZonaTitulo());
		retorno.setUfCi(p.getUfCi());
		retorno.setUf(p.getUfEndereco());
		retorno.setUfNascimento(p.getUfNascimento());
		retorno.setStatusAtendidoOrgaoRede(p.getStatusAtendidoOrgaoRede());
		retorno.setAutorizaEmail(p.getAutorizaEmail());
		retorno.setBeneficiarioBolsaFamilia(p.getBeneficiarioBolsaFamilia());
		retorno.setObservacoes(p.getObservacoes());
		retorno.setValorAluguel(p.getValorAluguel());
		retorno.setValorBolsaFamilia(p.getValorBolsaFamilia());
		
		if(Objects.nonNull(p.getMetadados())) {
			retorno.setMetadados(arquivoMetadadosTOBuilder.buildTO(p.getMetadados()));
		}

		retorno.setCondicoesMoradia(condicoesMoradiaTOBuilder.buildTO(p.getCondicoesMoradia()));
		retorno.setGrausInstrucao(grausInstrucaoTOBuilder.buildTO(p.getGrausInstrucao()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());
		retorno.setOrigemRendaFamiliar(p.getOrigemRendaFamiliar());
		
		retorno.setEhDeficiente(Objects.nonNull(p.getEhDeficiente()) ? (p.getEhDeficiente() ? "S" : "N") : "N");
		retorno.setCursandoNivelSuperior(Objects.nonNull(p.getCursandoNivelSuperior()) ? (p.getCursandoNivelSuperior() ? "S" : "N") : "N");
		retorno.setDescricaoDeficiencia(p.getDescricaoDeficiencia());
		retorno.setTipoSangue(p.getTipoSangue());
		retorno.setRaca(p.getRaca());
		retorno.setNumeroReservista(p.getNumeroReservista());
		retorno.setRegiaoMilitarReservista(p.getRegiaoMilitarReservista());
		retorno.setUfRegiaoMilitar(p.getUfRegiaoMilitar());
		retorno.setNumeroCNH(p.getNumeroCNH());
		retorno.setCategoriaCNH(p.getCategoriaCNH());
		retorno.setNumeroPisPasep(p.getNumeroPisPasep());
		retorno.setUfCTS(p.getUfCTS());
		retorno.setDataEmissaoCI(p.getDataEmissaoCI());
		retorno.setVencimentoCNH(p.getVencimentoCNH());
		
		retorno.setIdInstituicao(p.getIdInstituicao());
		retorno.setEmailProfissional(p.getEmailProfissional());
		retorno.setCelular2(p.getCelular2());
		retorno.setFoneRecado(p.getFoneRecado());
		retorno.setSemCpf(p.getSemCpf());

		retorno.setQtPessoasResidemFamilia(p.getQtPessoasResidemFamilia());		

		return retorno;
	}

	public PessoaFisicaTO buildParaCombo(PessoaFisica p) {
		PessoaFisicaTO to = new PessoaFisicaTO();
		
		if(Objects.isNull(p)) {
			return to;
		}
		
		to.setId(p.getId());
		to.setNome(p.getNome());
		to.setCpf(p.getCpf());
		
		return to;
		
	}
	
	public List<PessoaFisicaTO> buildAll(List<PessoaFisica> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
	public FornecedorColaboradorTO buildTO(FornecedorColaboradorDTO dto) {
		FornecedorColaboradorTO to = new FornecedorColaboradorTO();

		if(Objects.isNull(dto)) {
			return to;
		}
		
		BeanUtils.copyProperties(dto, to);
		
		return to;
	}
	
	public ComboPessoaFisicaTO buildComboTO(PessoaFisicaDTO p) {
		ComboPessoaFisicaTO retorno = new ComboPessoaFisicaTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);
		
		return retorno;
	}
	
	public List<FornecedorColaboradorTO> buildAllDTO(List<FornecedorColaboradorDTO> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
	public List<ComboPessoaFisicaTO> buildAllComboDTO(List<PessoaFisicaDTO> dtos) {
		return dtos.stream().map(dto -> buildComboTO(dto)).collect(Collectors.toList());
	}
	
}
