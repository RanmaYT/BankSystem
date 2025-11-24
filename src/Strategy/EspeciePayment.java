package Strategy;

import Model.ContaAbstrata;

public class EspeciePayment implements IPaymentStrategy{
    @Override
    public double pagar(ContaAbstrata conta, double valor) {
        conta.debitar(valor);
        return valor;
    }
}
