package org.example.service;

import org.example.exceptions.InputDataException;
import org.example.models.Card;
import org.example.repositories.CardRepository;

import java.util.List;

public class CardServiceImpl implements CardService {
    CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void deleteCard(long cardId) {
        cardRepository.deleteCard(cardId);
    }

    public void addCard(long chapterId, String question, String answer, boolean isRemembered) {
        if (!question.isEmpty() && !answer.isEmpty()) {
            cardRepository.addCard(chapterId, question, answer, isRemembered);
        } else throw new InputDataException();
    }

    public void updateMemorizing(long cardId, long chapterId) {
        cardRepository.updateMemorizing(cardId);
        cardRepository.getOneCardData(chapterId, cardId);
    }

    public List<Card> getOneCardData(long chapterId, long offset) {
        return cardRepository.getOneCardData(chapterId, offset);
    }
}
