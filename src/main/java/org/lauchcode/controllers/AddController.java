package org.lauchcode.controllers;

import org.lauchcode.models.*;
import org.lauchcode.models.data.ServerData;
import org.lauchcode.models.forms.ServerForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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

    @RequestMapping(value = "edit/{serverId}", method=RequestMethod.GET)
    public String edit(Model model, @PathVariable int serverId){

        model.addAttribute("title", "edit cheese");
        Server editedServer=ServerData.getById(serverId);
        return "edit";

    }

    @RequestMapping(value="edit/{serverId}", method=RequestMethod.POST)
    public String edit(Model model, @Valid Server editedServer, Errors errors,
                            @RequestParam("name")String name,@RequestParam("location")String location,
                            @RequestParam("status")String status, @RequestParam("team")String team)
    {

        if(errors.hasErrors()){
            model.addAttribute("title", "edit server");
            return "server/edit";
        }

        editedServer.setName(name);
        return "redirect:/server";


    }
}
