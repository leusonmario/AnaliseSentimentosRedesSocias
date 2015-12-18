package br.ufc.quixada.redes_sociais.analise.dl;

import java.util.HashMap;

import br.ufc.quixada.redes_sociais.analise.EscreveArquivo;
import br.ufc.quixada.redes_sociais.leitor.LeitorTXT;

public class ArquivoDLService {
	private LeitorTXT leitoTxt;
	private EscreveArquivo escreveArquivo;
	private String caminhoEscrita;
	private HashMap<Integer, String> associacoes;
	private String hashtag;
	
	public ArquivoDLService(String pathEntrada, String pathSaida, String hash) {
		leitoTxt = new LeitorTXT(pathEntrada);
		escreveArquivo = new EscreveArquivo();
		caminhoEscrita = pathSaida;
		hashtag = hash;
	}
	
	public void processamento(){
		lerArquivoDl();
		escreveArquivoDl();
	}
	
	private void lerArquivoDl(){
		associacoes = leitoTxt.lerPalavrasArquivo();
	}
	
	private void escreveArquivoDl(){
		escreveArquivo.gravaDL(associacoes, caminhoEscrita, hashtag);
	}
	
}
