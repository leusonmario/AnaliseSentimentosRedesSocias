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
	private Path caminho = Paths.get("contas.txt");
	private Charset utf8 = StandardCharsets.UTF_8;

	public HashMap<String, String> lerPalavrasArquivo(){
		HashMap<String, String> pares = new HashMap<String, String>();
		try (BufferedReader leitor = Files.newBufferedReader(caminho, utf8)) {
			String teste = null;
			while ((teste = leitor.readLine()) != null) {
				String[] linha = teste.split(":");
			}
		}catch (IOException e) {
			e.printStackTrace();
		}

		return pares;
	}
}
