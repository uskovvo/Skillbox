package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.Purchase;

import java.util.Hashtable;
import java.util.List;

@RestController
public class PurchaseController {

    @GetMapping("/purchases/")
    public List<Purchase> list(){
        return Storage.getAllPurchase();
    }

    @PostMapping("/purchases/")
    public int add(Purchase purchase){
        return Storage.addPurchase(purchase);
    }

    @GetMapping("/purchases/{id}")
    public ResponseEntity get (@PathVariable int id){
        Purchase purchase = Storage.getPurchase(id);
        if(purchase == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(purchase, HttpStatus.OK);
    }

    @DeleteMapping("/purchases/{id}")
    public void delete (@PathVariable int id){
        Storage.deletePurchase(id);
    }

    @PutMapping("/purchases/{id}")
    public ResponseEntity addById(@PathVariable int id, Purchase newPurchase){
        Purchase purchase = Storage.updatePurchase(id, newPurchase);
        if(purchase == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(purchase, HttpStatus.OK);
    }

    @DeleteMapping("/purchases/")
    public Hashtable<Integer, Purchase> clearList (){
        return Storage.clearPurchaseToDos();
    }
}
