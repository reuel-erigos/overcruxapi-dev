package br.com.crux.cmd.relatorios.gestao_pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.exception.RelatorioException;
import br.com.crux.exception.base.NegocioException;
import br.com.crux.infra.constantes.TipoRelatorioGestaoPessoal;

@Component
public class GerarRelatorioGestaoPessoalCmd {
	
	@Autowired private GerarRelatorioAniversariantesCmd gerarRelatorioAniversariantesCmd;
	@Autowired private GerarRelatorioCensoCmd gerarRelatorioCensoCmd;
	@Autowired private GerarRelatorioExamePeriodicoCmd gerarRelatorioExamePeriodicoCmd;
	@Autowired private GerarRelatorioFuncaoCmd gerarRelatorioFuncaoCmd;
	
	public byte[] gerar(List<Integer> listaIdsPessoaFisica,String mimeType, String tipoRelatorio)  {
		try {
			byte[] dados = null;
			
			switch (tipoRelatorio) {
			case TipoRelatorioGestaoPessoal.ANIVERSARIANTES:
				dados = gerarRelatorioAniversariantesCmd.gerar(listaIdsPessoaFisica, mimeType);
				break;
			case TipoRelatorioGestaoPessoal.CENSO:
				dados = gerarRelatorioCensoCmd.gerar(listaIdsPessoaFisica, mimeType);
				break;
			case TipoRelatorioGestaoPessoal.EXAME_PERIODICO:
				dados = gerarRelatorioExamePeriodicoCmd.gerar(listaIdsPessoaFisica, mimeType);
				break;				
			case TipoRelatorioGestaoPessoal.FUNCAO:
				dados = gerarRelatorioFuncaoCmd.gerar(listaIdsPessoaFisica, mimeType);
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
