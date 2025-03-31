package com.myorg.CardManagement.controller;

import com.myorg.CardManagement.dto.CardsDto;
import com.myorg.CardManagement.entity.Card;
import com.myorg.CardManagement.service.CardsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardsController {

    private final CardsService cardsService;

    public CardsController(CardsService cardsService){
        this.cardsService=cardsService;
    }

    @PostMapping
    public String createCard(@RequestBody CardsDto cardsDto){
        int rows = cardsService.addCard(cardsDto);
        if(rows == 0){
            return "Failed to add card";
        }
        return "Card created";
    }

    @PutMapping("/{customerId}")
    public String cancelCard(@PathVariable int customerId,@RequestParam int lastFourDigits){
        int rows = cardsService.cancelCard(customerId,lastFourDigits);
        if(rows == 0){
            return "Failed to cancel card";
        }
        return "Card cancelled";
    }

    @GetMapping("/{customerId}")
    public List<Card> getCardsByCustomerId(@PathVariable int customerId){
        return cardsService.getCardsByCustomerId(customerId);
    }
}
