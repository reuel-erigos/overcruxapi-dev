package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetCertificadoUnidadeCmd;
import br.com.crux.cmd.GetEstruturaUnidadeCmd;
import br.com.crux.cmd.GetInstituicaoCmd;
import br.com.crux.entity.Instituicao;
import br.com.crux.entity.Unidade;
import br.com.crux.enums.ClassificadorSituacaoImovel;
import br.com.crux.to.InstituicaoTO;
import br.com.crux.to.UnidadeTO;

@Component
public class UnidadeTOBuilder {

	@Autowired private InstituicaoTOBuilder instituicaoTOBuilder;
	@Autowired private GetInstituicaoCmd getInstituicaoCmd;
	@Autowired private EstruturaUnidadeTOBuilder estruturaUnidadeTOBuilder;
	@Autowired private GetEstruturaUnidadeCmd getEstruturaUnidadeCmd;
	@Autowired private CertificadoUnidadeTOBuilder certificadoUnidadeTOBuilder;
	@Autowired private GetCertificadoUnidadeCmd getCertificadoUnidadeCmd;
	@Autowired private ArquivoMetadadosTOBuilder arquivoMetadadosTOBuilder;

	public Unidade build(UnidadeTO to) {
		Unidade unidade = new Unidade();
		
		BeanUtils.copyProperties(to, unidade);
		
		Optional.ofNullable(to.getClassificacaoSituacaoImovel()).ifPresent(classificador -> {
			unidade.setClassificacaoSituacaoImovel(ClassificadorSituacaoImovel.getPorTipo(classificador));
		});

		if (Objects.nonNull(to.getInstituicao()) && Objects.nonNull(to.getInstituicao().getId())) {
			Instituicao instituicao = getInstituicaoCmd.getById(to.getInstituicao().getId());
			unidade.setInstituicao(instituicao);
		}

		if(Objects.nonNull(to.getArquivoMetadados()) && Objects.nonNull(to.getArquivoMetadados().getId())) {
			unidade.setArquivoMetadados(arquivoMetadadosTOBuilder.build(to.getArquivoMetadados()));
		}

		return unidade;
	}

	public UnidadeTO buildTO(Unidade entity) {
		UnidadeTO to = new UnidadeTO();

		if (Objects.isNull(entity)) {
			return to;
		}

		BeanUtils.copyProperties(entity, to);
		
		Optional.ofNullable(entity.getClassificacaoSituacaoImovel()).ifPresent(classificador -> {
			to.setClassificacaoSituacaoImovel(classificador.getTipo());
		});

		to.setNomeFantasia(entity.getNomeFantasia());
		to.setCnpj(entity.getCnpj());
		to.setInscricaoEstadual(entity.getInscricaoEstadual());
		to.setInscricaoMunicipal(entity.getInscricaoMunicipal());
		to.setHomePage(entity.getHomePage());
		to.setCidade(entity.getCidade());

		if(Objects.nonNull(entity.getArquivoMetadados()) && Objects.nonNull(entity.getArquivoMetadados().getId())) {
			to.setArquivoMetadados(arquivoMetadadosTOBuilder.buildTO(entity.getArquivoMetadados()));
		}
		
		to.setInstituicao(instituicaoTOBuilder.buildTO(entity.getInstituicao()));
		to.setEstruturasUnidades(estruturaUnidadeTOBuilder.buildAll(getEstruturaUnidadeCmd.getByUnidade(entity)));
		to.setCertificadosUnidade(certificadoUnidadeTOBuilder.buildAll(getCertificadoUnidadeCmd.getByUnidade(entity)));
		

		return to;
	}
	
	public UnidadeTO buildTOSimplificado(Unidade entity) {
		UnidadeTO to = new UnidadeTO();

		if (Objects.isNull(entity)) {
			return to;
		}

		BeanUtils.copyProperties(entity, to);
		
		Optional.ofNullable(entity.getClassificacaoSituacaoImovel()).ifPresent(classificador -> {
			to.setClassificacaoSituacaoImovel(classificador.getTipo());
		});

		to.setNomeFantasia(entity.getNomeFantasia());
		to.setCnpj(entity.getCnpj());
		to.setInscricaoEstadual(entity.getInscricaoEstadual());
		to.setInscricaoMunicipal(entity.getInscricaoMunicipal());
		to.setHomePage(entity.getHomePage());
		to.setCidade(entity.getCidade());
		to.setInstituicao(new InstituicaoTO());
		to.getInstituicao().setId(entity.getInstituicao().getId());
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
	
	public UnidadeTO buildTOComUnidadeLogadaSimplificada(Unidade unidade) {
		UnidadeTO unidadeTO = buildTOSimplificado(unidade);
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
