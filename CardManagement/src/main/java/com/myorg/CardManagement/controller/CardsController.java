package com.myorg.CardManagement.controller;

import com.myorg.CardManagement.dto.CardsDto;
import com.myorg.CardManagement.entity.Card;
import com.myorg.CardManagement.service.CardsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardsController {

    private final CardsService cardsService;
    private static final Logger logger = LoggerFactory.getLogger(CardsController.class);
    public CardsController(CardsService cardsService){
        this.cardsService=cardsService;
    }

    @PostMapping
    public ResponseEntity<HashMap<String, String>> createCard(@RequestBody CardsDto cardsDto){
        int rows = cardsService.addCard(cardsDto);
        HashMap<String,String> res = new HashMap<>();
        if(rows == 0){
            res.put("message", "Failed to add card");
            logger.error("Failed to add Card for customer: "+cardsDto.getCustomerId());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
        else {
            res.put("message", "Card is added to customer profile");
            logger.info("Card has been added successfully for customer");
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }
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
        List<Card> cards = cardsService.getCardsByCustomerId(customerId);
        logger.info("Card details for customer Id: "+customerId+" are: "+ cards);
        System.out.println(("Card details for customer Id: "+customerId+" are: "+ cards));
        return cards;
    }
}
