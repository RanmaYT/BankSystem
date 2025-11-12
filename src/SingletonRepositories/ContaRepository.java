package SingletonRepositories;

import Model.Conta;
import Model.ContaCorrente;
import Model.ExtratoBancario;

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
        textoArmazenado = textoArmazenado.substring(1, textoArmazenado.length() - 1);
        String[] partesPareadas = textoArmazenado.split(";");
        HashMap<String, String> map = new HashMap<>();

        for(String partePareada : partesPareadas) {
            String[] par = partePareada.split("=");
            map.put(par[0], par[1]);
        }

        double saldo = Double.parseDouble(map.get("saldo"));
        double chequeEspecial = Double.parseDouble(map.get("chequeEspecial"));
        Conta conta = new ContaCorrente(saldo, map.get("emailTitular"), new ExtratoBancario(new ArrayList<>()), chequeEspecial);

        return conta;
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
