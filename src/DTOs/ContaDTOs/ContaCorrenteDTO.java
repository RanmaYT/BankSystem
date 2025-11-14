package DTOs.ContaDTOs;

import Model.ContaCorrente;

public class ContaCorrenteDTO extends ContaDTO{
    private double chequeEspecial;

    public ContaCorrenteDTO(ContaCorrente contaCorrente){
        super(contaCorrente);
        this.chequeEspecial = contaCorrente.getChequeEspecial();
    }

    @Override
    public String toString(){
        String textoPai = super.toString();
        String textoFormatado = String.format("Cheque especial: %.2f", chequeEspecial);

        return textoPai + textoFormatado;
    }
}
