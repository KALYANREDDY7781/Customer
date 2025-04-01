package com.myorg.CardManagement.service;

import com.myorg.CardManagement.dto.CardsDto;
import com.myorg.CardManagement.entity.Card;
import com.myorg.CardManagement.exception.CardNotFoundException;
import com.myorg.CardManagement.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CardsServiceimpl implements CardsService{
    private final RestTemplate restTemplate;
    private final CardRepository cardRepository;

    @Autowired
    public CardsServiceimpl(RestTemplate restTemplate, CardRepository cardRepository) {
        this.restTemplate = restTemplate;
        this.cardRepository = cardRepository;
    }

    @Override
    public int addCard(CardsDto cardsDto) {
        boolean flag = checkIfCustomerExists(cardsDto.getCustomerId());
        if(!flag){
            throw new CardNotFoundException("Cards doesn't exists with ID: "+cardsDto.getCustomerId());
        }
        return cardRepository.add(cardsDto);
    }

    @Override
    public int cancelCard(int customerId, int lastFourDigits) {
        boolean flag = checkIfCustomerExists(customerId);
        if(!flag){
            throw new CardNotFoundException("Cards doesn't exists with ID: "+customerId);
        }
        return cardRepository.cancel(customerId,lastFourDigits);
    }

    @Override
    public List<Card> getCardsByCustomerId(int customerId) {
        boolean flag = checkIfCustomerExists(customerId);
        if(!flag){
            throw new CardNotFoundException("Cards doesn't exists with ID: "+customerId);
        }
        return cardRepository.getCards(customerId);
    }

    public boolean checkIfCustomerExists(int customerId){
        String url = "http://localhost:8081/customers/"+customerId+"/exists";
        return restTemplate.getForObject(url, Boolean.class);
    }
}
