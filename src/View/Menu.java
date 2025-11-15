package View;

import Controller.AdminController;
import Controller.ContaController;
import DTOs.ContaDTOs.ContaDTO;
import DTOs.ExtratoBancarioDTO;
import DTOs.UserDTOs.UserDTO;

// Esses 3 aqui são gambiarra por enquanto
import Exceptions.ItemNotFoundException;
import Exceptions.OperacaoNaoConcluidaException;
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

            try{
                switch (opcao) {
                    case 1:
                        String cpfCliente = input.getStringInput("Entre com seu cpf: ");

                        // Gambiarra aqui, segurando o fio da realidade
                        SessionManager.getInstance().setUsuarioLogado(UserRepository.getInstance().pegarPorCpf(cpfCliente));
                        SessionManager.getInstance().setContaAtiva(ContaRepository.getInstance().pegarPorTitular(cpfCliente));

                        menuPrincipalCliente();
                        break;
                    case 2:
                        menuPrincipalAdmin();
                        break;
                    default:
                        System.out.println("Valor inválido");
                }
            } catch(ItemNotFoundException e) {
                System.out.println(TextColor.RED_BOLD + e.getMessage() + TextColor.ANSI_RESET);
                System.out.println("Voltando ao menu!!");
            }
        }
    }

    public void menuPrincipalCliente(){
        while(true) {
            System.out.printf("Escolha uma opção, %s\n", SessionManager.getInstance().getUsuarioLogado().getNome());
            System.out.println("[1] Ver informações da conta");
            System.out.println("[2] Menu de operações monetárias");
            System.out.println("[3] Ver Extrato");
            System.out.println("[4] Cancelar conta");
            System.out.println("[0] Sair");

            int opcao = input.getIntegerInput("|| ");

            try{
                switch (opcao) {
                    case 1:
                        mostrarInfoConta(contaController.pegarInfoConta());
                        continue;
                    case 2:
                        menuOperacoesMonetarias();
                        continue;
                    case 3:
                        mostrarExtrato(contaController.pegarExtrato());
                        continue;
                    case 4:
                        System.out.println("Essa ação é irreversível!");
                        String confirmacao = input.getStringInput("Digite seu cpf para confirmar");

                        if(!confirmacao.equals(SessionManager.getInstance().getUsuarioLogado().getCpf())) {
                            System.out.println("A conta não foi encerrada!");
                            return;
                        }

                        contaController.cancelarConta();
                    case 0:
                        System.out.println("Saindo do menu de clientes");
                        return;
                    default:
                        System.out.println("Valor inválido, usuário!");
                }
            }
            catch (ItemNotFoundException e) {
                System.out.println(TextColor.RED_BOLD + e.getMessage() + TextColor.ANSI_RESET);
                System.out.println("Voltando ao menu do cliente!!");
            }

        }
    }

    public void menuPrincipalAdmin(){
        while(true) {
            System.out.println("Escolha uma opção:");
            System.out.println("[1] Cadastrar cliente");
            System.out.println("[2] Bloquear cliente");
            System.out.println("[3] Desbloquear cliente");
            System.out.println("[4] Verificar usuários");
            System.out.println("[0] Sair");

            int opcao = input.getIntegerInput("|| ");

            try{
                switch (opcao) {
                    case 1:
                        // Menu cadastro cliente
                        System.out.println("=== Cadastro Cliente ===");

                        // Pega os campos necessários
                        String tipoConta = input.getAlphaInput("Tipo de conta (Poupança/Corrente): ");
                        String nome = input.getAlphaInput("Nome: ");
                        String senha = input.getStringInput("Senha: ");
                        String email = input.getStringInput("Email: ");
                        String cpf = input.getStringInput("CPF: ");
                        double rendaMensal = input.getDoubleInput("Renda Mensal: ");

                        adminController.cadastrarCliente(nome, senha, email, cpf, rendaMensal, tipoConta);
                        continue;
                    case 2:
                        String cpfBloqueio = input.getStringInput("Digite o cpf do cliente para bloquear a conta: ");
                        adminController.bloquearCliente(cpfBloqueio);
                        continue;
                    case 3:
                        String cpfDesbloqueio = input.getStringInput("Digite o cpf do cliente para desbloquear a conta: ");
                        adminController.desbloquearCliente(cpfDesbloqueio);
                        continue;
                    case 4:
                        String cpfConsulta = input.getStringInput("Digite o cpf do cliente buscado: ");
                        mostrarInfoUsuario(adminController.getUserInfo(cpfConsulta));
                        continue;
                    case 0:
                        System.out.println("Saindo do menu de administração");
                        return;
                    default:
                        System.out.println("Valor inválido");
                }

            }
            catch (ItemNotFoundException e) {
                System.out.println(TextColor.RED_BOLD + e.getMessage() + TextColor.ANSI_RESET);
                System.out.println("Voltando ao menu de administração!!");
            }

        }
    }

    public void menuOperacoesMonetarias(){
        while(true) {
            System.out.println("Escolha uma operação monetária:");
            System.out.println("[1] Sacar");
            System.out.println("[2] Depositar");
            System.out.println("[3] Realizar pagamento");
            System.out.println("[4] Transferir para outra pessoa");
            System.out.println("[0] Voltar");

            int opcao = input.getIntegerInput("|| ");

            try{
                switch (opcao) {
                    case 1:
                        double valorSaque = input.getDoubleInput("Valor a ser sacado: ");
                        contaController.sacar(valorSaque);
                        continue;
                    case 2:
                        double valorDeposito = input.getDoubleInput("Valor a ser depositado: ");
                        contaController.depositar(valorDeposito);
                        continue;
                    case 3:
                        boolean escolhaPagamentoValida = false;
                        int escolhaPagamento = 0;

                        while(!escolhaPagamentoValida) {
                            // Perguntar como ele vai pagar
                            System.out.println("Como você quer pagar?");
                            System.out.println("[1] Espécie");
                            System.out.println("[2] Internet Banking");
                            System.out.println("[0] Voltar");

                            escolhaPagamento = input.getIntegerInput("|| ");

                            escolhaPagamentoValida = !(escolhaPagamento < 0 || escolhaPagamento > 2);
                        }

                        if(escolhaPagamento == 0) { continue; }

                        String itemPago = input.getStringInput("O que está sendo pago: ");
                        double valorPago = input.getDoubleInput("Qual o valor pago: ");

                        contaController.realizarPagamento(escolhaPagamento, itemPago, valorPago);
                        continue;
                    case 4:
                        String emailReceptor = input.getStringInput("CPF do receptor: ");
                        double valorOperacao = input.getDoubleInput("Valor a enviar: ");

                        contaController.pagarPessoa(emailReceptor, valorOperacao);
                        continue;
                    case 0:
                        System.out.println("Saindo do menu de operações monetárias");
                        break;
                    default:
                        System.out.println("Valor inválido, usuário!");
                        continue;
                }

                break;
            }
            catch(OperacaoNaoConcluidaException e) {
                System.out.println(TextColor.RED_BOLD + e.getMessage() + TextColor.ANSI_RESET);
                System.out.println("Voltando ao menu de operações monetárias!!");
            }
            catch(ItemNotFoundException e) {
                System.out.println(TextColor.RED_BOLD + e.getMessage() + TextColor.ANSI_RESET);
                System.out.println("Voltando ao menu de operações monetárias!!");
            }
        }
    }

    public void mostrarInfoUsuario(UserDTO userDTO){
        System.out.println("================");
        System.out.println(userDTO);
        System.out.println("================");
    }

    public void mostrarInfoConta(ContaDTO contaDTO) {
        System.out.println("================");
        System.out.println(contaDTO);
        System.out.println("================");
    }

    public void mostrarExtrato(ExtratoBancarioDTO extratoBancarioDTO) {
        System.out.println(extratoBancarioDTO);
    }
}
