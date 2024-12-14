
package com.atila.apirest.controller;

import com.atila.apirest.model.Pedido;
import com.atila.apirest.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public Pedido criar(@RequestBody Pedido pedido) {
        return pedidoService.salvar(pedido);
    }

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id);
    }

    @GetMapping("/cliente/{idCliente}")
    public List<Pedido> buscarPorIdCliente(@PathVariable Long idCliente) {
        return pedidoService.buscarPorIdCliente(idCliente);
    }

    @GetMapping("/produto/{idProduto}")
    public List<Pedido> buscarPorIdProduto(@PathVariable Long idProduto) {
        return pedidoService.buscarPorIdProduto(idProduto);
    }
}

