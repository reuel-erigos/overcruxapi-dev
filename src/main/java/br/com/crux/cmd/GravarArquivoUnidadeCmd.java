package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.builder.ArquivoBuilder;
import br.com.crux.dao.repository.ArquivoMetadadosRepository;
import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.dao.repository.UnidadeRepository;
import br.com.crux.entity.Arquivo;
import br.com.crux.entity.ArquivoMetadado;
import br.com.crux.entity.Unidade;
import br.com.crux.enums.TipoArquivoMetadado;

@Component
public class GravarArquivoUnidadeCmd {

	@Autowired private ArquivoBuilder arquivoBuilder;
	@Autowired private ArquivoRepository arquivoRepository;
	@Autowired private UnidadeRepository unidadeRepository;
	@Autowired private GetUnidadePorIdCmd getUnidadePorIdCmd;
	@Autowired private ArquivoMetadadosRepository arquivoMetadadosRepository;
	
	public void gravar(MultipartFile file, Long idUnidade) {
		Unidade unidade = getUnidadePorIdCmd.getUnidadeById(idUnidade);
		
		Arquivo arquivo = null;		
		if(Objects.nonNull(unidade.getArquivoMetadados())) {
			arquivo = arquivoRepository.findByIdMetadados(unidade.getArquivoMetadados().getId()).get();
			arquivo = arquivoBuilder.build(file, arquivo);
		} else {
			arquivo = arquivoBuilder.build(file);
		}
		
		arquivo = arquivoRepository.save(arquivo);
		arquivo.getMetadados().setTipo(TipoArquivoMetadado.LOGOMARCA_UNIDADE.getCodigo());
		ArquivoMetadado metadado = arquivoMetadadosRepository.save(arquivo.getMetadados());

		unidade.setArquivoMetadados(metadado);
		unidadeRepository.save(unidade);
	}
	
}
