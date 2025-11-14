package SingletonRepositories;

import Factory.ContaFactory.ContaEstadoSimpleFactory;
import Model.*;

public class ContaRepository extends BaseRepositoryAbstract<ContaAbstrata> {
    private static ContaRepository instance;
    private static final String fileName = "ContasCadastradas.txt";

    private ContaRepository() {}

    public static ContaRepository getInstance(){
        if(instance == null) {
            instance = new ContaRepository();
        }

        return instance;
    }

    public ContaAbstrata pegarPorTitular(String emailTitular){
        String linhaConta = buscarLinhaComItem(emailTitular);

        return carregarEntidade(linhaConta);
    }

    @Override
    public ContaAbstrata carregarEntidade(String json) {
        ContaAbstrata conta = null;

        if (json.contains("\"tipoConta\":\"Corrente\"")) {
            conta = gson.fromJson(json, ContaCorrente.class);
        } else if (json.contains("\"tipoConta\":\"Poupança\"")) {
            conta = gson.fromJson(json, ContaPoupanca.class);
        }

        // Criação de uma factory para gambiarra
        ContaEstadoSimpleFactory contaEstadoSimpleFactory = new ContaEstadoSimpleFactory();
        conta.mudarEstado(contaEstadoSimpleFactory.criarEstadoPorNome(conta.getNomeEstado()));

        return conta;
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
