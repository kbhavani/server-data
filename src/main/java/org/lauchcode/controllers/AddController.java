package org.lauchcode.controllers;

import org.lauchcode.models.*;
import org.lauchcode.models.data.ServerData;
import org.lauchcode.models.forms.EditForm;
import org.lauchcode.models.forms.SearchForm;
import org.lauchcode.models.forms.ServerForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by karumuri on 5/8/2017.
 */

@Controller
@RequestMapping(value = "server")
public class AddController {

    private ServerData serverData = ServerData.getInstance();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id){

        Server newServer = serverData.findById(id);
        model.addAttribute("server", newServer);

        return "server-detail";
    }

    @RequestMapping(value = "add", method=RequestMethod.GET)
    public String add(Model model){

        model.addAttribute(new ServerForm());
        return "new-server";
    }



    @RequestMapping(value = "add", method=RequestMethod.POST)
    public String add(Model model, @Valid ServerForm serverForm, Errors errors){

        if(errors.hasErrors()){

            return "new-server";
        }

        Server newServer = new Server(serverForm.getName(),
                serverData.getLocations().findById(serverForm.getLocationId()),
                serverData.getStatuss().findById(serverForm.getStatusId()),
                serverData.getTeams().findById(serverForm.getTeamId())
        );

        serverData.add(newServer);
        return "redirect:?id=" + newServer.getId();
    }




}
