package br.ufc.quixada.redes_sociais.analise;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class EscreveArquivo {
	private Path caminhoTXT;
	private Path caminhoDL;
	private Path caminhoDlW;
	private Charset utf8 = StandardCharsets.UTF_8;
	
	public void gravarTweet(HashMap<String, Integer> mencoesTweet, String path){
		caminhoTXT = Paths.get(path);
		try(BufferedWriter escritor = Files.newBufferedWriter(caminhoTXT,utf8)){
			for (String keyMencao : mencoesTweet.keySet()) {
				escritor.write(keyMencao + ";" + mencoesTweet.get(keyMencao) +"\n");	
			}			
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	public void gravaDlHash(HashMap<String,Integer> associacoes, String path, String hash) {
		caminhoDL = Paths.get(path);
		try(BufferedWriter escritor = Files.newBufferedWriter(caminhoDL,utf8)){
			escritor.write("Tamanho: "+associacoes.size());
			for (String index : associacoes.keySet()) {
				escritor.write(index+",");
			}
			escritor.write("\n");
			for (String index : associacoes.keySet()) {
				Integer cont = associacoes.get(index);
				while (cont > 0) {
					{
						escritor.write(hash + " " + index + " 1\n");
						cont--;
					}
				}
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
	
	public void gravaDlWord(HashMap<String,Integer> associacoes, String path, String hash) {
		caminhoDlW = Paths.get(path);
		try(BufferedWriter escritor = Files.newBufferedWriter(caminhoDlW,utf8)){
			escritor.write("Tamanho: "+associacoes.size()+"\n");
			for (String index : associacoes.keySet()) {
				escritor.write(index+",");
			}
			escritor.write("\n");
			for (String index : associacoes.keySet()) {
				Integer cont = associacoes.get(index);
				while (cont > 0) {
					{
						escritor.write(hash + " " + index + " 1\n");
						cont--;
					}

				}
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
}
