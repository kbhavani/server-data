package org.lauchcode.models.forms;

import org.lauchcode.models.*;
import org.lauchcode.models.data.ServerData;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

/**
 * Created by karumuri on 7/10/2017.
 */
public class EditForm {

    @NotNull
    @Size(min=1, message = "Name may not be empty")
    private String name;

    @NotNull
    private int serverId;

    @NotNull
    private int locationId;

    @NotNull
    private int statusId;

    @NotNull
    private int teamId;



    private ArrayList<Location> locations;
    private ArrayList<Status> statuss;
    private ArrayList<Team> teams;


    public EditForm(){

        ServerData serverData = ServerData.getInstance(false);

        locations = serverData.getLocations().findAll();
        statuss = serverData.getStatuss().findAll();
        teams = serverData.getTeams().findAll();

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public ArrayList<Status> getStatuss() {
        return statuss;
    }

    public void setStatuss(ArrayList<Status> statuss) {
        this.statuss = statuss;
    }
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }


    public Location findByLocationId(int id){
        for (Location location : locations){
            if(location.getId() == id)
                return location;
        }

        return null;
    }

    public Status findByStatusId(int id){
        for (Status status : statuss){
            if(status.getId() == id)
                return status;
        }

        return null;
    }

    public Team findByTeamId(int id){
        for (Team team : teams){
            if(team.getId() == id)
                return team;
        }

        return null;
    }
}
