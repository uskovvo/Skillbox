package main;

import main.model.Installation;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {

    private static AtomicInteger currentId = new AtomicInteger(1);
    private static Hashtable<Integer, Installation> installations = new Hashtable<>();

    public static int addInstallation(Installation installation){
        int id = currentId.getAndIncrement();
        installation.setId(id);
        installations.put(id, installation);
        return id;
    }

    public static List<Installation> getAllInstallations(){
        return new ArrayList<>(installations.values());
    }

    public static Installation getInstallation(int installationId){
        if(installations.containsKey(installationId)){
            return installations.get(installationId);
        }
        return null;
    }

    public static void delInstallationById(int installationId){
        installations.remove(installationId);
    }

    public static Installation updateInstallationById(int installationId, Installation newInstallation){
        if(installations.containsKey(installationId)){
            installations.put(installationId, newInstallation);
            return installations.get(installationId);
        }
        return null;
    }

    public static Hashtable<Integer, Installation> clearInstallationsList(){
        installations.clear();
        currentId.set(1);
        return installations;
    }
}
