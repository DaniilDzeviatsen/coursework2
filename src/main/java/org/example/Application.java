package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.repositories.CardRepository;
import org.example.repositories.CardRepositoryImpl;
import org.example.repositories.ChapterRepository;
import org.example.repositories.ChapterRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;



public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(System.getenv("FLASHCARDS_DB_URL"));
        hikariConfig.setUsername(System.getenv("FLASHCARDS_DB_USER"));
        hikariConfig.setPassword(System.getenv("FLASHCARDS_DB_PASSWORD"));
        try (HikariDataSource db = new HikariDataSource(hikariConfig)) {

            ChapterRepository chapterRepository = new ChapterRepositoryImpl(db);
            CardRepository cardRepository = new CardRepositoryImpl(db);
            Scanner sc = new Scanner(System.in);

        /*PGSimpleDataSource db = new PGSimpleDataSource();
        db.setURL(System.getenv("FLASHCARDS_DB_URL"));
        db.setUser(System.getenv("FLASHCARDS_DB_USER"));
        db.setPassword(System.getenv("FLASHCARDS_DB_PASSWORD"));*/


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
            cardRepository.getCardsToTraining(3, 1).stream().forEach(System.out::println);
        }
    }
}