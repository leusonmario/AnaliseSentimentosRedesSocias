package br.ufc.quixada.redes_sociais.analise;

import java.util.Collection;
import java.util.HashMap;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class AnaliseHashtag {

	public AnaliseHashtag() {
	}

	public HashMap<String, Integer> analisarHashtags(
			HashMap<String, String> tweets) throws RowsExceededException,
			WriteException {
		return mencoesHashtags(tweets);
	}

	private HashMap<String, Integer> mencoesHashtags(
			HashMap<String, String> tweets) throws RowsExceededException,
			WriteException {
		Collection<String> conjuntoPalavras = tweets.values();
		HashMap<String, Integer> palavrasMencoes = new HashMap<String, Integer>();
		for (String tweet : conjuntoPalavras) {
			String[] palavras = tweet.split("(\\s|\\n|(?=(#(\\w)+)))");
			for (String conteudo : palavras) {
				if (conteudo.length() > 1 && conteudo.charAt(0) == '#') {
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
