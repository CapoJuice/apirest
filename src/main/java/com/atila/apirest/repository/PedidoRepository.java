// PedidoRepository.java
package  com.atila.apirest.repository;

import com.atila.apirest.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByIdCliente(Long idCliente);
    // Para buscar por produto, verificamos se o produto está na lista
    List<Pedido> findByIdsProdutosContains(Long idProduto);
}
