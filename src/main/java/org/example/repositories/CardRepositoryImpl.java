package org.example.repositories;

import org.example.exceptions.RepositoryException;
import org.example.models.Card;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardRepositoryImpl implements CardRepository {
    private final DataSource db;

    public CardRepositoryImpl(DataSource db) {
        this.db = db;
    }

    @Override
    public void deleteCard(long cardId) {
        String sql = """
                DELETE
                FROM card
                WHERE card.id = ?;
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
    public void addCard(long chapterId, String question, String answer, boolean isRemembered) {
        String sql = """
                INSERT INTO card (chapter_id, question, answer, is_remembered)
                VALUES (?,?,?,?);
                """;
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, chapterId);
            statement.setString(2, question);
            statement.setString(3, answer);
            statement.setBoolean(4, isRemembered);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void updateMemorizing(long cardId) {
        String sql = """
                UPDATE card
                SET is_remembered = TRUE
                WHERE card.id = ?;
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
    public List<Card> showAllCards(long chapterId) {
        String sql = """
                SELECT card.id AS id,
                       card.question AS question,
                       card.answer AS answer,
                       card.is_remembered AS remembered
                FROM card
                WHERE card.chapter_id = ?
                                 """;
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, chapterId);
            ResultSet resultSet = statement.executeQuery();
            List<Card> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Card(
                        resultSet.getString("question"),
                        resultSet.getString("answer"),
                        resultSet.getBoolean("remembered"),
                        resultSet.getLong("id")
                ));
            }
            return result;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
