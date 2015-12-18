package br.ufc.quixada.redes_sociais.analise;

import java.util.Collection;
import java.util.HashMap;

public class AnaliseHashtagPalavra {
	private HashMap<String, Integer> mencaoHashtagVemPraRua;
	private HashMap<String, Integer> mencaoHashtagNaoVaiTerGolpe;
	private HashMap<String, Integer> mencaoPalavraVemPraRua;
	private HashMap<String, Integer> mencaoPalavraNaoVaiTerGolpe;

	public AnaliseHashtagPalavra() {
		this.mencaoHashtagVemPraRua = new HashMap<String, Integer>();
		this.mencaoHashtagNaoVaiTerGolpe = new HashMap<String, Integer>();
		this.mencaoPalavraVemPraRua = new HashMap<String, Integer>();
		this.mencaoPalavraNaoVaiTerGolpe = new HashMap<String, Integer>();
	}

	public void analiseMencoesPalavraHashtag(HashMap<String, String> tweets) {
		Collection<String> idTweets = tweets.keySet();

		for (String string : idTweets) {
			if (tweets.get(string).contains("#vemprarua")) {
				analisaHashtagTweet(tweets.get(string), "#vemprarua", this.mencaoHashtagVemPraRua);
				analisaPalavraTweet(tweets.get(string), this.mencaoPalavraVemPraRua);
			}
			if (tweets.get(string).contains("#naovaitergolpe")) {
				analisaHashtagTweet(tweets.get(string), "#naovaitergolpe", this.mencaoHashtagNaoVaiTerGolpe);
				analisaPalavraTweet(tweets.get(string), this.mencaoPalavraNaoVaiTerGolpe);
			}
		}
		EscreveArquivo escreve = new EscreveArquivo();
		escreve.gravarTweet(mencaoHashtagVemPraRua,
				"Assets/Hashtag/VemPraRua/nuvemHashtagVemPraRua.txt");
		escreve.gravarTweet(mencaoHashtagNaoVaiTerGolpe,
				"Assets/Hashtag/NaoVaiTerGolpe/nuvemHashtagNaoVaiTerGolpe.txt");
		escreve.gravarTweet(mencaoPalavraVemPraRua,
				"Assets/Hashtag/VemPraRua/nuvemPalavraVemPraRua.txt");
		escreve.gravarTweet(mencaoPalavraNaoVaiTerGolpe,
				"Assets/Hashtag/NaoVaiTerGolpe/nuvemPalavraNaoVaiTerGolpe.txt");
	}

	private void analisaHashtagTweet(String tweet, String hashtag,
			HashMap<String, Integer> mencoes) {
		tweet = tweet.replaceAll(hashtag+"\\s+|\\n+", "");
		String[] mencoesTweet = tweet.split("(\\s|\\n|(?=(#(\\w)+)))");
		
		for (String mencaoTweet : mencoesTweet) {
			if (mencaoTweet.length() > 0 &&  mencaoTweet.charAt(0) == '#') {
				if (mencoes.get(mencaoTweet) == null) {
					mencoes.put(mencaoTweet, 1);
				} else {
					mencoes.put(mencaoTweet, mencoes.get(mencaoTweet) + 1);
				}
			}
		}
	}
	
	private void analisaPalavraTweet(String tweet,
			HashMap<String, Integer> mencoes) {
		String[] mencoesTweet = tweet.split("(\\s|\\n|(?=#))");
		
		for (String mencaoTweet : mencoesTweet) {
			if (mencaoTweet.length() > 0 &&  mencaoTweet.charAt(0) != '#') {
				if (mencoes.get(mencaoTweet) == null) {
					mencoes.put(mencaoTweet, 1);
				} else {
					mencoes.put(mencaoTweet, mencoes.get(mencaoTweet) + 1);
				}
			}
		}
	}

}
