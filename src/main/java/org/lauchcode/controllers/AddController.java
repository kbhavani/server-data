package org.lauchcode.controllers;

import org.lauchcode.models.*;
import org.lauchcode.models.data.EditServerData;
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

    private ServerData serverData = ServerData.getInstance(false);

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
                serverForm.findByLocationId(serverForm.getLocationId()),
                serverForm.findByStatusId(serverForm.getStatusId()),
                serverForm.findByTeamId(serverForm.getTeamId())
        );

        System.out.println("print the location"+serverForm.getLocationId());
        //System.out.println("print the data" + serverData.getLocations().);

        //serverData.add(newServer);
        EditServerData editServerData = new EditServerData();
        editServerData.addData(newServer);

        //return "redirect:?id=" + newServer.getId();
        return "add-save";/////////////
    }
}
