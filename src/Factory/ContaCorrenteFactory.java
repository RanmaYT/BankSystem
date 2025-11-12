package Factory;

import Model.*;
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
        return new ContaCorrente(0, cliente.getEmail(), new ExtratoBancario(new ArrayList<>()), chequeEspecial);
    }
}
