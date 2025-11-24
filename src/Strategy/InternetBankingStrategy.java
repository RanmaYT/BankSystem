package Strategy;

import Model.ContaAbstrata;

public class InternetBankingStrategy implements IPaymentStrategy{
    @Override
    public double pagar(ContaAbstrata conta, double valor) {
        double novoValor = valor * 0.98;
        conta.debitar(novoValor);
        return novoValor;
    }
}
