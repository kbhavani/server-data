package org.lauchcode.controllers;

import org.lauchcode.models.Server;
import org.lauchcode.models.ServerFieldType;
import org.lauchcode.models.data.ServerData;
import org.lauchcode.models.forms.SearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by karumuri on 5/8/2017.
 */

@Controller
@RequestMapping("search")
public class SearchController {

    private ServerData serverData = ServerData.getInstance();

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute(new SearchForm());
        return "search";
    }
    @RequestMapping(value = "results")
    public String search(Model model,
                         @ModelAttribute SearchForm searchForm) {

        ArrayList<Server> servers;

        if (searchForm.getSearchField().equals(ServerFieldType.ALL)) {
            servers = serverData.findByValue(searchForm.getKeyword());
        } else {
            servers = serverData.findByColumnAndValue(searchForm.getSearchField(), searchForm.getKeyword());
        }

        model.addAttribute("servers", servers);

        return "search";
    }

}
