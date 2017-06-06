package org.lauchcode.models;

/**
 * Created by karumuri on 5/8/2017.
 */
public class Server {

    private int id;
    private static int nextId = 1;

    private String name;
    private Location location;
    private Status status;
    private Team team;
    private int serverId;

    public Server(){
        id = nextId;
        nextId++;
    }

    public Server(String aName, Location aLocation,
               Status astatus, Team ateam) {

        this();

        name = aName;
        location = aLocation;
        status = astatus;
        team = ateam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public int getServerId(){ return serverId;}

    public void setServerId(int serverId){this.serverId=serverId;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Server server = (Server) o;

        return id == server.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
