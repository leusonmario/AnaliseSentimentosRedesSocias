package br.ufc.quixada.redes_sociais.run;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import br.ufc.quixada.redes_sociais.leitor.LeitorXLS;

public class TesteFile {

	public static void main(String[] args) throws RowsExceededException, WriteException {
		LeitorXLS leitor = new LeitorXLS();
		leitor.carregaArquivo("C:\\Users\\Leuson\\Documents\\GitHub\\redessociais_2015.2\\Crawler\\tweets.xls");
		leitor.processaArquivo();
	}

}