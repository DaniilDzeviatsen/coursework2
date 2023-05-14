package org.example.repositories;

public interface CardRepository {
void deleteCard(long cardId);
void addCard(String question, String answer, long chapter_id);

}
