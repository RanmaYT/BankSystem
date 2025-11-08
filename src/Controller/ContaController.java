package Controller;

import DTOs.ExtratoBancarioDTO;
import Model.Services.ContaService;
import Strategy.EspeciePayment;
import Strategy.IPaymentStrategy;
import Strategy.InternetBankingStrategy;

public class ContaController {
    private ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    public double verSaldo(){
        return contaService.verSaldo();
    }

    public void sacar(double valor) {
        contaService.sacar(valor);
    }

    public void depositar(double valor) {
        contaService.depositar(valor);
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

        contaService.realizarPagamento(strategy, itemPago, valor);
    }

    public ExtratoBancarioDTO pegarExtrato() {
        return null;
    }
}
