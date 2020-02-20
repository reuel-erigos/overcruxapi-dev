
package br.com.crux.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.crux.entity.ItensPedidosMateriais;
import br.com.crux.entity.PedidosMateriais;

@Repository
public interface ItensPedidosMateriaisRepository extends JpaRepository<ItensPedidosMateriais, Long> {

	public Optional<List<ItensPedidosMateriais>> findByPedidosMateriais(PedidosMateriais pedidosMateriais);

}
