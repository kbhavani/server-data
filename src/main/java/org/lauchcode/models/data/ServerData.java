package org.lauchcode.models.data;

import org.lauchcode.models.*;

import java.util.ArrayList;

/**
 * Created by karumuri on 5/8/2017.
 */
public class ServerData {

    private ArrayList<Server> servers = new ArrayList<>();
    public static ServerData instance;

    private ServerFieldData<Location> locations = new ServerFieldData<>();
    private ServerFieldData<Team> teams = new ServerFieldData<>();
    private ServerFieldData<Status> statuss = new ServerFieldData<>();

    private ServerData(boolean isReload)
    {

        ServerDataImporter.loadData(this,isReload);
    }

    public static ServerData getInstance(boolean isCreateInstance){
        if(isCreateInstance){
            instance = null;
        }

        if (instance == null){

            instance = new ServerData(isCreateInstance);
        }

        return instance;
    }

    public Server findById(int id){
        for (Server server : servers){
            if(server.getId() == id)
                return server;
        }

        return null;
    }


    public ArrayList<Server> findAll(){

        return servers;
    }

    public ArrayList<Server> findByColumnAndValue(ServerFieldType column, String value) {

        ArrayList<Server> matchingServers = new ArrayList<>();

        for (Server server : servers) {
            if (getFieldByType(server, column).contains(value))
                matchingServers.add(server);
        }

        return matchingServers;
    }

    public ArrayList<Server> findByValue(String value) {

        ArrayList<Server> matchingServers = new ArrayList<>();

        for (Server server : servers) {

            if (server.getName().toLowerCase().contains(value)) {
                matchingServers.add(server);
                continue;
            }

            for (ServerFieldType column : ServerFieldType.values()) {
                if (column != ServerFieldType.ALL && getFieldByType(server, column).contains(value)) {
                    matchingServers.add(server);
                    break;
                }
            }
        }

        return matchingServers;
    }


    public void add(Server server) {
        servers.add(server);
    }

    public void edit(Server editServer)
    {

    }


    private static ServerField getFieldByType(Server server, ServerFieldType type) {
        switch(type) {
            case LOCATION:
                return server.getLocation();
            case TEAM:
                return server.getTeam();
            case STATUS:
                return server.getStatus();
        }

        throw new IllegalArgumentException("Cannot get field of text " + type);
    }

    public ServerFieldData<Team> getTeams() {
        return teams;
    }

    public ServerFieldData<Location> getLocations() {
        return locations;
    }

    public ServerFieldData<Status> getStatuss() {
        return statuss;
    }


}
