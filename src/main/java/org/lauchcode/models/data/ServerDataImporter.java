package org.lauchcode.models.data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.lauchcode.models.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Created by karumuri on 5/8/2017.
 */
public class ServerDataImporter {

    private static final String DATA_FILE = "server_data.csv";
    private static boolean isDataLoaded = false;
    /**
     * Read in data from a CSV file and store it in a list
     */
    static void loadData(ServerData serverData) {

        // Only load data once
        if (isDataLoaded) {
            return;
        }
        try {
            // Open the CSV file and set up pull out column header info and records
            Resource resource = new ClassPathResource(DATA_FILE);
            InputStream is = resource.getInputStream();
            Reader reader = new InputStreamReader(is);
            CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            List<CSVRecord> records = parser.getRecords();
            Integer numberOfColumns = records.get(0).size();
            String[] headers = parser.getHeaderMap().keySet().toArray(new String[numberOfColumns]);

            // Put the records into a more friendly format
            for (CSVRecord record : records) {
                String locStr = record.get("location");
                String statStr = record.get("status");
                String teaStr = record.get("team");

                Location loc = serverData.getLocations().findByValue(locStr);
                if (loc == null) {
                    loc = new Location(locStr);
                    serverData.getLocations().add(loc);
                }

                Status stat = serverData.getStatuss().findByValue(statStr);
                if (stat == null) {
                    stat = new Status(statStr);
                    serverData.getStatuss().add(stat);
                }

                Team tea = serverData.getTeams().findByValue(teaStr);
                if (tea == null) {
                    tea = new Team(teaStr);
                    serverData.getTeams().add(tea);
                }

                Server newServer = new Server(record.get("name"), loc, stat, tea);

                serverData.add(newServer);
            }

            // flag the data as loaded, so we don't do it twice
            isDataLoaded = true;

        } catch (IOException e) {
            System.out.println("Failed to load server data");
            e.printStackTrace();
        }
    }

}
