package br.ufc.quixada.redes_sociais.leitor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class LeitorXLS {
	private File file;
	private HashMap<String, String> frases;
	private ArrayList<String> palavrasInuteis;

	public LeitorXLS() {
		frases = new HashMap<String, String>();
		palavrasInuteis = new ArrayList<String>();
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
				if (frases.containsKey(sheet.getCell(3, i).getContents()) == false) {
					frases.put(
							sheet.getCell(3, i).getContents(),
							removePalavras(" "
									+ removeAcentos(sheet.getCell(12, i)
											.getContents()))
									+ " ");

				}

			}
			printFrases();
			citacoesPalavras();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String removePalavras(String umaFrase) {
		for (String string : palavrasInuteis) {
			umaFrase = umaFrase
					.replaceAll(" " + string + " ", " ")
					.replaceAll(
							"[\\[\\]|.,+\\-\\~?!:;\"^'\\/*()\n]|\\S*@\\S*|http\\S*|(kk+)|(KK+)|(kk+)K",
							"");
		}

		return umaFrase;
	}

	private void carregaPalavrasInuteis() {
		try {
			String content = new String(
					Files.readAllBytes(Paths
							.get("C:\\Users\\Leuson\\Documents\\GitHub\\redessociais_2015.2\\Crawler\\stopwords.txt")),
					StandardCharsets.ISO_8859_1);

			String[] palavrasFile = content.split(",");

			for (String string : palavrasFile) {
				palavrasInuteis.add(string.replaceAll(" ", ""));
			}
			for (String string : palavrasInuteis) {
				System.err.println(string);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void printFrases() {
		Set<String> chaves = frases.keySet();
		for (String string : chaves) {
			System.out.println(frases.get(string));
		}
	}

	private String removeAcentos(String string) {
		if (string != null) {
			string = Normalizer.normalize(string, Normalizer.Form.NFD);
			string = string.replaceAll("[^\\p{ASCII}]", "");
		}
		return string;
	}

	private void citacoesPalavras() throws RowsExceededException,
			WriteException {
		Collection<String> conjuntoPalavras = frases.values();
		HashMap<String, Integer> palavrasMencoes = new HashMap<String, Integer>();
		int i = 0;
		String novaPalavra = "";
		for (String string : conjuntoPalavras) {
			i = 0;
			novaPalavra = "";
			while (i < string.length()) {
				while (string.charAt(i) != ' ') {
					novaPalavra += string.charAt(i);
					i++;
				}
				// novaPalavra += novaPalavra.replaceAll(" ", "");
				if (novaPalavra.contains(" ") == false
						&& novaPalavra.length() > 0) {
					if (palavrasMencoes.get(novaPalavra) == null) {
						palavrasMencoes.put(novaPalavra, 1);
					} else {
						palavrasMencoes.put(novaPalavra,
								palavrasMencoes.get(novaPalavra) + 1);
					}
				}
				i++;
				novaPalavra = "";
			}
		}
		printMencoes(palavrasMencoes);
	}

	private void printMencoes(HashMap<String, Integer> mencoes)
			throws RowsExceededException, WriteException {
		File novaPlanilha = new File(
				"C:\\Users\\Leuson\\Documents\\GitHub\\redessociais_2015.2\\Crawler\\tweetsTeste.xls");
		WritableWorkbook writableWorkbook;
		try {
			writableWorkbook = Workbook.createWorkbook(novaPlanilha);
			WritableSheet writableSheet = writableWorkbook.createSheet(
					"Sheet1", 0);
			Label labelPalavra = new Label(0, 0, "Palavra");
			Label labelValor = new Label(1, 0, "Valor");
			writableSheet.addCell(labelPalavra);
			writableSheet.addCell(labelValor);
			int i = 1;
			Set<String> chaves = mencoes.keySet();
			for (String string : chaves) {
				System.out.println("Chave: " + string + "-Valor: "
						+ mencoes.get(string));
				if (string.contains(" ") == false && string.length() > 0) {
					writableSheet.addCell(new Label(0, i, string));
					writableSheet.addCell(new Label(1, i, mencoes.get(string)
							+ ""));
					i++;
				}
			}
			writableWorkbook.write();
			writableWorkbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
