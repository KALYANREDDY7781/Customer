package com.myorg.CardManagement.mapper;

import com.myorg.CardManagement.entity.Card;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CardsMapper implements RowMapper<Card> {
    @Override
    public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
        Card card = new Card();
        card.setId(rs.getInt("id"));
        card.setCustomerId(rs.getInt("customer_id"));
        card.setCardNumber(rs.getString("card_number"));
        card.setCardType(rs.getString("card_type"));
        card.setExpiryDate(rs.getTimestamp("expiry_date").toLocalDateTime());
        card.setStatus(rs.getString("status"));
        card.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        card.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return card;
    }
}
