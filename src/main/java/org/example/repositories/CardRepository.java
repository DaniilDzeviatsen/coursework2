package org.example.repositories;

public interface CardRepository {
void deleteCard(long cardId);
void addCard(long chapter_id, String question, String answer, boolean isRemembered);
void updateMemorizing(long cardId);

}
