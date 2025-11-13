package Model.Services;

import Model.ContaAbstrata;
import Model.ExtratoBancario;
import Model.OperacaoExtratavel;
import SingletonRepositories.ContaRepository;
import SingletonRepositories.ExtratoRepository;
import SingletonSession.SessionManager;
import Strategy.EspeciePayment;
import Strategy.IPaymentStrategy;
import Strategy.InternetBankingStrategy;

public class MonetaryService {
    private ContaRepository contaRepo;
    private SessionManager sessionManager;
    private PagamentoService pagamentoService;

    public MonetaryService(ContaRepository contaRepository, SessionManager sessionManager, PagamentoService pagamentoService) {
        this.contaRepo = contaRepository;
        this.sessionManager = sessionManager;
        this.pagamentoService = pagamentoService;
    }

    public void sacar(double valor){
        ContaAbstrata conta = sessionManager.getContaAtiva();

        // Realizar a operação
        conta.debitar(valor);

        // Salvar a operação no extrato
        salvarNoExtrato(conta.getEmailTitular(), "Saque", -valor );
    }

    public void depositar(double valor){
        ContaAbstrata conta = sessionManager.getContaAtiva();

        // Realizar a operação
        conta.creditar(valor);

        // Salvar a operação no extrato
        salvarNoExtrato(conta.getEmailTitular(), "Depósito", valor);
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

        // Setta a estratégia que será usada
        pagamentoService.setPayStrategy(strategy);

        // Pegar a conta do login atual
        ContaAbstrata conta = sessionManager.getContaAtiva();

        // Realizar a operação
        double valorFinalPago = pagamentoService.realizarPagamento(conta, valor);

        // Salva a operação no extrato
        String nomeOperacao = "Pagamento: " + itemPago;
        salvarNoExtrato(conta.getEmailTitular(), nomeOperacao, -valorFinalPago);
    }

    // TODO: MOVER PARA EXTRATO SERVICE DEPOIS
    public void salvarNoExtrato(String emailTitular, String nomeOperacao, double valorOperacao){
        // Cria uma operação extratável
        OperacaoExtratavel operacaoExtratavel = new OperacaoExtratavel(nomeOperacao, valorOperacao);

        // Pegar o extrato e adiciona a operação ao extrato.
        ExtratoBancario extratoBancario = ExtratoRepository.getInstance().pegarPorTitular(emailTitular);
        extratoBancario.adicionarOperacao(operacaoExtratavel);

        // gambiarra: sem injeção de dependência e outras loucuras
        ExtratoRepository.getInstance().atualizarLinha(extratoBancario.getEmailTitular(), extratoBancario);
    }

}
