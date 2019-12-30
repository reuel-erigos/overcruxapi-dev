package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCondicoesMoradiaCmd;
import br.com.crux.cmd.GetGrausInstrucaoCmd;
import br.com.crux.entity.CondicoesMoradia;
import br.com.crux.entity.GrausInstrucao;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.to.PessoaFisicaTO;

@Component
public class PessoaFisicaTOBuilder {

	@Autowired private GrausInstrucaoTOBuilder grausInstrucaoTOBuilder;
	@Autowired private CondicoesMoradiaTOBuilder condicoesMoradiaTOBuilder;
	@Autowired private GetGrausInstrucaoCmd getGrausInstrucaoCmd;
	@Autowired private GetCondicoesMoradiaCmd getCondicoesMoradiaCmd;

	public PessoaFisica build(PessoaFisicaTO p) {
		PessoaFisica retorno = new PessoaFisica();

		retorno.setId(p.getId());
		retorno.setNome(p.getNome());
		retorno.setOrgaoCi(p.getOrgaoCi());
		retorno.setClassificadorMotivoNaoTrab(p.getClassificadorMotivoNaoTrab());
		retorno.setDescricaoPessoaFisicaAtendidoOrgaoRede(p.getDescricaoPessoaFisicaAtendidoOrgaoRede());
		retorno.setBairro(p.getBairro());
		retorno.setCidadeNaturalidade(p.getCidade());
		retorno.setCondicaoMoradia(p.getCondicaoMoradia());
		retorno.setCor(p.getCor());
		retorno.setCursoEscola(p.getCursoEscola());
		retorno.setEmail(p.getEmail());
		retorno.setEndereco(p.getEndereco());
		retorno.setEscola(p.getEscola());
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
		retorno.setRegiaoEscola(p.getRegiaoEscola());
		retorno.setSerieEscola(p.getSerieEscola());
		retorno.setSexo(p.getSexo());
		retorno.setSituacaoTrabalho(p.getSituacaoTrabalho());
		retorno.setTipoEscola(p.getTipoEscola());
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
		retorno.setUfEndereco(p.getUf());
		retorno.setUfNascimento(p.getUfNascimento());
		retorno.setStatusAtendidoOrgaoRede(p.getStatusAtendidoOrgaoRede());

		Optional.ofNullable(p.getAutorizaEmail()).ifPresent(autoriza -> {
			retorno.setAutorizaEmail(autoriza.equals("true") ? "S" : "N");
		});

		retorno.setBeneficiarioBolsaFamilia(p.getBeneficiarioBolsaFamilia());
		retorno.setObservacoes(p.getObservacoes());
		retorno.setValorAluguel(p.getValorAluguel());
		retorno.setValorBolsaFamilia(p.getValorBolsaFamilia());
		retorno.setValorOutrosBenerficiosSoc(p.getValorOutrosBenerficiosSoc());
		retorno.setValorRenda(p.getValorRenda());
		retorno.setIdArquivo(p.getIdArquivo());

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

		return retorno;
	}

	public PessoaFisicaTO buildTO(PessoaFisica p) {
		PessoaFisicaTO retorno = new PessoaFisicaTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setNome(p.getNome());
		retorno.setOrgaoCi(p.getOrgaoCi());
		retorno.setClassificadorMotivoNaoTrab(p.getClassificadorMotivoNaoTrab());
		retorno.setDescricaoPessoaFisicaAtendidoOrgaoRede(p.getDescricaoPessoaFisicaAtendidoOrgaoRede());
		retorno.setBairro(p.getBairro());
		retorno.setCidade(p.getCidadeNaturalidade());
		retorno.setCondicaoMoradia(p.getCondicaoMoradia());
		retorno.setCor(p.getCor());
		retorno.setCursoEscola(p.getCursoEscola());
		retorno.setEmail(p.getEmail());
		retorno.setEndereco(p.getEndereco());
		retorno.setEscola(p.getEscola());
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
		retorno.setRegiaoEscola(p.getRegiaoEscola());
		retorno.setSerieEscola(p.getSerieEscola());
		retorno.setSexo(p.getSexo());
		retorno.setSituacaoTrabalho(p.getSituacaoTrabalho());
		retorno.setTipoEscola(p.getTipoEscola());
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
		retorno.setValorOutrosBenerficiosSoc(p.getValorOutrosBenerficiosSoc());
		retorno.setValorRenda(p.getValorRenda());
		retorno.setIdArquivo(p.getIdArquivo());
		retorno.setCondicoesMoradia(condicoesMoradiaTOBuilder.buildTO(p.getCondicoesMoradia()));
		retorno.setGrausInstrucao(grausInstrucaoTOBuilder.buildTO(p.getGrausInstrucao()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());
		
		
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

		return retorno;
	}

	public List<PessoaFisicaTO> buildAll(List<PessoaFisica> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
