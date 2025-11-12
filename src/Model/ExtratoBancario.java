package Model;

import SingletonRepositories.ExtratoRepository;
import SingletonRepositories.IStorable;

import java.util.List;

public class ExtratoBancario implements IStorable {
    private List<OperacaoExtratavel> operacoesRealizadas;
    private String emailTitular;

    public ExtratoBancario(List<OperacaoExtratavel> tipoDeLista, String emailTitular) {
        operacoesRealizadas = tipoDeLista;
        this.emailTitular = emailTitular;
    }

    public void adicionarOperacao(OperacaoExtratavel operacao){
        operacoesRealizadas.add(operacao);
        // gambiarra: classe de dados fazendo operaçoes de negocios
        ExtratoRepository.getInstance().atualizarLinha(emailTitular, converterParaStringArmazenavel());
    }

    public List<OperacaoExtratavel> getOperacoesRealizadas(){
        return operacoesRealizadas;
    }

    public String getEmailTitular() {
        return emailTitular;
    }

    @Override
    public String converterParaStringArmazenavel() {
        String textoFormatado = String.format("{emailTitular=%s;[", emailTitular);

        for(OperacaoExtratavel operacao : operacoesRealizadas) {
            textoFormatado += (operacao.converterParaStringArmazenavel() + "/");
        }

        textoFormatado += "]}";

        return textoFormatado;
    }

    @Override
    public int getId() {
        return 0;
    }
}
