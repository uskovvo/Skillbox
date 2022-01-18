package main;

import response.Cleaning;
import response.Purchase;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {

    private static AtomicInteger currentId = new AtomicInteger(1);
    private static Hashtable<Integer, Purchase> purchases = new Hashtable<>();

    public static int addPurchase(Purchase purchase){
        int id = currentId.getAndIncrement();
        purchase.setId(id);
        purchases.put(id, purchase);
        return id;
    }

    public static List<Purchase> getAllPurchase(){
        return new ArrayList<>(purchases.values());
    }

    public static Purchase getPurchase(int purchaseId){
        if(purchases.containsKey(purchaseId)){
            return purchases.get(purchaseId);
        }
        return null;
    }

    public static void deletePurchase(int purchaseId){
        purchases.remove(purchaseId);
    }

    public static Purchase updatePurchase(int id, Purchase newPurchase){
        if(purchases.containsKey(id)){
            purchases.put(id, newPurchase);
            return purchases.get(id);
        }
        return null;
    }

    public static Hashtable<Integer, Purchase> clearPurchaseToDos(){
        purchases.clear();
        currentId.set(1);
        return purchases;
    }
}
