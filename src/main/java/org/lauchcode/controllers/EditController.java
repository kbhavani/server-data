package org.lauchcode.controllers;

import org.lauchcode.models.Location;
import org.lauchcode.models.Server;
import org.lauchcode.models.Status;
import org.lauchcode.models.Team;
import org.lauchcode.models.data.EditServerData;
import org.lauchcode.models.data.ServerData;
import org.lauchcode.models.forms.EditForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by karumuri on 7/13/2017.
 */
@Controller
@RequestMapping(value = "edit")
public class EditController {



    private ServerData serverData = ServerData.getInstance();


    @RequestMapping(value = "", method= RequestMethod.GET)
    public String edit(Model model){

        model.addAttribute(new EditForm());
        return "edit";
    }

    @RequestMapping(value = "results", method=RequestMethod.GET )
    public String search(Model model,
                         @ModelAttribute EditForm editForm) {

        ArrayList<Server> servers;


        /*servers = serverData.findByColumnAndValue(editForm.getSearchField(), editForm.getKeyword()); */

        servers = serverData.findByValue(editForm.getKeyword());

        model.addAttribute("servers", servers);

        return "edit";
    }

    @RequestMapping(value = "results/save", method=RequestMethod.GET)
    public String edit(Model model, @Valid EditForm editForm, Errors errors){

        System.out.println("Entered in to edit");

         EditServerData editServerData = new EditServerData();

        /*if(errors.hasErrors()){

            return "edit";
        }*/

        Location location = new Location(Integer.toString(editForm.getLocationId()));
        Status status = new Status(Integer.toString(editForm.getStatusId()));
        Team team = new Team(Integer.toString(editForm.getTeamId()));

        Server editServer = new Server(editForm.getName(),
                location,
                status,
                team
        );

        editServerData.editData(editServer);


        return "redirect:?id=" + editServer.getId();
    }
}
