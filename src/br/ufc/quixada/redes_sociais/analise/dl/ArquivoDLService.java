package br.ufc.quixada.redes_sociais.analise.dl;

import java.util.HashMap;

import br.ufc.quixada.redes_sociais.analise.EscreveArquivo;
import br.ufc.quixada.redes_sociais.leitor.LeitorTXT;

public class ArquivoDLService {
	private LeitorTXT leitoTxt;
	private EscreveArquivo escreveArquivo;
	private String caminhoEscrita;
	private HashMap<String, Integer> associacoesH;
	private HashMap<String, Integer> associacoesP;
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
	
	public void processamentoPalavras(){
		associacoesP = leitoTxt.lerPalavrasArquivo();
		escreveArquivoDlPalavra();
	}
	
	private void lerArquivoDl(){
		associacoesH = leitoTxt.lerPalavrasArquivo();
	}
	
	private void escreveArquivoDl(){
		escreveArquivo.gravaDlHash(associacoesH, caminhoEscrita, hashtag);
	}
	
	private void escreveArquivoDlPalavra(){
		escreveArquivo.gravaDlHash(associacoesP, caminhoEscrita, hashtag);
	}
	
}
