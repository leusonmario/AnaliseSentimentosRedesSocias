package br.ufc.quixada.redes_sociais.run;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import br.ufc.quixada.redes_sociais.leitor.LeitorXLS;

public class TesteFile {

	public static void main(String[] args) throws RowsExceededException, WriteException {
		LeitorXLS leitor = new LeitorXLS();
		leitor.carregaArquivo("Assets/tweets-nao-golpe-vem-pra-rua-2.xls");
		leitor.processaArquivo();
	}

}
