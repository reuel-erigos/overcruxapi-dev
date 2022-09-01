package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.to.ArquivoMetadadoTO;
import br.com.crux.to.filtro.FiltroArquivoTO;

@Component
public class ArquivoCmd {

	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private GravarArquivoUnidadeCmd gravarArquivoUnidadeCmd;
	@Autowired private GetArquivoUnidadeCmd getArquivoUnidadeCmd;
	
	@Autowired private GravarArquivoInstituicaoCmd gravarArquivoInstituicaoCmd;
	@Autowired private GetArquivoInstituicaoCmd getArquivoInstituicaoCmd;

	public void salvar(MultipartFile file) {
		gravarArquivoUnidadeCmd.gravar(file, getUnidadeLogadaCmd.get().getId());
	}

	//////////////////////////////////////////////////////////////////////////
	// Unidade
	//////////////////////////////////////////////////////////////////////////
	public void salvarComIdUnidade(MultipartFile file, Long idUnidade) {
		gravarArquivoUnidadeCmd.gravar(file, idUnidade);
	}

	public byte[] getArquivoPorUnidade(Long idUnidade) {
		return getArquivoUnidadeCmd.get(idUnidade);
	}
	
	public void alterarArquivoUnidade(MultipartFile file, Long idUnidade) {
		gravarArquivoUnidadeCmd.gravar(file, idUnidade);
	}
	
	
	//////////////////////////////////////////////////////////////////////////
	// Instituição
	//////////////////////////////////////////////////////////////////////////
	public void salvarComIdInstituicao(MultipartFile file, Long id) {
		gravarArquivoInstituicaoCmd.gravar(file, id);
	}

	public void salvarComIdInstituicao(MultipartFile file, String tipo) {
		gravarArquivoInstituicaoCmd.gravar(file, tipo);
	}
	
	public byte[] getArquivoPorInstituicao(Long id) {
		return getArquivoInstituicaoCmd.get(id);
	}
	
	public void alterarArquivoInstituicao(MultipartFile file, Long id) {
		gravarArquivoInstituicaoCmd.gravar(file, id);
	}

	public Page<ArquivoMetadadoTO> listFilteredAndPaged(FiltroArquivoTO filtro, Pageable pageable) {
		return getArquivoInstituicaoCmd.listFilteredAndPaged(filtro, pageable);
	}

	public byte[] getArquivoPorIdArquivo(Long id) {
		return getArquivoInstituicaoCmd.getIdArquivo(id);
	}
	

}
