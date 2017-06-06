package org.lauchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by karumuri on 5/8/2017.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "")
    public String index(){return "index";}
}
