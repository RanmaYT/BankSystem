package SingletonRepositories;

import Model.ExtratoBancario;
import Model.OperacaoExtratavel;

import java.util.ArrayList;

public class ExtratoRepository extends BaseRepository<ExtratoBancario> {
    private static ExtratoRepository instance;
    private static final String fileName = "Extratos.txt";

    private ExtratoRepository() {};

    public static ExtratoRepository getInstance() {
        if(instance == null) {
            instance = new ExtratoRepository();
        }

        return instance;
    }

    public ExtratoBancario pegarPorTitular(String emailTitular) {
        String linhaSalva = buscarLinhaComItem(emailTitular);

        ExtratoBancario extrato = carregarEntidade(linhaSalva);

        return extrato;
    }

    @Override
    public ExtratoBancario carregarEntidade(String textoArmazenado) {
        // Gambiarra: procurar uma forma melhor de estruturar e guardar os dados

        // Remove os {}
        textoArmazenado = textoArmazenado.substring(1, textoArmazenado.length() - 1);

        String[] partes = textoArmazenado.split(";");
        String[] emailPartes = partes[0].split("=");
        String email = emailPartes[1];

        // remove os [] da lista
        partes[1] = partes[1].substring(1, partes[1].length() - 1);


        ArrayList<OperacaoExtratavel> operacoesRealizadas = new ArrayList<>();

        if(!partes[1].isBlank()) {
            // separa as entidades
            String[] operacoesExtrataveis = partes[1].split("/");

            for(String operacaoExtratavel : operacoesExtrataveis) {
                // Remove os {}
                operacaoExtratavel = operacaoExtratavel.substring(1, operacaoExtratavel.length() - 1);

                // Separa em pares de campo-valor
                String[] camposOperacao = operacaoExtratavel.split(",");

                // Separa o campo em nome-valor
                String[] nomeOperacao = camposOperacao[0].split("=");
                String[] valorOperacao = camposOperacao[1].split("=");

                OperacaoExtratavel operacaoExtratavelObjeto = new OperacaoExtratavel(nomeOperacao[1], Double.parseDouble(valorOperacao[1]));
                operacoesRealizadas.add(operacaoExtratavelObjeto);
            }

        }


        return new ExtratoBancario(operacoesRealizadas, email);
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
