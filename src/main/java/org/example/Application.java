package org.example;

import org.postgresql.ds.PGSimpleDataSource;

public class Application {
    public static void main(String[] args) {

        PGSimpleDataSource db=new PGSimpleDataSource();
        db.setURL(System.getenv("FLASHCARDS_DB_URL"));
        db.setUser(System.getenv("FLASHCARDS_DB_USER"));
        db.setPassword(System.getenv("FLASHCARDS_DB_PASSWORD"));

    }
}