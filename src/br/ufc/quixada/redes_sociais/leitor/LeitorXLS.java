package br.ufc.quixada.redes_sociais.leitor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import br.ufc.quixada.redes_sociais.analise.AnaliseHashtag;
import br.ufc.quixada.redes_sociais.analise.AnaliseHashtagPalavra;
import br.ufc.quixada.redes_sociais.analise.AnalisePalavra;
import br.ufc.quixada.redes_sociais.analise.EscreveArquivo;

public class LeitorXLS {
	private File file;
	private HashMap<String, String> tweets;
	private ArrayList<String> palavrasInuteis;
	private AnaliseHashtag analiseHashtag;
	private AnalisePalavra analisePalavra;
	private EscreveArquivo escreve;

	public LeitorXLS() {
		this.tweets = new HashMap<String, String>();
		this.palavrasInuteis = new ArrayList<String>();
		this.analiseHashtag = new AnaliseHashtag();
		this.analisePalavra = new AnalisePalavra();
		this.escreve = new EscreveArquivo();
		carregaPalavrasInuteis();
	}

	public void carregaArquivo(String filePath) {
		this.file = new File(filePath);
	}

	public void processaArquivo() throws RowsExceededException, WriteException {
		Workbook workbook;
		try {
			WorkbookSettings configuracao = new WorkbookSettings();
			configuracao.setEncoding("Cp1252");
			workbook = Workbook.getWorkbook(file, configuracao);

			Sheet sheet = workbook.getSheet(0);
			int linhas = sheet.getRows();

			for (int i = 1; i < linhas; i++) {
				if (!tweets.containsKey(sheet.getCell(1, i).getContents())) {
					tweets.put(
							sheet.getCell(1, i).getContents(),
							removePalavras(" "
									+ removeAcentos(sheet.getCell(12, i)
											.getContents()))
									+ " ");

				}

			}

			escreve.gravarTweet(analisePalavra.analisaPalavras(tweets),
					"Assets/Palavra/nuvemDePalavra.txt");
			escreve.gravarTweet(analiseHashtag.analisarHashtags(tweets),
					"Assets/Hashtag/nuvemDeHashtag.txt");
			AnaliseHashtagPalavra analiseHashtagPalavra = new AnaliseHashtagPalavra();
			analiseHashtagPalavra.analiseMencoesPalavraHashtag(tweets);
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String removePalavras(String tweet) {
		for (String string : palavrasInuteis) {
			tweet = tweet
					.replaceAll(" " + string + " ", " ")
					.replaceAll(
							"([k|K]+[K|k]*[k|K]+)|([z|Z]+[Z|z]*[z|Z]+)"
									+ "|(http|https):\\/\\/([\\w-]+\\.)+[\\w-]+(\\/[\\w- ./?%&=]*)"
									+ "|(\\S*@*)([\\w-]+\\.)+[\\w-]+(\\/[\\w- ./?%&=]*)?|(\\s\\d+\\s)|(\\s+\\W+\\s+)"
									+ "|(\\v+)|(\\s\\W+\\s)|(\\s\\w\\s)|(@\\w*)|[^\\w#\\s]",
							"");
		}

		return tweet;
	}

	private void carregaPalavrasInuteis() {
		try {
			String content = new String(Files.readAllBytes(Paths
					.get("Assets/stopwords.txt")), StandardCharsets.ISO_8859_1);

			String[] palavrasFile = content.split(",");

			for (String string : palavrasFile) {
				palavrasInuteis.add(string.replaceAll(" ", ""));
			}
			for (String string : palavrasInuteis) {
				System.err.println(string);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private String removeAcentos(String string) {
		if (string != null) {
			string = Normalizer.normalize(string, Normalizer.Form.NFD);
			string = string.replaceAll("[^\\p{ASCII}]", "");
		}
		return string;
	}

}
