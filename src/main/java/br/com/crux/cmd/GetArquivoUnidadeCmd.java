package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.entity.Arquivo;
import br.com.crux.entity.Unidade;
import br.com.crux.exception.ParametroNaoInformadoException;

@Component
public class GetArquivoUnidadeCmd {

	@Autowired GetUnidadePorIdCmd getUnidadePorIdCmd;
	@Autowired ArquivoRepository arquivoRepository;

	public byte[] get(Long idUnidade) {
		if (Objects.isNull(idUnidade)) {
			throw new ParametroNaoInformadoException("Erro ao buscar o arquivo, parâmetro não informado no resource.");
		}

		Unidade unidade = getUnidadePorIdCmd.getUnidade(idUnidade);

		if (unidade.getIdArquivo() == null) {
			return null;
		}

		Optional<Arquivo> arquivo = arquivoRepository.findById(unidade.getIdArquivo());
		if (!arquivo.isPresent()) {
			return null;
		}

		return arquivo.get().getBlob();
	}
}
