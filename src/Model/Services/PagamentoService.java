package Model.Services;

import Model.Conta;
import Strategy.IPaymentStrategy;

public class PagamentoService {
    private IPaymentStrategy payStrategy;

    public PagamentoService(IPaymentStrategy payStrategy) {
        this.payStrategy = payStrategy;
    }

    public void setPayStrategy(IPaymentStrategy payStrategy) {
        this.payStrategy = payStrategy;
    }

    public void realizarPagamento(Conta conta, double valor) {
        payStrategy.pagar(conta, valor);
    }
}
