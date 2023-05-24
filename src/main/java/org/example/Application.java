package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.repositories.CardRepository;
import org.example.repositories.CardRepositoryImpl;
import org.example.repositories.ChapterRepository;
import org.example.repositories.ChapterRepositoryImpl;
import org.example.service.CardService;
import org.example.service.CardServiceImpl;
import org.example.service.ChapterService;
import org.example.service.ChapterServiceImpl;
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
            CardService cardService = new CardServiceImpl(cardRepository);
            ChapterService chapterService = new ChapterServiceImpl(chapterRepository);
            Scanner sc = new Scanner(System.in);

            chapterService.getAllChapters().stream().forEach(System.out::println);
        }

    }
}