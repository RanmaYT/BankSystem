package Controller;

import Exceptions.CredenciaisInvalidaException;
import Exceptions.ItemNotFoundException;
import Exceptions.OperacaoNaoConcluidaException;
import Model.Services.AutenticacaoService;
import SingletonSession.SessionManager;
import Util.InputUtil;
import View.View;
import org.w3c.dom.Text;

public class UIController {
    private InputUtil inputUtil;
    private AdminController adminController;
    private ContaController contaController;
    private View view;

    public UIController(InputUtil inputUtil, AdminController adminController, ContaController contaController, View view) {
        this.inputUtil = inputUtil;
        this.adminController = adminController;
        this.contaController = contaController;
        this.view = view;
    }

    public void menuPrincipal() {
        while(true) {
            view.mostrarMenuPrincipal();
            int opcao = inputUtil.getIntegerInput("|| ");

            try {
                switch (opcao) {
                    case 1:
                        String cpfCliente = inputUtil.getStringInput("Entre com seu cpf: ");
                        String senhaCliente = inputUtil.getStringInput("Digite sua senha: ");

                        // Gambiarra aqui: o service não deveria conversar diretamente com o menu.
                        AutenticacaoService authService = new AutenticacaoService();
                        authService.logIn(cpfCliente, senhaCliente);

                        menuCliente();
                        break;
                    case 2:
                        // Sistema simples de senha
                        String passeChave = inputUtil.getStringInput("Entre com a senha de administração: ");

                        if (!passeChave.equals("123Bank")) {
                            view.exibirErro("Senha inválida!!");
                            continue;
                        }

                        menuAdministrador();
                        break;
                    default:
                        view.exibirErro("Valor inválido!");
                        break;
                }
            }
            catch(Exception e) {
                view.exibirMensagem(e.getMessage());
            }
        }
    }

    public void menuCliente(){
        while(true) {
            view.mostrarMenuCliente();

            int opcao = inputUtil.getIntegerInput("--> ");

            try{
                switch (opcao) {
                    case 1:
                        view.mostrarInfoConta(contaController.pegarInfoConta());
                        continue;
                    case 2:
                        menuOperacoesFinanceiras();
                        continue;
                    case 3:
                        view.mostrarExtrato(contaController.pegarExtrato());
                        continue;
                    case 4:
                        view.exibirMensagem("Essa ação é irreversível");
                        String confirmacao = inputUtil.getStringInput("Digite seu cpf para confirmar");

                        // Uso do Singleton aumenta o acoplamento
                        if(!confirmacao.equals(SessionManager.getInstance().getUsuarioLogado().getCpf())) {
                            view.exibirMensagem("CPF errado, voltando ao menu");
                            return;
                        }

                        contaController.cancelarConta();
                    case 0:
                        view.exibirMensagem("Saindo do menu de clientes");
                        return;
                    default:
                        view.exibirErro("Valor inválido!");
                        break;
                }
            }
            catch(Exception e) {
                view.exibirMensagem(e.getMessage());
            }
        }
    }

    public void menuAdministrador(){
        while(true) {
            view.mostrarMenuAdministrador();

            int opcao = inputUtil.getIntegerInput("--> ");

            try{
                switch (opcao) {
                    case 1:
                        // Menu cadastro cliente
                        try{
                            view.exibirMensagem("=== Cadastro Cliente ===");

                            // Pega os campos necessários
                            String tipoConta = inputUtil.getAlphaInput("Tipo de conta (Poupança/Corrente): ");
                            String nome = inputUtil.getNameInput("Nome: ");
                            String senha = inputUtil.getPasswordInput("Senha: ");
                            String email = inputUtil.getEmailInput("Email: ");
                            String cpf = inputUtil.getCPFInput("CPF: ");
                            double rendaMensal = inputUtil.getDoubleInput("Renda Mensal: ");

                            adminController.cadastrarCliente(nome, senha, email, cpf, rendaMensal, tipoConta);
                        }
                        catch(IllegalArgumentException e) {
                            view.exibirErro(e.getMessage());
                        }

                        continue;
                    case 2:
                        String cpfBloqueio = inputUtil.getStringInput("Digite o cpf do cliente para bloquear a conta: ");
                        adminController.bloquearCliente(cpfBloqueio);
                        continue;
                    case 3:
                        String cpfDesbloqueio = inputUtil.getStringInput("Digite o cpf do cliente para desbloquear a conta: ");
                        adminController.desbloquearCliente(cpfDesbloqueio);
                        continue;
                    case 4:
                        String cpfConsulta = inputUtil.getStringInput("Digite o cpf do cliente buscado: ");
                        view.mostrarInfoUsuario(adminController.getUserInfo(cpfConsulta));
                        continue;
                    case 0:
                        view.exibirMensagem("Saindo do menu de administração");
                        return;
                    default:
                        view.exibirErro("Valor inválido!");
                }

            }
            catch(Exception e) {
                view.exibirMensagem(e.getMessage());
            }
        }
    }

    public void menuOperacoesFinanceiras(){
        while(true) {
            view.mostrarMenuOperacaoFinanceira();

            int opcao = inputUtil.getIntegerInput("--> ");

            try{
                switch (opcao) {
                    case 1:
                        double valorSaque = inputUtil.getDoubleInput("Valor a ser sacado: ");
                        contaController.sacar(valorSaque);
                        continue;
                    case 2:
                        double valorDeposito = inputUtil.getDoubleInput("Valor a ser depositado: ");
                        contaController.depositar(valorDeposito);
                        continue;
                    case 3:
                        boolean escolhaPagamentoValida = false;
                        int escolhaPagamento = 0;

                        while(!escolhaPagamentoValida) {
                            // Perguntar como ele vai pagar
                            view.mostrarOpcoesPagamento();

                            escolhaPagamento = inputUtil.getIntegerInput("|| ");

                            escolhaPagamentoValida = !(escolhaPagamento < 0 || escolhaPagamento > 2);
                        }

                        if(escolhaPagamento == 0) { continue; }

                        String itemPago = inputUtil.getStringInput("O que está sendo pago: ");
                        double valorPago = inputUtil.getDoubleInput("Qual o valor pago: ");

                        contaController.realizarPagamento(escolhaPagamento, itemPago, valorPago);
                        continue;
                    case 4:
                        String emailReceptor = inputUtil.getStringInput("CPF do receptor: ");
                        double valorOperacao = inputUtil.getDoubleInput("Valor a enviar: ");

                        contaController.pagarPessoa(emailReceptor, valorOperacao);
                        continue;
                    case 0:
                        System.out.println("Saindo do menu de operações monetárias");
                        return;
                    default:
                        System.out.println("Valor inválido, usuário!");
                }
            }
            catch(Exception e) {
                view.exibirMensagem(e.getMessage());
            }
        }
    }
}
