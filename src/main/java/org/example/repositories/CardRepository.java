package org.example.repositories;

import org.example.models.Card;

import java.util.List;

public interface CardRepository {
    void deleteCard(long cardId);

    void addCard(long chapterId, String question, String answer, boolean isRemembered);

    void updateMemorizing(long cardId);

    List<Card> showAllCards(long chapterId);

    List<Card> getCardsToTraining(long chapterId, int offset);
}
