package SingletonRepositories;

import java.io.*;

public abstract class BaseRepository<T extends IStorable>{
    public void salvar(T entidade) {
        try(BufferedWriter escritor = new BufferedWriter(new FileWriter(getFileName(), true))) {
            String textoArmazenavel = entidade.converterParaStringArmazenavel();

            escritor.write(textoArmazenavel);
            escritor.newLine();
        }
        catch(IOException e) {
            System.out.println("Falha ao salvar no arquivo: " + getFileName());
        }
    }

    public String buscarLinhaComItem(String identificadorLinha) {
        try(BufferedReader leitor = new BufferedReader(new FileReader(getFileName()))) {
            String linhaAtual;
            while((linhaAtual = leitor.readLine()) != null){
                if(linhaAtual.contains(identificadorLinha)) {
                    return linhaAtual;
                }
            }
        }
        catch(IOException e) {
            System.out.println("Falha ao ler o arquivo");
            return null;
        }

        return null;
    }

    public void atualizarLinha(String identificadorLinhaAntiga, String novaLinha) {
        File arquivoOriginal = new File(getFileName());
        File arquivoTemporario = new File("tempFile.txt");
        String linhaAntiga = buscarLinhaComItem(identificadorLinhaAntiga);

        try(BufferedReader leitor = new BufferedReader(new FileReader(getFileName()))) {
            String linhaLida;
            BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoTemporario, true));

            // Verifica cada linha, caso seja uma linha diferente da do item que está sendo atualizado, só reescreve ela
            // no arquivo temporário
            while((linhaLida = leitor.readLine()) != null) {
                if(!linhaLida.equals(linhaAntiga)) {
                    escritor.write(linhaLida);
                }
                else {
                    escritor.write(novaLinha);
                }

                escritor.newLine();
            }
            escritor.close();
            leitor.close();

            // Apaga o arquivo antigo
            arquivoOriginal.delete();
            arquivoTemporario.renameTo(arquivoOriginal);
        }
        catch(IOException e) {
            System.out.println("Erro ao atualizar arquivo");
        }
    }

    public void deletar(T entidade) {
        String linhaParaDeletar = entidade.converterParaStringArmazenavel();

        File arquivoOriginal = new File(getFileName());
        File arquivoTemporario = new File("tempFile.txt");

        try(BufferedReader leitor = new BufferedReader(new FileReader(getFileName()))) {
            String linhaLida;
            BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoTemporario, true));

            // Verifica cada linha, caso seja uma linha diferente da que será deletada, escreve a linha
            while((linhaLida = leitor.readLine()) != null) {
                if(!linhaLida.equals(linhaParaDeletar)) {
                    escritor.write(linhaLida);
                }
                escritor.newLine();
            }
            escritor.close();
            leitor.close();

            // Apaga o arquivo antigo
            arquivoOriginal.delete();
            arquivoTemporario.renameTo(arquivoOriginal);

            System.out.println("Linha apagada com sucesso:" + getFileName());
        }
        catch(IOException e) {
            System.out.println("Erro ao atualizar arquivo");
        }
    }


    public abstract T carregarEntidade(String textoArmazenado);
    public abstract String getFileName();
}
