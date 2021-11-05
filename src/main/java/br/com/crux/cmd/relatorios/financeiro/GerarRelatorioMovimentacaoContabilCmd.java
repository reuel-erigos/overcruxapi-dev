package br.com.crux.cmd.relatorios.financeiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.excel.RelatorioExcelFileExporter;
import br.com.crux.exception.RelatorioException;
import br.com.crux.exception.base.NegocioException;
import br.com.crux.infra.relatorio.GeradorRelatorio;
import br.com.crux.infra.relatorio.TipoRelatorio;
import br.com.crux.to.relatorios.financeiro.MovimentacaoContabilTO;


@Component
public class GerarRelatorioMovimentacaoContabilCmd {
	
	@Autowired private GeradorRelatorio geradorRelatorio;
	@Autowired private RelatorioExcelFileExporter relatoriExcelFileExporter;
	
	private byte[] gerarRelatorioExcel(List<MovimentacaoContabilTO> dados)  {
		try {
			String[] nomesCelulasHeader = {"Programa/Projeto", "Numero Documento", "Data Documento", "Histórico Movimentação", 
					                        "Data Movimentação", "Valor Movimentação", "Conta Destino","Conta Origem"};
			
			Object[][] dadosExcel = new Object[dados.size()][nomesCelulasHeader.length];
			
			for(int i = 0; i < dados.size(); i ++) {
				dadosExcel[i][0] = dados.get(i).getNomeProgramaProjeto();
				dadosExcel[i][1] = dados.get(i).getNumeroDocumento();
				dadosExcel[i][2] = dados.get(i).getDataDocumento();
				dadosExcel[i][3] = dados.get(i).getDescricaoCategoria();
				dadosExcel[i][4] = dados.get(i).getDataMovimentacao();
				dadosExcel[i][5] = dados.get(i).getValorCategoria();
				dadosExcel[i][6] = dados.get(i).getContaDestino();
				dadosExcel[i][7] = dados.get(i).getContaOrigem();
			}
			
			return relatoriExcelFileExporter.gerar(dadosExcel, nomesCelulasHeader);
			
		} catch (RelatorioException e) {
			throw new NegocioException(e.getMessage());
		} catch (Exception e) {
			throw new NegocioException("Não foi possível gerar o relatório.");
		}
	}	
	
	private byte[] gerarRelatorioPDF(List<MovimentacaoContabilTO> dados, String mimeType) throws Exception {
		String nomeRelatorio = "MovimentacaoContabil";
		String[] path = {"casa_azul", "financeiro", "movimentacao_contabil"};
		Map<String, Object> parametros = new HashMap<>();
		return geradorRelatorio.gerar(parametros, dados, nomeRelatorio, path, mimeType);
	}
	
	public byte[] gerar(List<MovimentacaoContabilTO> dados, String mimeType)  {
		byte[] toReturn = null;
		
		try {
			TipoRelatorio tipoRelatorio = TipoRelatorio.getPorTipo(mimeType);
			switch (tipoRelatorio) {
			case PDF:
				toReturn = gerarRelatorioPDF(dados, mimeType);
				break;
			case XLS:
				toReturn = gerarRelatorioExcel(dados);
				break;
			default:
				throw new Exception("Não é possível gerar o tipo de relatório informado (" + tipoRelatorio + "). Suportado apenas:[1-PDF, 2-XLS]");
			}
			
			return toReturn;			
		} catch (RelatorioException e) {
			throw new NegocioException(e.getMessage());
		} catch (Exception e) {
			throw new NegocioException("Não foi possível gerar o relatório.");
		}
	}	
}
