package sample;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Vocabulary {
	private Map<String, String> vocabulary;
	String separator = " ";
	
	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public Vocabulary() {
		vocabulary = new HashMap<>();
	}
	
	public Vocabulary(Map<String, String> vocabulary, String separator) {
		this.vocabulary = vocabulary;
		this.separator = separator;
	}

	public Map<String, String> getVocabulary() {
		return vocabulary;
	}

	public void setVocabulary(Map<String, String> vocabulary) {
		this.vocabulary = vocabulary;
	}
	
	public boolean addWordToVocabulary() {
		String word;
		String ukrTranslation;
		var sc = new Scanner(System.in);
		
		System.out.println("Enter eglish word");
		word = sc.nextLine();
		System.out.println("Enter ukrainian translation for that word");
		ukrTranslation = sc.nextLine();
		vocabulary.put(word, ukrTranslation);

		return vocabulary.get(word) != null;
	}
	
	public boolean addWordToVocabulary(String word, String ukrTranslation) {
		vocabulary.put(word, ukrTranslation);
		return vocabulary.get(word) != null;
	}
	
	public String getTranSlation(String word) {
		return vocabulary.get(word)!= null ? vocabulary.get(word) : "There is no such a word";
	}
	
	public String getTranSlation() {
		String tmp = "";
		var sc = new Scanner(System.in);
		
		System.out.println("Enter eglish word which you want to translate");
		tmp = sc.nextLine();
		return vocabulary.getOrDefault(tmp, "There is no such a word");

	}
	
	public void writeVocabularyToFile(File file) throws IOException{
		Set<String> words = vocabulary.keySet();
		String tmp = "";
		try(Writer fw = new FileWriter(file, true)){
			for(String word : words) {
				tmp = word+separator+vocabulary.get(word)+System.lineSeparator();
				fw.write(tmp);
			}
		}
	}
	
	public Map<String, String> getVocabularyFromFile(File file) throws IOException{
		String result = "";
		try(Reader rd = new FileReader(file)){
			char[] chars = new char[5000];
			int readChars = 0;
			for(;;) {
				readChars = rd.read(chars);
				if(readChars <= 0) {
					break;
				}
				result += new String(chars, 0, readChars);
			}
		}
		return populateMapFromString(result);
	}
	
	private Map<String,String> populateMapFromString(String str){
		Map<String,String> tmp = new HashMap<>();
		
		String [] pairsArray = str.split(System.lineSeparator());
		String [] pair;
		for(int i = 0 ; i < pairsArray.length; i++) {
			pair = pairsArray[i].split(separator);
			tmp.put(pair[0], pair[1]);
		}
		
		return tmp;
	}

	@Override
	public String toString() {
		return "Vocabulary [vocabulary=" + vocabulary + "]";
	}
	
	
}
