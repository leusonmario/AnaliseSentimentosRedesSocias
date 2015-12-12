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

	public void processaArquivo() {
		Workbook workbook;
		try {
			WorkbookSettings configuracao = new WorkbookSettings();
			configuracao.setEncoding("Cp1252");
			workbook = Workbook.getWorkbook(file, configuracao);

			Sheet sheet = workbook.getSheet(0);
			int linhas = sheet.getRows();

			for (int i = 0; i < linhas; i++) {
				if (!frases.containsKey(sheet.getCell(3, i).getContents())) {
					frases.put(sheet.getCell(3, i).getContents(), removeAcentos(" "+removePalavras(sheet.getCell(12, i)
									.getContents())+" "));

				}

			}
			printFrases();
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
			umaFrase = umaFrase.replaceAll(" " + string + " ", " ").replaceAll(
					"[\\[\\]|.,+\\-\\~?!:;\"^'\\/*()]|\\S*@\\S*|http\\S*|(kk+)|(KK+)|(kk+)K", "");
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
}
