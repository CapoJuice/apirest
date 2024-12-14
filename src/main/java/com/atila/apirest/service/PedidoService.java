// PedidoService.java
package com.atila.apirest.service;

import com.atila.apirest.model.Pedido;
import com.atila.apirest.model.Produto;
import  com.atila.apirest.model.Cliente;
import com.atila.apirest.repository.PedidoRepository;
import com.atila.apirest.repository.ProdutoRepository;
import com.atila.apirest.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public Pedido salvar(Pedido pedido) {
        if (pedido.getIdCliente() == null) {
            throw new IllegalArgumentException("Pedido deve conter um id de cliente.");
        }
        Cliente cliente = clienteRepository.findById(pedido.getIdCliente()).orElse(null);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }

        if (pedido.getIdsProdutos() == null || pedido.getIdsProdutos().isEmpty()) {
            throw new IllegalArgumentException("Pedido deve conter ao menos um produto.");
        }

        for (Long idProduto : pedido.getIdsProdutos()) {
            Produto p = produtoRepository.findById(idProduto).orElse(null);
            if (p == null) {
                throw new IllegalArgumentException("Produto com id " + idProduto + " não encontrado.");
            }
        }

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public List<Pedido> buscarPorIdCliente(Long idCliente) {
        return pedidoRepository.findByIdCliente(idCliente);
    }

    public List<Pedido> buscarPorIdProduto(Long idProduto) {
        return pedidoRepository.findByIdsProdutosContains(idProduto);
    }
}
