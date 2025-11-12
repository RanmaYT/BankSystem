package Controller;

import DTOs.ExtratoBancarioDTO;
import Model.Services.ContaService;
import Model.Services.ExtratoService;
import Model.Services.MonetaryService;

public class ContaController {
    private ContaService contaService;
    private MonetaryService monetaryService;
    private ExtratoService extratoService;

    public ContaController(ContaService contaService, MonetaryService monetaryService, ExtratoService extratoService) {
        this.contaService = contaService;
        this.monetaryService = monetaryService;
        this.extratoService = extratoService;
    }

    public double verSaldo(){
        return contaService.pegarSaldo();
    }

    public ExtratoBancarioDTO pegarExtrato() {
        return extratoService.pegarExtratoDoUsuarioLogado();
    }

    public void sacar(double valor) {
        monetaryService.sacar(valor);
    }

    public void depositar(double valor) {
        monetaryService.depositar(valor);
    }

    public void realizarPagamento(int opcaoPagamento, String itemPago, double valor) {
        monetaryService.realizarPagamento(opcaoPagamento, itemPago, valor);
    }
}
