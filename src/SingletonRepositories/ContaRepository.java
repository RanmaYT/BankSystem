package SingletonRepositories;

import Model.Conta;
import Model.ContaCorrente;
import Model.ExtratoBancario;
import State.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ContaRepository extends BaseRepository<Conta>{
    private static ContaRepository instance;
    private static final String fileName = "ContasCadastradas.txt";

    private ContaRepository() {}

    public static ContaRepository getInstance(){
        if(instance == null) {
            instance = new ContaRepository();
        }

        return instance;
    }

    public Conta pegarPorTitular(String emailTitular){
        String linhaConta = buscarLinhaComItem(emailTitular);

        Conta conta = carregarEntidade(linhaConta);

        return conta;
    }

    @Override
    public Conta carregarEntidade(String textoArmazenado) {
        // Desconversor básico e acoplado, gambiarra!
        textoArmazenado = textoArmazenado.substring(1, textoArmazenado.length() - 1);
        String[] partesPareadas = textoArmazenado.split(";");
        HashMap<String, String> map = new HashMap<>();

        for(String partePareada : partesPareadas) {
            String[] par = partePareada.split("=");
            map.put(par[0], par[1]);
        }

        double saldo = Double.parseDouble(map.get("saldo"));
        double chequeEspecial = Double.parseDouble(map.get("chequeEspecial"));
        String email = map.get("emailTitular");
        ExtratoBancario extratoBancario = new ExtratoBancario(new ArrayList<>(), email);

        // Escolher o estado da conta, gambiarra: pode ser resolvida com uma factory talvez?
        IContaState state = null;

        switch (map.get("estadoConta")) {
            case "POSITIVA":
                state = new ContaPositiva();
                break;
            case "NEGATIVADA":
                state = new ContaNegativada();
                break;
            case "BLOQUEADA":
                state = new ContaBloqueada();
                break;
            case "ENCERRADA":
                state = new ContaEncerrada();
                break;
        }

        Conta conta = new ContaCorrente(saldo, email, state, chequeEspecial);

        return conta;
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
