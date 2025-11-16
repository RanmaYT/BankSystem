package View;

import DTOs.ContaDTOs.ContaDTO;
import DTOs.ExtratoBancarioDTO;
import DTOs.UserDTOs.UserDTO;
import SingletonSession.SessionManager;

public class View {
    public void mostrarMenuPrincipal() {
        System.out.println("Como você deseja entrar:");
        System.out.println("[1] Cliente");
        System.out.println("[2] Administrador");
    }

    public void mostrarMenuCliente(){
        System.out.printf("Escolha uma opção, %s\n", SessionManager.getInstance().getUsuarioLogado().getNome());
        System.out.println("[1] Ver informações da conta");
        System.out.println("[2] Menu de operações monetárias");
        System.out.println("[3] Ver Extrato");
        System.out.println("[4] Cancelar conta");
        System.out.println("[0] Sair");
    }

    public void mostrarMenuAdministrador(){
        System.out.println("Escolha uma opção:");
        System.out.println("[1] Cadastrar cliente");
        System.out.println("[2] Bloquear cliente");
        System.out.println("[3] Desbloquear cliente");
        System.out.println("[4] Verificar usuários");
        System.out.println("[0] Sair");
    }

    public void mostrarMenuOperacaoFinanceira(){
        System.out.println("Escolha uma operação monetária:");
        System.out.println("[1] Sacar");
        System.out.println("[2] Depositar");
        System.out.println("[3] Realizar pagamento");
        System.out.println("[4] Transferir para outra pessoa");
        System.out.println("[0] Voltar");
    }

    public void mostrarOpcoesPagamento(){
        System.out.println("Como você quer pagar?");
        System.out.println("[1] Espécie");
        System.out.println("[2] Internet Banking");
        System.out.println("[0] Voltar");
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

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void exibirErro(String erro){
        System.out.println(TextColor.RED_BOLD + erro + TextColor.ANSI_RESET);
    }
}
