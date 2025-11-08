package Model.Services;

import Model.Conta;
import Model.OperacaoExtratavel;
import SingletonRepositories.ContaRepository;
import SingletonSession.SessionManager;
import Strategy.IPaymentStrategy;

public class MonetaryServices {
    private ContaRepository contaRepo;
    private SessionManager sessionManager;
    private PagamentoService pagamentoService;

    public MonetaryServices(ContaRepository contaRepository, SessionManager sessionManager, PagamentoService pagamentoService) {
        this.contaRepo = contaRepository;
        this.sessionManager = sessionManager;
        this.pagamentoService = pagamentoService;
    }

    public void sacar(double valor){
        // Pegar a conta no repo
        Conta conta = contaRepo.acharPorTitular(sessionManager.getUsuarioLogado());

        // Realizar a operação
        conta.debitar(valor);

        // Salvar a operação no extrato
        salvarNoExtrato(conta, "Saque", -valor );
    }

    public void depositar(double valor){
        // Pegar a conta no repo
        Conta conta = contaRepo.acharPorTitular(sessionManager.getUsuarioLogado());

        // Realizar a operação
        conta.creditar(valor);

        // Salvar a operação no extrato
        salvarNoExtrato(conta, "Depósito", valor);
    }

    public void realizarPagamento(IPaymentStrategy strategy, String itemPago, double valor) {
        // Setta a estratégia que será usada
        pagamentoService.setPayStrategy(strategy);

        // Pegar a conta no repo
        Conta conta = contaRepo.acharPorTitular(sessionManager.getUsuarioLogado());

        // Realizar a operação
        double valorFinalPago = pagamentoService.realizarPagamento(conta, valor);

        // Salva a operação no extrato
        String nomeOperacao = "Pagamento: " + itemPago;
        salvarNoExtrato(conta, nomeOperacao, -valorFinalPago);
    }

    public void salvarNoExtrato(Conta conta, String nomeOperacao, double valorOperacao){
        // Cria uma operação extratável
        OperacaoExtratavel operacaoExtratavel = new OperacaoExtratavel(nomeOperacao, valorOperacao);

        // Pegar o extrato e adiciona a operação ao extrato.
        conta.getExtrato().adicionarOperacao(operacaoExtratavel);

        System.out.println("Salvo no extrato!");
    }

}
