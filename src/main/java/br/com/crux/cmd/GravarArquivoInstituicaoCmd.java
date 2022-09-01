package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.builder.ArquivoBuilder;
import br.com.crux.dao.repository.ArquivoMetadadosRepository;
import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.dao.repository.InstituicaoRepository;
import br.com.crux.entity.Arquivo;
import br.com.crux.entity.ArquivoMetadado;
import br.com.crux.entity.Instituicao;
import br.com.crux.enums.TipoArquivoMetadado;
import br.com.crux.exception.base.NegocioException;

@Component
public class GravarArquivoInstituicaoCmd {

	@Autowired private ArquivoBuilder arquivoBuilder;
	@Autowired private InstituicaoRepository repository;
	@Autowired private GetInstituicaoCmd getInstituicaoCmd;
	@Autowired private ArquivoRepository arquivoRepository;
	@Autowired private ArquivoMetadadosRepository arquivoMetadadosRepository;
	

	public void gravar(MultipartFile file, Long idInstituicao) {
		Instituicao instituicao = getInstituicaoCmd.getById(idInstituicao);
		
		Arquivo arquivo = null;		
		if(Objects.nonNull(instituicao.getMetadados())) {
			arquivo = arquivoRepository.findByIdMetadados(instituicao.getMetadados().getId()).get();
			arquivo = arquivoBuilder.build(file, arquivo);
		} else {
			arquivo = arquivoBuilder.build(file);
		}
		
		arquivo = arquivoRepository.save(arquivo);
		arquivo.getMetadados().setTipo(TipoArquivoMetadado.LOGOMARCA_INSTITUICAO.getCodigo());
		ArquivoMetadado metadado = arquivoMetadadosRepository.save(arquivo.getMetadados());

		instituicao.setArquivoMetadado(metadado);
		repository.save(instituicao);
	}
	
	public void gravar(MultipartFile file, String tipo) {
		Arquivo arquivo = arquivoBuilder.build(file);
		ArquivoMetadado recuperado =  arquivoMetadadosRepository.findByIdInstituicaoAndTipo(arquivo.getMetadados().getIdInstituicao(), tipo);
		if(recuperado != null) {
			throw new NegocioException("Imagem já cadastrada para o tipo informado.");
		}
		arquivo.getMetadados().setTipo(tipo);
		ArquivoMetadado metadado = arquivoMetadadosRepository.save(arquivo.getMetadados());
		arquivo.setMetadados(metadado);
		arquivo = arquivoRepository.save(arquivo);
	}

}
