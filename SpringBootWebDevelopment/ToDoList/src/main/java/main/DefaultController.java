package main;

import main.model.Installation;
import main.model.InstallationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class DefaultController {

    @Autowired
    InstallationRepository installationRepository;


    @RequestMapping("/")
    public String index(Model model){
        Iterable<Installation> installationIterable = installationRepository.findAll();
        ArrayList<Installation> installs = new ArrayList<>();
        for(Installation installation: installationIterable){
            installs.add(installation);
        }

        model.addAttribute("installs", installs);
        model.addAttribute("installsCount", installs.size());
        return "index";
    }
}
