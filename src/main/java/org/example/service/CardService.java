package org.example.service;

import org.example.models.Card;

import java.util.List;

public interface CardService {

    void deleteCard(long cardId);

    void addCard(long chapterId, String question, String answer, boolean isRemembered);

    void updateMemorizing(long cardId, long chapterId);

    List<Card> getOneCardData(long chapterId, long offset);

}
