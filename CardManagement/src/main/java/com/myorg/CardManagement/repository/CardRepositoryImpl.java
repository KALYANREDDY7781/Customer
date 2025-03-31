package com.myorg.CardManagement.repository;

import com.myorg.CardManagement.dto.CardsDto;
import com.myorg.CardManagement.entity.Card;
import com.myorg.CardManagement.mapper.CardsMapper;
import com.myorg.CardManagement.utility.QueryLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CardRepositoryImpl implements CardRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final QueryLoader queryLoader;
    private final CardsMapper cardsMapper;

    @Autowired
    public CardRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, QueryLoader queryLoader, CardsMapper cardsMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.queryLoader = queryLoader;
        this.cardsMapper = cardsMapper;
    }

    @Override
    public int add(CardsDto cardsDto) {
        String sql = queryLoader.getQuery("createCard");
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("customerId", cardsDto.getCustomerId());
        paramMap.addValue("cardNumber", cardsDto.getCardNumber());
        paramMap.addValue("cardType", cardsDto.getCardType());
        paramMap.addValue("expiryDate", cardsDto.getExpiryDate());
        return jdbcTemplate.update(sql,paramMap);
    }

    @Override
    public int cancel(int customerId,int lastFourDigits) {
        String sql = queryLoader.getQuery("cancelCard");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("customerId", customerId);
        paramMap.put("lastFourDigits", lastFourDigits);
        return jdbcTemplate.update(sql,paramMap);
    }

    @Override
    public List<Card> getCards(int customerId) {
        String sql = queryLoader.getQuery("getCardsByCustomerId");
        MapSqlParameterSource paramMap = new MapSqlParameterSource();
        paramMap.addValue("customerId",customerId);
        return jdbcTemplate.query(sql,paramMap,cardsMapper);
    }
}
