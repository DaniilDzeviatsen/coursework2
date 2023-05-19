package org.example;

import org.example.models.Card;
import org.example.models.Chapter;
import org.example.repositories.CardRepository;
import org.example.repositories.CardRepositoryImpl;
import org.example.repositories.ChapterRepository;
import org.example.repositories.ChapterRepositoryImpl;
import org.postgresql.ds.PGSimpleDataSource;

import java.util.List;
import java.util.Scanner;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class Application {
    public static void main(String[] args) {

        PGSimpleDataSource db = new PGSimpleDataSource();
        db.setURL(System.getenv("FLASHCARDS_DB_URL"));
        db.setUser(System.getenv("FLASHCARDS_DB_USER"));
        db.setPassword(System.getenv("FLASHCARDS_DB_PASSWORD"));

        ChapterRepository chapterRepository = new ChapterRepositoryImpl(db);
        CardRepository cardRepository = new CardRepositoryImpl(db);
        Scanner sc = new Scanner(System.in);
       /* while (true){
            System.out.print("> ");
            String command=sc.nextLine();
            String [] parts=command.split("\\s");
            switch (parts[0]){
                case "addChapter"->{
                    long chapterId=Long.parseLong(parts[1]);

                }
            }
        }*/
        //String name=sc.nextLine();
        //chapterRepository.getAllChapters().stream().forEach(System.out::println);
//cardRepository.updateMemorizing(7); ;
        //List<Chapter> chapters=chapterRepository.getAllChapters();
        //chapterRepository.getAllChapters().stream().forEach(System.out::println);
        //cardRepository.showAllCards(3).stream().forEach(System.out::println);
        cardRepository.getCardsToTraining(3,1).stream().forEach(System.out::println);
    }
}