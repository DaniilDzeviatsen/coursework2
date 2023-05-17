package org.example.models;

public class Card {
    private final String question;
    private final String answer;
    private final boolean is_remembered;
    private final long id;

    public Card(String question, String answer, boolean is_remembered, long id) {
        this.question = question;
        this.answer = answer;
        this.is_remembered = is_remembered;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Card{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", is_remembered=" + is_remembered +
                ", id=" + id +
                '}';
    }
}
