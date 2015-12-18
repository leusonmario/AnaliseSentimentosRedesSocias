package br.ufc.quixada.redes_sociais.run;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import br.ufc.quixada.redes_sociais.analise.dl.ArquivoDLService;
import br.ufc.quixada.redes_sociais.leitor.LeitorXLS;

public class TesteFile {

	public static void main(String[] args) throws RowsExceededException, WriteException {
		LeitorXLS leitor = new LeitorXLS();
		leitor.carregaArquivo("Assets/tweets-nao-golpe-vem-pra-rua-3.xls");
		leitor.processaArquivo();
		ArquivoDLService arquivoDLService = new ArquivoDLService("Assets/Hashtag/NaoVaiTerGolpe/nuvemHashtagNaoVaiTerGolpe.txt",
				"Assets/DL/hashtagNaoVaiTerGolpe.dl","#NaoVaiTerGolpe");
		arquivoDLService.processamento();
		
	}

}
