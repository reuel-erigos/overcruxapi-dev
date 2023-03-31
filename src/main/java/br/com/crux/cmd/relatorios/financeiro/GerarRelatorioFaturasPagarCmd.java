package br.com.crux.cmd.relatorios.financeiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.FaturasPagarDao;
import br.com.crux.exception.RelatorioException;
import br.com.crux.exception.base.NegocioException;
import br.com.crux.infra.relatorio.GeradorRelatorio;
import br.com.crux.to.relatorios.financeiro.FaturasPagarDTO;
import br.com.crux.to.relatorios.financeiro.FaturasPagarViewDTO;

@Component
public class GerarRelatorioFaturasPagarCmd {
	
	@Autowired private GeradorRelatorio geradorRelatorio;
	
	@Autowired private FaturasPagarDao faturasPagarDao;
	
	public byte[] gerar(List<FaturasPagarViewDTO> dados, String mimeType)  {
		try {
			String nomeRelatorio = "FaturasPagar";
			String[] path = {"casa_azul", "financeiro", "faturas_pagar"};
			
			Map<String, Object> parametros = new HashMap<>();
			Set<Long> ids = dados.stream().map(item -> item.getIdMovimentacao()).collect(Collectors.toSet());
			Optional<List<FaturasPagarDTO>> listaFaturas = faturasPagarDao.getAllFilter(ids);
			return geradorRelatorio.gerar(parametros, listaFaturas.get(), nomeRelatorio, path, mimeType);
		} catch (RelatorioException e) {
			throw new NegocioException(e.getMessage());
		} catch (Exception e) {
			throw new NegocioException("Não foi possível gerar o relatório.");
		}
	}	
}
