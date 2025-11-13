package View;

import Controller.AdminController;
import Controller.ContaController;
import DTOs.ExtratoBancarioDTO;
import DTOs.UserDTO;

// Esses 3 aqui são gambiarra por enquanto
import SingletonRepositories.ContaRepository;
import SingletonRepositories.UserRepository;
import SingletonSession.SessionManager;

import Util.InputUtil;

public class Menu {
    private InputUtil input;
    private AdminController adminController;
    private ContaController contaController;

    public Menu(InputUtil input, AdminController adminController, ContaController contaController) {
        this.input = input;
        this.adminController = adminController;
        this.contaController = contaController;
    }

    public void menuPrincipal(){
        while(true) {
            System.out.println("Como você deseja entrar:");
            System.out.println("[1] Cliente");
            System.out.println("[2] Administrador");

            int opcao = input.getIntegerInput("|| ");

            switch (opcao) {
                case 1:
                    String emailCliente = input.getStringInput("Entre com seu email: ");

                    // Gambiarra aqui, segurando o fio da realidade
                    SessionManager.getInstance().setUsuarioLogado(UserRepository.getInstance().pegarPorEmail(emailCliente));
                    SessionManager.getInstance().setContaAtiva(ContaRepository.getInstance().pegarPorTitular(emailCliente));

                    menuPrincipalCliente();
                    break;
                case 2:
                    menuPrincipalAdmin();
                    break;
                default:
                    System.out.println("Valor inválido");
            }
        }
    }

    public void menuPrincipalCliente(){
        while(true) {
            System.out.printf("Escolha uma opção, %s\n", SessionManager.getInstance().getUsuarioLogado().getNome());
            System.out.println("[1] Ver saldo");
            System.out.println("[2] Sacar");
            System.out.println("[3] Depositar");
            System.out.println("[4] Realizar pagamento");
            System.out.println("[5] Ver Extrato");
            System.out.println("[0] Sair");

            int opcao = input.getIntegerInput("|| ");

            switch (opcao) {
                case 1:
                    System.out.println("================");
                    System.out.printf("Saldo: R$%.2f\n", contaController.verSaldo());
                    System.out.println("================");
                    continue;
                case 2:
                    double valorSaque = input.getDoubleInput("Valor a ser sacado: ");
                    contaController.sacar(valorSaque);
                    continue;
                case 3:
                    double valorDeposito = input.getDoubleInput("Valor a ser depositado: ");
                    contaController.depositar(valorDeposito);
                    continue;
                case 4:
                    boolean escolhaValida = false;
                    int escolhaPagamento = 0;

                    while(!escolhaValida) {
                        // Perguntar como ele vai pagar
                        System.out.println("Como você quer pagar?");
                        System.out.println("[1] Espécie");
                        System.out.println("[2] Internet Banking");
                        System.out.println("[0] Voltar");

                        escolhaPagamento = input.getIntegerInput("|| ");

                        escolhaValida = !(escolhaPagamento < 1 || escolhaPagamento > 2);
                        if(escolhaPagamento == 0) { return; }
                    }

                    String itemPago = input.getStringInput("O que está sendo pago: ");
                    double valorPago = input.getDoubleInput("Qual o valor pago: ");

                    contaController.realizarPagamento(escolhaPagamento, itemPago, valorPago);
                    continue;
                case 5:
                    mostrarExtrato(contaController.pegarExtrato());
                    continue;
                case 0:
                    System.out.println("Saindo do menu de clientes");
                    break;
                default:
                    System.out.println("Valor inválido, usuário!");
                    continue;
            }

            break;
        }
    }

    public void menuPrincipalAdmin(){
        while(true) {
            System.out.println("Escolha uma opção:");
            System.out.println("[1] Cadastrar cliente");
            System.out.println("[2] Bloquear cliente");
            System.out.println("[3] Desbloquear cliente");
            System.out.println("[4] Verificar cliente");
            System.out.println("[0] Sair");

            int opcao = input.getIntegerInput("|| ");

            switch (opcao) {
                case 1:
                    // Menu cadastro cliente
                    System.out.println("=== Cadastro Cliente ===");

                    // Pega os campos necessários
                    String nome = input.getAlphaInput("Nome: ");
                    String senha = input.getStringInput("Senha: ");
                    String email = input.getStringInput("Email: ");
                    String cpf = input.getStringInput("CPF: ");
                    double rendaMensal = input.getDoubleInput("Renda Mensal: ");

                    adminController.cadastrarCliente(nome, senha, email, cpf, rendaMensal);
                    continue;
                case 2:
                    String emailBloqueio = input.getStringInput("Digite o Email do cliente para bloquear a conta: ");
                    adminController.bloquearCliente(emailBloqueio);
                    continue;
                case 3:
                    String emailDesbloqueio = input.getStringInput("Digite o Email do cliente para desbloquear a conta: ");
                    adminController.desbloquearCliente(emailDesbloqueio);
                    continue;
                case 4:
                    String emailConsulta = input.getStringInput("Digite o Email do cliente buscado: ");
                    mostrarInfoUsuario(adminController.getUserInfo(emailConsulta));
                    continue;
                case 0:
                    System.out.println("Saindo do menu de administração");
                    break;
                default:
                    System.out.println("Valor inválido");
                    continue;
            }

            break;
        }
    }

    public void mostrarInfoUsuario(UserDTO userDTO){
        System.out.println("================");
        System.out.println(userDTO);
        System.out.println("================");
    }

    public void mostrarExtrato(ExtratoBancarioDTO extratoBancarioDTO) {
        System.out.println(extratoBancarioDTO);
    }
}
