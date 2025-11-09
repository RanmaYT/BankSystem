package Controller;

import DTOs.ExtratoBancarioDTO;
import Model.Services.ContaService;
import Model.Services.MonetaryService;
import Strategy.IPaymentStrategy;
import Strategy.EspeciePayment;
import Strategy.InternetBankingStrategy;

public class ContaController {
    private ContaService contaService;
    private MonetaryService monetaryService;

    public ContaController(ContaService contaService, MonetaryService monetaryService) {
        this.contaService = contaService;
        this.monetaryService = monetaryService;
    }

    public double verSaldo(){
        return contaService.pegarSaldo();
    }

    public ExtratoBancarioDTO pegarExtrato() {
        return contaService.pegarExtrato();
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
