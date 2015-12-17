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

public class AnalisePalavra {
	
	public HashMap<String, Integer> analisaPalavras(HashMap<String, String> tweets) throws RowsExceededException, WriteException{
		return citacoesPalavras(tweets);
	}

	private HashMap<String, Integer> citacoesPalavras(HashMap<String, String> tweets) throws RowsExceededException,
			WriteException {
		Collection<String> conjuntoPalavras = tweets.values();
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
		//printMencoes(palavrasMencoes);
		return palavrasMencoes;
	}

	private void printMencoes(HashMap<String, Integer> mencoes)
			throws RowsExceededException, WriteException {

		File novaPlanilha = new File("Assets/Palavra/tweetsMencaoPalavra.xls");
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
			e.printStackTrace();
		}

	}

}
