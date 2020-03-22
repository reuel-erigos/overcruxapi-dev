package br.com.crux.cmd;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MovimentosExtratoTOBuilder;
import br.com.crux.dao.ExtratoDao;
import br.com.crux.dao.dto.MovimentoExtratoDTO;
import br.com.crux.entity.ContasBancaria;
import br.com.crux.to.ExtratoTO;
import br.com.crux.to.MovimentosExtratoTO;

@Component
public class GetExtratoContaBancariaCmd {

	@Autowired
	private ExtratoDao extratoDao;
	@Autowired
	private GetContasBancariaCmd getContasBancariaCmd;
	@Autowired
	private MovimentosExtratoTOBuilder movimentosExtratoTOBuilder;
	
	public ExtratoTO getExtrato(Long idContaBancaria, String p_dataInicio, String p_dataFim) {
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate dataInicio = LocalDate.parse(p_dataInicio, pattern);
		LocalDate dataFim    = LocalDate.parse(p_dataFim   , pattern);
		
		ExtratoTO extrato = new ExtratoTO();
		
		// Busca a conta bancaria
		ContasBancaria contaBancaria = getContasBancariaCmd.getById(idContaBancaria);
				
		// Busca o saldo anterior
		Double saldoAnterior  = extratoDao.getValorSaldoAnterior(idContaBancaria, dataInicio);
		
		// Busca o saldo atual
		Double saldoAtual = extratoDao.getValorSaldoAtual(idContaBancaria, dataFim);

		// Busca os movimentos
		List<MovimentoExtratoDTO> movimentos = extratoDao.getMovimentosExtrato(idContaBancaria, dataInicio, dataFim);
		
		extrato.setDataAtual(LocalDateTime.now());
		extrato.setPeriodoExtrato(dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " à " + dataFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		extrato.setNomeBanco(contaBancaria.getNomeBanco());
		extrato.setNumeroAgencia(contaBancaria.getNumeroAgencia());
		extrato.setNumeroBanco(contaBancaria.getNumeroBanco());
		extrato.setNumeroConta(contaBancaria.getNumeroContaBancaria());
		extrato.setSaldoAtual(saldoAtual);
		extrato.setSaldoAnterior(saldoAnterior);
		extrato.setMovimentacoes(movimentosExtratoTOBuilder.buildAll(movimentos));

		double valorSaldoFinal = extrato.getSaldoAnterior() ;
		double valor = 0;
		if(extrato.getMovimentacoes() != null) {
			for (int i = 0; i < extrato.getMovimentacoes().size(); i++) {
				MovimentosExtratoTO mov = extrato.getMovimentacoes().get(i);
				
				if(Objects.nonNull(mov.getValorEntrada()) ) {
					valor = mov.getValorEntrada();
				}
				
				if(Objects.nonNull(mov.getValorSaida()) ) {
					valor = mov.getValorSaida() * -1;
				}				

				valorSaldoFinal = valorSaldoFinal + valor;
				mov.setSaldo(valorSaldoFinal);
			}
		}
		
		
		if(valorSaldoFinal != extrato.getSaldoAtual().doubleValue()) {
			extrato.setIsSaldoAtualDivergente(true);
		}
		
		return extrato;
	}
	
	
}
