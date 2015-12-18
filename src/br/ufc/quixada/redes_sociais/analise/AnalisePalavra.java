package br.ufc.quixada.redes_sociais.analise;

import java.util.Collection;
import java.util.HashMap;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class AnalisePalavra {
	
	public HashMap<String, Integer> analisaPalavras(HashMap<String, String> tweets) throws RowsExceededException, WriteException{
		return citacoesPalavras(tweets);
	}

	private HashMap<String, Integer> citacoesPalavras(HashMap<String, String> tweets) throws RowsExceededException,
			WriteException {
		Collection<String> conjuntoPalavras = tweets.values();
		HashMap<String, Integer> palavrasMencoes = new HashMap<String, Integer>();
		for (String string : conjuntoPalavras) {
			String[] tweet = string.split("(\\s|\\n|(?=#))");
			for (String conteudo : tweet) {
				if (conteudo.length() > 0 && conteudo.charAt(0) != '#') {
					if (palavrasMencoes.get(conteudo) == null) {
						palavrasMencoes.put(conteudo, 1);
					} else {
						palavrasMencoes.put(conteudo,
								palavrasMencoes.get(conteudo) + 1);
					}
				}
			}
		}
		return palavrasMencoes;
	}

}
