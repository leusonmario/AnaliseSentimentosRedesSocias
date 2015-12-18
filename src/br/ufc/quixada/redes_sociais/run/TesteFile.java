package br.ufc.quixada.redes_sociais.run;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import br.ufc.quixada.redes_sociais.analise.dl.ArquivoDLService;
import br.ufc.quixada.redes_sociais.leitor.LeitorXLS;

public class TesteFile {

	public static void main(String[] args) throws RowsExceededException, WriteException {
		//XLS
		/*LeitorXLS leitor = new LeitorXLS();
		leitor.carregaArquivo("Assets/tweets-nao-golpe-vem-pra-rua-3.xls");
		leitor.processaArquivo();
		*/
		
		//HASHs
	/*	ArquivoDLService arquivoHashDLServiceNVTG = new ArquivoDLService("Assets/Hashtag/NaoVaiTerGolpe/nuvemHashtagNaoVaiTerGolpe.txt",
				"Assets/DL/hashtagNaoVaiTerGolpe.dl","#NaoVaiTerGolpe");
		arquivoHashDLServiceNVTG.processamento();
		ArquivoDLService arquivoHashDLServiceVPR = new ArquivoDLService("Assets/Hashtag/VemPraRua/nuvemHashtagVemPraRua.txt", 
				"Assets/DL/hashtagVemPraRua.dl","#VemPraRua");
		arquivoHashDLServiceVPR.processamento();*/
		//PALAVRAS
		ArquivoDLService arquivoPalavraDLServiceVPR = new ArquivoDLService("Assets/Palavra/VemPraRua/nuvemPalavraVemPraRua.txt", 
				"Assets/DL/palavrasVemPraRua.dl","#VemPraRua");
		arquivoPalavraDLServiceVPR.processamentoPalavras();
		ArquivoDLService arquivoPalavraDLServiceNVTG = new ArquivoDLService("Assets/Palavra/NaoVaiTerGolpe/nuvemPalavraNaoVaiTerGolpe.txt", 
				"Assets/DL/palavrasNaoVaiTerGolpe.dl","#NaoVaiTerGolpe");
		arquivoPalavraDLServiceNVTG.processamentoPalavras();
	}

}
