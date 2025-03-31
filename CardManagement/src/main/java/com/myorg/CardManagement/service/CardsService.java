package com.myorg.CardManagement.service;

import com.myorg.CardManagement.dto.CardsDto;
import com.myorg.CardManagement.entity.Card;
import java.util.List;

public interface CardsService {
    int addCard(CardsDto cardsDto);
    int cancelCard(int customerId,int lastFourDigits);
    List<Card> getCardsByCustomerId(int customerId);
}
