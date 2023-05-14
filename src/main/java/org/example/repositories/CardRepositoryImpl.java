package org.example.repositories;

import org.example.exceptions.RepositoryException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CardRepositoryImpl implements CardRepository {
    private final DataSource db;

    public CardRepositoryImpl(DataSource db) {
        this.db = db;
    }

    @Override
    public void deleteCard(long cardId) {
        String sql = """
                DELETE
                FROM CARD
                WHERE CARD.ID=?;
                """;
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, cardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void addCard(String question, String answer, long chapter_id) {

    }
}
