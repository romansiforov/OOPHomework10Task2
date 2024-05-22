package sample;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
//		Написать программу словарь (англо-украинский). Добавить возможность ручного 
//		наполнения словаря и возможность его сохранения и вычитки из файла.
		
		Vocabulary vocabulary = new Vocabulary();
		
		File vocabularyCsv = new File("voc.csv");
		try {
			vocabularyCsv.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		vocabulary.addWordToVocabulary();
		
		System.out.println(vocabulary.getTranSlation());
		
		try {
			vocabulary.writeVocabularyToFile(vocabularyCsv);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(vocabulary);
		
		Map<String, String> vocabularyFromFile;
		try {
			vocabularyFromFile = vocabulary.getVocabularyFromFile(vocabularyCsv);
			System.out.println(vocabularyFromFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	}

}
