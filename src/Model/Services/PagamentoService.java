package Model.Services;

import Model.Conta;
import Strategy.EspeciePayment;
import Strategy.IPaymentStrategy;

public class PagamentoService {
    private IPaymentStrategy payStrategy;

    public PagamentoService() {
        // Criando uma forma padrão de realizar pagamento
        this.payStrategy = new EspeciePayment();
    }

    public void setPayStrategy(IPaymentStrategy strategy) {
        this.payStrategy = payStrategy;
    }

    public void realizarPagamento(Conta conta, double valor) {
        payStrategy.pagar(conta, valor);
    }
}
