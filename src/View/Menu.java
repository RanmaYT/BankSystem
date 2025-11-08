package View;

import Controller.AdminController;
import Controller.ContaController;
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
        System.out.println(userDTO);
    }
}
