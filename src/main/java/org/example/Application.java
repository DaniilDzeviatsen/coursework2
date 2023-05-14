package org.example;

import org.example.repositories.ChapterRepository;
import org.example.repositories.ChapterRepositoryImpl;
import org.postgresql.ds.PGSimpleDataSource;

public class Application {
    public static void main(String[] args) {

        PGSimpleDataSource db=new PGSimpleDataSource();
        db.setURL(System.getenv("FLASHCARDS_DB_URL"));
        db.setUser(System.getenv("FLASHCARDS_DB_USER"));
        db.setPassword(System.getenv("FLASHCARDS_DB_PASSWORD"));

        ChapterRepository chapterRepository=new ChapterRepositoryImpl(db);

    }
}