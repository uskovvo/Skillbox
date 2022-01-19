package main;

import main.model.InstallationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Installation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

@RestController
public class InstallationController {

    @Autowired
    private InstallationRepository instRep;

    @GetMapping("/installations/")
    public List<Installation> list(){
        Iterable<Installation> installs = instRep.findAll();
        List<Installation> install = new ArrayList<>();
        installs.forEach(install::add);
        return install;
    }

    @PostMapping("/installations/")
    public int add(Installation installation){
        Installation install = instRep.save(installation);
        return install.getId();
    }

    @GetMapping("/installations/{id}")
    public ResponseEntity get (@PathVariable int id){
        Optional<Installation> optionalInstallation = instRep.findById(id);
        if(!optionalInstallation.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalInstallation.get(), HttpStatus.OK);
    }

    @DeleteMapping("/installations/{id}")
    public void delete (@PathVariable int id){
        instRep.deleteById(id);
    }

    @PutMapping("/installations/{id}")
    public ResponseEntity addById(@PathVariable int id, Installation newInstallation){
        Optional<Installation> optionalInstallation = instRep.findById(id);
        if(!optionalInstallation.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(instRep.save(newInstallation), HttpStatus.OK);
    }

    @DeleteMapping("/installations/")
    public void clearList (){
        instRep.deleteAll();
    }
}
