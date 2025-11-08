package Controller;

import DTOs.ExtratoBancarioDTO;
import Model.Services.ContaService;
import Model.Services.MonetaryServices;
import Strategy.EspeciePayment;
import Strategy.IPaymentStrategy;
import Strategy.InternetBankingStrategy;

public class ContaController {
    private ContaService contaService;
    private MonetaryServices monetaryServices;

    public ContaController(ContaService contaService, MonetaryServices monetaryServices) {
        this.contaService = contaService;
        this.monetaryServices = monetaryServices;
    }

    public double verSaldo(){
        return contaService.verSaldo();
    }

    public ExtratoBancarioDTO pegarExtrato() {
        contaService.verExtrato();
        return null;
    }

    public void sacar(double valor) {
        monetaryServices.sacar(valor);
    }

    public void depositar(double valor) {
        monetaryServices.depositar(valor);
    }

    public void realizarPagamento(int opcaoPagamento, String itemPago, double valor) {
        IPaymentStrategy strategy;

        // Cria a estratégia dependendo do que o usuário colocou
        switch (opcaoPagamento) {
            case 1:
                strategy = new EspeciePayment();
                break;
            case 2:
                strategy = new InternetBankingStrategy();
                break;
            default:
                System.out.println("Uma forma de pagamento inválida foi escolhida, encerrando processo de pagamento!");
                return;
        }

        monetaryServices.realizarPagamento(strategy, itemPago, valor);
    }


}
