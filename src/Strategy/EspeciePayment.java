package Strategy;

import Model.Conta;

public class EspeciePayment implements IPaymentStrategy{
    @Override
    public double pagar(Conta conta, double valor) {
        conta.debitar(valor);
        System.out.println("Foi pago um total de R$" + valor + "em espécie");
        return valor;
    }
}
