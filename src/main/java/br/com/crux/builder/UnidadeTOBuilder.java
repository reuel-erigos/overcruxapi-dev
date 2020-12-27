package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCertificadoUnidadeCmd;
import br.com.crux.cmd.GetEstruturaUnidadeCmd;
import br.com.crux.cmd.GetInstituicaoCmd;
import br.com.crux.entity.Instituicao;
import br.com.crux.entity.Unidade;
import br.com.crux.enums.ClassificadorSituacaoImovel;
import br.com.crux.to.UnidadeTO;

@Component
public class UnidadeTOBuilder {

	@Autowired private InstituicaoTOBuilder instituicaoTOBuilder;
	@Autowired private GetInstituicaoCmd getInstituicaoCmd;
	@Autowired private EstruturaUnidadeTOBuilder estruturaUnidadeTOBuilder;
	@Autowired private GetEstruturaUnidadeCmd getEstruturaUnidadeCmd;
	@Autowired private CertificadoUnidadeTOBuilder certificadoUnidadeTOBuilder;
	@Autowired private GetCertificadoUnidadeCmd getCertificadoUnidadeCmd;

	public Unidade build(UnidadeTO to) {
		Unidade unidade = new Unidade();
		
		unidade.setIdUnidade(to.getIdUnidade());
		unidade.setSiglaUnidade(to.getSiglaUnidade());
		unidade.setNomeUnidade(to.getNomeUnidade());
		unidade.setEndereco(to.getEndereco());
		unidade.setTelefone(to.getTelefone());
		unidade.setDescricaoSituacaoImovel(to.getDescricaoSituacaoImovel());
		unidade.setDescricaoEstruturaFisicaImovel(to.getDescricaoEstruturaFisicaImovel());
		unidade.setUsuarioAlteracao(to.getUsuarioAlteracao());
		unidade.setVisao(to.getVisao());
		unidade.setMissao(to.getMissao());
		unidade.setEmail(to.getEmail());
		unidade.setCep(to.getCep());
		unidade.setBairro(to.getBairro());
		unidade.setUf(to.getUf());
		unidade.setCelular(to.getCelular());
		unidade.setTipoUnidade(to.getTipoUnidade());
		Optional.ofNullable(to.getClassificacaoSituacaoImovel()).ifPresent(classificador -> {
			unidade.setClassificacaoSituacaoImovel(ClassificadorSituacaoImovel.getPorTipo(classificador));
		});

		unidade.setNomeFantasia(to.getNomeFantasia());
		unidade.setCnpj(to.getCnpj());
		unidade.setInscricaoEstadual(to.getInscricaoEstadual());
		unidade.setInscricaoMunicipal(to.getInscricaoMunicipal());
		unidade.setHomePage(to.getHomePage());
		unidade.setCidade(to.getCidade());

		if (Objects.nonNull(to.getInstituicao()) && Objects.nonNull(to.getInstituicao().getId())) {
			
			Instituicao instituicao = getInstituicaoCmd.getById(to.getInstituicao().getId());
			unidade.setInstituicao(instituicao);
		}

		unidade.setIdArquivo(to.getArquivo());
		
		unidade.setNumeroCas(to.getNumeroCas());
		unidade.setNumeroCdca(to.getNumeroCdca());
		unidade.setDataVigenciaCdca(to.getDataVigenciaCdca());
		unidade.setNumeroCnas(to.getNumeroCnas());
		unidade.setDataVigenciaCas(to.getDataVigenciaCas());

		return unidade;
	}

	public UnidadeTO buildTO(Unidade entity) {
		UnidadeTO to = new UnidadeTO();

		if (Objects.isNull(entity)) {
			return to;
		}

		to.setIdUnidade(entity.getIdUnidade());
		to.setSiglaUnidade(entity.getSiglaUnidade());
		to.setNomeUnidade(entity.getNomeUnidade());
		to.setEndereco(entity.getEndereco());
		to.setTelefone(entity.getTelefone());
		to.setDescricaoSituacaoImovel(entity.getDescricaoSituacaoImovel());
		to.setDescricaoEstruturaFisicaImovel(entity.getDescricaoEstruturaFisicaImovel());
		to.setUsuarioAlteracao(entity.getUsuarioAlteracao());
		to.setVisao(entity.getVisao());
		to.setMissao(entity.getMissao());
		to.setEmail(entity.getEmail());
		to.setCep(entity.getCep());
		to.setBairro(entity.getBairro());
		to.setUf(entity.getUf());
		to.setCelular(entity.getCelular());
		to.setTipoUnidade(entity.getTipoUnidade());
		Optional.ofNullable(entity.getClassificacaoSituacaoImovel()).ifPresent(classificador -> {
			to.setClassificacaoSituacaoImovel(classificador.getTipo());
		});

		to.setNomeFantasia(entity.getNomeFantasia());
		to.setCnpj(entity.getCnpj());
		to.setInscricaoEstadual(entity.getInscricaoEstadual());
		to.setInscricaoMunicipal(entity.getInscricaoMunicipal());
		to.setHomePage(entity.getHomePage());
		to.setCidade(entity.getCidade());
		to.setArquivo(entity.getIdArquivo());

		to.setInstituicao(instituicaoTOBuilder.buildTO(entity.getInstituicao()));
		
		to.setEstruturasUnidades(estruturaUnidadeTOBuilder.buildAll(getEstruturaUnidadeCmd.getByUnidade(entity)));
		to.setCertificadosUnidade(certificadoUnidadeTOBuilder.buildAll(getCertificadoUnidadeCmd.getByUnidade(entity)));
		
		to.setNumeroCas(entity.getNumeroCas());
		to.setNumeroCdca(entity.getNumeroCdca());
		to.setDataVigenciaCdca(entity.getDataVigenciaCdca());
		to.setNumeroCnas(entity.getNumeroCnas());
		to.setDataVigenciaCas(entity.getDataVigenciaCas());

		return to;
	}

	public List<UnidadeTO> buildAllTO(List<Unidade> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
	public List<UnidadeTO> buildAllTOParaCombo(List<Unidade> dtos) {
		return dtos.stream().map(dto -> buildTOCombo(dto)).collect(Collectors.toList());
	}
	
	public UnidadeTO buildTOComUnidadeLogada(Unidade unidade) {
		UnidadeTO unidadeTO = buildTO(unidade);
		unidadeTO.setUnidadeLogada(Boolean.TRUE);
		return unidadeTO;
		
	}
	
	public UnidadeTO buildTOCombo(Unidade unidade) {
		UnidadeTO to = new UnidadeTO();

		if (Objects.isNull(unidade)) {
			return to;
		}

		
		to.setIdUnidade(unidade.getIdUnidade());
		to.setNomeFantasia(unidade.getNomeFantasia());
		to.setNomeUnidade(unidade.getNomeUnidade());
		
		return to;
	}

}
