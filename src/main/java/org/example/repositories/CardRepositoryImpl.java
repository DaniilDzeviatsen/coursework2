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
                WHERE CARD.ID = ?;
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
    public void addCard(long chapter_id, String question, String answer, boolean is_remembered) {
        String sql = """
                INSERT INTO CARD (chapter_id, question, answer, is_remembered)
                VALUES (?,?,?,?);
                """;
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, chapter_id);
            statement.setString(2, question);
            statement.setString(3, answer);
            statement.setBoolean(4, is_remembered);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void updateMemorizing(long cardId) {
        String sql = """
                UPDATE CARD
                SET IS_REMEMBERED = TRUE
                WHERE CARD.ID = ?;
                """;
        try(
                Connection connection= db.getConnection();
                PreparedStatement statement=connection.prepareStatement(sql);
                ){
            statement.setLong(1, cardId);
            statement.executeUpdate();
        }catch (SQLException e){
            throw new RepositoryException(e);
        }
    }
}
