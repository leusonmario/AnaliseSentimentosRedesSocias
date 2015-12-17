package br.ufc.quixada.redes_sociais.analise;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class AnaliseHashtagPalavra {
	private HashMap<String, Integer> mencoesPalavraVPR;
	private HashMap<String, Integer> mencoesPalavraNVTG;

	public AnaliseHashtagPalavra() {
		this.mencoesPalavraVPR = new HashMap<String, Integer>();
		this.mencoesPalavraNVTG = new HashMap<String, Integer>();
	}

	public void analiseMencoesPalavraHashtag(HashMap<String, String> tweets) {
		Collection<String> idTweets = tweets.keySet();

		for (String string : idTweets) {
			if (tweets.get(string).contains("#vemprarua")) {
				analisaPalavraTweet(tweets.get(string), "#vemprarua",
						this.mencoesPalavraVPR);
			}
			if (tweets.get(string).contains("#naovaitergolpe")) {
				analisaPalavraTweet(tweets.get(string), "#naovaitergolpe",
						this.mencoesPalavraNVTG);
			}
		}
		printMencoes(mencoesPalavraVPR);
		printMencoes(mencoesPalavraNVTG);
	}

	private void analisaPalavraTweet(String tweet, String hashtag,
			HashMap<String, Integer> mencoes) {
		int i = 0;
		String novaPalavra = "";
		tweet = tweet.replaceAll(hashtag, "");
		while (i < tweet.length()) {
			while (tweet.charAt(i) != ' ') {
				novaPalavra += tweet.charAt(i);
				i++;
			}
			if (novaPalavra.contains(" ") == false && novaPalavra.length() > 0) {
				if (mencoes.get(novaPalavra) == null) {
					mencoes.put(novaPalavra, 1);
				} else {
					mencoes.put(novaPalavra, mencoes.get(novaPalavra) + 1);
				}
			}
			novaPalavra = "";
			i++;
		}

	}

	private void printMencoes(HashMap<String, Integer> mencoes) {
		Collection<String> palavra = mencoes.keySet();
		System.out.println("*******************************");
		for (String string : palavra) {
			System.out.println(string + ";" + mencoes.get(string));
		}
	}

}
