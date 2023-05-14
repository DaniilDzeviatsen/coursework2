package org.example.repositories;

import javax.sql.DataSource;

public class CardRepositoryImpl implements CardRepository{
    private final DataSource db;

    public CardRepositoryImpl(DataSource db) {
        this.db = db;
    }

    @Override
    public void deleteCard(long cardId) {

    }

    @Override
    public void addCard(String question, String answer, long chapter_id) {

    }
}
