package br.com.crux.cmd.relatorios.secretaria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.exception.RelatorioException;
import br.com.crux.exception.base.NegocioException;
import br.com.crux.infra.constantes.TipoRelatorioBeneficiario;

@Component
public class GerarRelatorioBeneficiarioCmd {
	
	@Autowired private GerarRelatorioFichaMatriculaBeneficiarioCmd gerarRelatorioFichaMatriculaCmd;
	@Autowired private GerarRelatorioDeclaracaoBeneficiarioCmd gerarRelatorioDeclaracaoBeneficiarioCmd;
	@Autowired private GerarRelatorioPasseEstudantilBeneficiarioCmd gerarRelatorioPasseEstudantilBeneficiarioCmd;
	

	
	
	public byte[] gerar(List<Integer> listaIdsPessoaFisica,String mimeType, String tipoRelatorio)  {
		try {
			byte[] dados = null;
			
			switch (tipoRelatorio) {
			case TipoRelatorioBeneficiario.FICHA_MATRICULA:
				dados = gerarRelatorioFichaMatriculaCmd.gerar(listaIdsPessoaFisica, mimeType);
				break;
			case TipoRelatorioBeneficiario.DECLARACAO:
				dados = gerarRelatorioDeclaracaoBeneficiarioCmd.gerar(listaIdsPessoaFisica, mimeType);
				break;
			case TipoRelatorioBeneficiario.PASSE_ESTUDANTIL:
				dados = gerarRelatorioPasseEstudantilBeneficiarioCmd.gerar(listaIdsPessoaFisica, mimeType);
				break;				
			default:
				throw new RelatorioException("Tipo de relatório informado está inválido.");
			}
			
			return dados;
			
		} catch (RelatorioException e) {
			throw new NegocioException(e.getMessage());
		} catch (Exception e) {
			throw new NegocioException("Não foi possível gerar o relatório.");
		}
	}
	
}
