package SingletonRepositories;

import Factory.ContaFactory.ContaEstadoSimpleFactory;
import Model.ContaAbstrata;
import Model.ContaCorrente;

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
    public ContaAbstrata carregarEntidade(String textoArmazenado) {
        // GAMBIARRA: ACOPLAMENTO ALTO COM CONTA CORRENTE.
        ContaAbstrata conta = gson.fromJson(textoArmazenado, ContaCorrente.class);

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
