package Strategy;

import Model.Conta;

public class InternetBankingStrategy implements IPaymentStrategy{
    @Override
    public void pagar(Conta conta, double valor) {
        double novoValor = valor * 0.98;
        conta.debitar(novoValor);
        System.out.println("Foi pago um total de R$" + novoValor);
    }
}
