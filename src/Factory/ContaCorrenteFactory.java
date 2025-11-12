package Factory;

import Model.*;
import State.ContaPositiva;
import State.IContaState;
import Util.FinancaUtil;

import java.util.ArrayList;

public class ContaCorrenteFactory implements ContaFactory{

    @Override
    public Conta criarConta(Usuario usuario) {
        if(!(usuario.getCargo() == NivelUsuario.Cliente)) {
            System.out.println("Apenas clientes podem criar contas corrente");
            return null;
        }

        Cliente cliente = (Cliente) usuario;
        double chequeEspecial = FinancaUtil.calcularChequeEspecial(cliente.getRendaMensal());
        String email = cliente.getEmail();
        ExtratoBancario extrato = new ExtratoBancario(new ArrayList<>(), email);
        IContaState state = new ContaPositiva();
        return new ContaCorrente(0, email, state, chequeEspecial);
    }
}
