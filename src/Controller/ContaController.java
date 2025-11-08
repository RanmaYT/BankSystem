package Controller;

import Model.Services.ContaService;

public class ContaController {
    private ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    public void sacar(double valor) {
        contaService.sacar(valor);
    }

    public void depositar(double valor) {
        contaService.depositar(valor);
    }

    public void realizarPagamento(double valor) {
        contaService.realizarPagamento(valor);
    }

    public void pegarExtrato() {

    }
}
