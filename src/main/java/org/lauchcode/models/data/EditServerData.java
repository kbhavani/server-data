package org.lauchcode.models.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.*;
import org.lauchcode.models.Location;
import org.lauchcode.models.Server;
import org.lauchcode.models.Status;
import org.lauchcode.models.Team;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.io.BufferedReader;

import java.io.*;
import java.util.List;


/**
 * Created by karumuri on 7/24/2017.
 */
public class EditServerData {

    private static final String COMMA_DELIMITER=",";
    private static final String NEW_LINE_SEPERATOR="\n";

    private static final String DATA_FILE = "server_data.csv";

    private static final String DATA_FILE_UPDATE = "C:/SourceFile/server_data_edit.csv";

    private static final String DATA_FILE_DIR = "C:/SourceFile";

    private static final String DATA_FILE_NEW = "C:/SourceFile/server_data.csv";

    private ServerFieldData<Location> locations = new ServerFieldData<>();
    private ServerFieldData<Team> teams = new ServerFieldData<>();
    private ServerFieldData<Status> statuss = new ServerFieldData<>();

    public ServerFieldData<Team> getTeams() {
        return teams;
    }

    public ServerFieldData<Location> getLocations() {
        return locations;
    }

    public ServerFieldData<Status> getStatuss() {
        return statuss;
    }

    public void addData(Server server){

        BufferedReader fileReader = null;
        FileWriter fileWriter = null;
        File dir = new File(DATA_FILE_DIR);
        File[] csvFiles = dir.listFiles();
        File newFile = null;
        File sourceFile = null;

        try

        {
            String line = "";
            for(File csvFile :csvFiles){
                if(csvFile.isFile() && csvFile.getName().equalsIgnoreCase(DATA_FILE)){
                    sourceFile = csvFile;
                    break;
                }
            }
           fileReader = new BufferedReader(new FileReader(sourceFile));
           newFile = new File(DATA_FILE_UPDATE);
            fileWriter = new FileWriter(newFile);
            fileWriter.append(fileReader.readLine());
            fileWriter.append(NEW_LINE_SEPERATOR);
           while((line = fileReader.readLine())!=null){

                        fileWriter.append(line);
                        fileWriter.append(NEW_LINE_SEPERATOR);

            }

            fileWriter.append(server.getName());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(server.getLocation().getValue());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(server.getStatus().getValue());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(server.getTeam().getValue());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(NEW_LINE_SEPERATOR);
            System.out.println("Successfully created new CSV file");
            fileWriter.flush();
            fileWriter.close();
            fileReader.close();
            sourceFile.delete();
            newFile.renameTo(new File(DATA_FILE_NEW));
            System.out.println("Successfully renamed new CSV file");
        } catch (Exception e) {
            System.out.println("Failed to create file");
            e.printStackTrace();
            try {
                fileWriter.flush();
                fileWriter.close();
                fileReader.close();
            }catch (IOException ex){

                System.out.println("Error while flushing/closing fileWriter !!!");
                ex.printStackTrace();
            }
        }

    }

    public void editData(Server server) {

        System.out.println("Entered in to editData");

        BufferedReader fileReader = null;
        FileWriter fileWriter = null;
        File dir = new File(DATA_FILE_DIR);
        File[] csvFiles = dir.listFiles();
        File editFile = null;
        File sourceFile = null;


        try

        {
            String line = "";
            for(File csvFile :csvFiles){
                if(csvFile.isFile() && csvFile.getName().equalsIgnoreCase(DATA_FILE)){
                    sourceFile = csvFile;
                    break;
                }
            }
            fileReader = new BufferedReader(new FileReader(sourceFile));
            editFile = new File(DATA_FILE_UPDATE);
            fileWriter = new FileWriter(editFile);
            fileWriter.append(fileReader.readLine());
            fileWriter.append(NEW_LINE_SEPERATOR);
            while((line = fileReader.readLine())!=null){
                String[] tokens = line.split(COMMA_DELIMITER);
                if(tokens.length > 0){
                    if(tokens[0].equalsIgnoreCase(server.getName())){
                        fileWriter.append(server.getName());
                        fileWriter.append(COMMA_DELIMITER);
                        fileWriter.append(server.getLocation().getValue());
                        fileWriter.append(COMMA_DELIMITER);
                        fileWriter.append(server.getStatus().getValue());
                        fileWriter.append(COMMA_DELIMITER);
                        fileWriter.append(server.getTeam().getValue());
                        fileWriter.append(COMMA_DELIMITER);
                        fileWriter.append(NEW_LINE_SEPERATOR);
                    }
                    else {
                        fileWriter.append(line);
                        fileWriter.append(NEW_LINE_SEPERATOR);
                    }
                }
            }
            System.out.println("Successfully created CSV file");
            fileWriter.flush();
            fileWriter.close();
            fileReader.close();
            sourceFile.delete();
            editFile.renameTo(new File(DATA_FILE_NEW));
           System.out.println("Successfully renamed CSV file");
            } catch (Exception e) {
            System.out.println("Failed to create file");
            e.printStackTrace();
            try {
            fileWriter.flush();
            fileWriter.close();
            fileReader.close();
            }catch (IOException ex){

                System.out.println("Error while flushing/closing fileWriter !!!");
                ex.printStackTrace();
            }
        }
    }
}
