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
	public HashMap<String, Integer > lerPalavrasArquivo(){
		HashMap<String, Integer > pares = new HashMap<String, Integer >();
		try (BufferedReader leitor = Files.newBufferedReader(caminho, utf8)) {
			String teste = null;
			while ((teste = leitor.readLine()) != null) {
				String[] linha = teste.split(";");
				Integer temp = Integer.parseInt(linha[1]);
				pares.put(linha[0], temp);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return pares;
	}
}
