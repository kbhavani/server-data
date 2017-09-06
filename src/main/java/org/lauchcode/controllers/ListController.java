package org.lauchcode.controllers;


import org.lauchcode.models.Server;
import org.lauchcode.models.ServerField;
import org.lauchcode.models.ServerFieldType;
import org.lauchcode.models.data.ServerData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * Created by karumuri on 5/8/2017.
 */
@Controller
@RequestMapping(value = "list")

public class ListController {

    //private ServerData serverData = ServerData.getInstance();

    @RequestMapping(value = "")
    public String list(Model model){

        ServerFieldType[] fields = ServerFieldType.values();
        model.addAttribute("fields", fields);
        return "list";

    }

    @RequestMapping(value = "values")
    public String listColumnValues(Model model, @RequestParam ServerFieldType column) {

        if (column.equals(ServerFieldType.ALL)) {
            return "redirect:/list/all";
        }
        ServerData serverData = ServerData.getInstance(true);
        ArrayList<? extends ServerField> items;

        switch(column) {

            case LOCATION:
                items = serverData.getLocations().findAll();
                break;
            case TEAM:
                items = serverData.getTeams().findAll();
                break;
            case STATUS:
            default:
                items = serverData.getStatuss().findAll();

        }

        model.addAttribute("title", "All " + column.getName() + " Values");
        model.addAttribute("column", column);
        model.addAttribute("items", items);

        return "list-column";
    }

    @RequestMapping(value = "servers")
    public String listServersByColumnAndValue(Model model,
                                              @RequestParam ServerFieldType column, @RequestParam String name){
        ServerData serverData = ServerData.getInstance(true);
        ArrayList<Server> servers = serverData.findByColumnAndValue(column, name);

        model.addAttribute("title", "Servers with " + column.getName() + ": " + name);
        model.addAttribute("servers", servers);

        return "list-servers";
    }

    @RequestMapping(value = "all")
    public String listAllServers(Model model) {

        ServerData serverData = ServerData.getInstance(true);
        ArrayList<Server> servers = serverData.findAll();

        model.addAttribute("title", "All servers");
        model.addAttribute("servers", servers);

        return "list-servers";

    }

}
