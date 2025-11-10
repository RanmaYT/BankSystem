package View;

import Controller.AdminController;
import Controller.ContaController;
import DTOs.ExtratoBancarioDTO;
import DTOs.UserDTO;
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

    }

    public void menuPrincipalCliente(){
        System.out.println("Escolha uma opção:");
        System.out.println("[1] Ver saldo");
        System.out.println("[2] Sacar");
        System.out.println("[3] Depositar");
        System.out.println("[4] Realizar pagamento");
        System.out.println("[5] Ver Extrato");

        int opcao = input.getIntegerInput("|| ");

        switch (opcao) {
            case 1:
                System.out.println("Saldo: R$" + contaController.verSaldo());
                break;
            case 2:
                double valorSaque = input.getDoubleInput("Valor a ser sacado: ");
                contaController.sacar(valorSaque);
                break;
            case 3:
                double valorDeposito = input.getDoubleInput("Valor a ser depositado: ");
                contaController.depositar(valorDeposito);
                break;
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
                break;
            case 5:
                mostrarExtrato(contaController.pegarExtrato());
                break;
            default:
                System.out.println("Valor inválido, usuário!");
                break;
        }
    }

    public void menuPrincipalAdmin(){
        System.out.println("Escolha uma opção:");
        System.out.println("[1] Cadastrar cliente");
        System.out.println("[2] Bloquear cliente");
        System.out.println("[3] Desbloquear cliente");
        System.out.println("[4] Verificar cliente");
        System.out.println("[5] Simular cliente");

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
                break;
            case 2:
                int idBloqueio = input.getIntegerInput("Digite o ID do cliente para bloquear a conta: ");
                adminController.bloquearCliente(idBloqueio);
                break;
            case 3:
                int idDesbloqueio = input.getIntegerInput("Digite o ID do cliente para desbloquear a conta: ");
                adminController.desbloquearCliente(idDesbloqueio);
                break;
            case 4:
                int idConsulta = input.getIntegerInput("Digite o ID do cliente buscado: ");
                mostrarInfoUsuario(adminController.getUserInfo(idConsulta));
                break;
            case 5:
                int idSimulacao = input.getIntegerInput("Digite o ID do cliente que você quer simular: ");

                SessionManager.getInstance().setUsuarioLogado(UserRepository.getInstance().acharPorId(idSimulacao));

                System.out.println("Simulando: " + SessionManager.getInstance().getUsuarioLogado().getNome());

                menuPrincipalCliente();
                break;
            default:
                System.out.println("Valor inválido");
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
