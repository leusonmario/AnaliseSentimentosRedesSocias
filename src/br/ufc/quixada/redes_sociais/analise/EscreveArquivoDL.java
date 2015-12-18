package br.ufc.quixada.redes_sociais.analise;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class EscreveArquivoDL {
	private Path caminho; 
	private Charset utf8 = StandardCharsets.UTF_8;
	
	public void gravarTweet(HashMap<String, Integer> mencoesTweet, String path){
		caminho = Paths.get(path);
		try(BufferedWriter escritor = Files.newBufferedWriter(caminho,utf8)){
			for (String keyMencao : mencoesTweet.keySet()) {
				escritor.write(keyMencao + ";" + mencoesTweet.get(keyMencao) +"\n");	
			}			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
	}
}
