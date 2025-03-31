package com.myorg.CardManagement.repository;

import com.myorg.CardManagement.dto.CardsDto;
import com.myorg.CardManagement.entity.Card;

import java.util.List;

public interface CardRepository {
    int add(CardsDto cardsDto);
    int cancel(int customerId,int lastFourDigits);
    List<Card> getCards(int customerId);
}
