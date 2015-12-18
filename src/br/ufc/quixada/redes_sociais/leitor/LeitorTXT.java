package br.ufc.quixada.redes_sociais.leitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class LeitorTXT {
	private Path caminho;
	private Charset utf8 = StandardCharsets.UTF_8;

	public LeitorTXT(String caminho) {
		this.caminho = Paths.get(caminho);
	}
	public HashMap<Integer, String> lerPalavrasArquivo(){
		HashMap<Integer, String> pares = new HashMap<Integer, String>();
		try (BufferedReader leitor = Files.newBufferedReader(caminho, utf8)) {
			String teste = null;
			int i = 0;
			while ((teste = leitor.readLine()) != null) {
				String[] linha = teste.split(";");
				pares.put(i++, linha[0]);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return pares;
	}
}
