package br.ufc.quixada.redes_sociais.leitor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class LeitorXLS {
	private File file;
	private HashMap<String, String> tweets;
	private ArrayList<String> palavrasInuteis;
	private AnaliseHashtag analiseHashtag;
	private AnalisePalavra analisePalavra;

	public LeitorXLS() {
		this.tweets = new HashMap<String, String>();
		this.palavrasInuteis = new ArrayList<String>();
		this.analiseHashtag = new AnaliseHashtag();
		this.analisePalavra = new AnalisePalavra();
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

			for (int i = 0; i < linhas; i++) {
				if (tweets.containsKey(sheet.getCell(3, i).getContents()) == false) {
					tweets.put(
							sheet.getCell(3, i).getContents(),
							removePalavras(" "
									+ removeAcentos(sheet.getCell(12, i)
											.getContents()))
									+ " ");

				}

			}
			printFrases();
			analisePalavra.analisaPalavras(tweets);
			analiseHashtag.analisarHashtags(tweets);
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String removePalavras(String umaFrase) {
		for (String string : palavrasInuteis) {
			umaFrase = umaFrase
					.replaceAll(" " + string + " ", " ")
					.replaceAll(
							"([k|K]+[K|k]*[k|K]+)|([z|Z]+[Z|z]*[z|Z]+)"
							+ "|(http|https):\\/\\/([\\w-]+\\.)+[\\w-]+(\\/[\\w- ./?%&=]*)"
							+ "|(\\S*@*)([\\w-]+\\.)+[\\w-]+(\\/[\\w- ./?%&=]*)?|(\\s\\d+\\s)|(\\s+\\W+\\s+)"
							+ "|(\\v+)|(\\s\\W+\\s)",
							"");
		}

		return umaFrase;
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

	private void printFrases() {
		Set<String> chaves = tweets.keySet();
		for (String string : chaves) {
			System.out.println(tweets.get(string));
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