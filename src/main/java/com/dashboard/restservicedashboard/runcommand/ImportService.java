package com.dashboard.restservicedashboard.runcommand;

import com.dashboard.restservicedashboard.configuration.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class ImportService {

    @Autowired
    private AppProperties appProp;

    private Instant lastImportedInstant;

    private static final Logger log = LoggerFactory.getLogger(ImportService.class);

    private Boolean needToBeImported(Instant instantFromFile) {
        log.info(instantFromFile.toString());
        if(lastImportedInstant ==null) {
            log.info("First run, need to import collections");
            lastImportedInstant = instantFromFile;
            return true;
        } else if(lastImportedInstant.compareTo(instantFromFile)==0) {
            log.info(instantFromFile.toString() + " "  + lastImportedInstant.toString());
            log.info("Collections already imported for that time");
            return false;
        } else {
            int difference = Instant.now().minus(Duration.ofMinutes(5)).compareTo(instantFromFile);
            if(difference>0) {
                log.info("Export done more than 5 minutes ago I can import");
                lastImportedInstant = instantFromFile;
                return true;
            } else {
                log.info("Export done less than 5 minutes ago I cannot import");
                return false;
            }
        }
    }

    public void runImport() {

        Instant updateTimeFromFile = readDateFromFile();
        if(needToBeImported(updateTimeFromFile)) {
            importCollections();
        }


    }

    private void importCollections() {
        for(String collection : appProp.getCollectionsToBeImported().split(",")){
            List<String> args = new ArrayList<String>();
            args.add(appProp.getMongoImportPath());
            args.add("--uri");
            args.add(appProp.getMongoUri());
            args.add("--collection");
            args.add(collection);
            args.add("--drop");
            args.add("--file");
            args.add(appProp.getOriginFolder()+collection+".json");

            for(String arg : args) {
                log.info(arg);
            }



            ProcessBuilder builder = new ProcessBuilder(args);
            try {
                Process process = builder.start();
                logOutput(process);

            } catch (IOException e) {
                log.error(e.getMessage());
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }

        }
    }

    private void logOutput(Process process) throws IOException, InterruptedException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line + "\n");
        }
        int exitVal = process.waitFor();
        if (exitVal == 0) {
            log.info(output.toString());
        } else {
            log.info("Generic error with export command:" +exitVal);
        }
    }

    private Instant readDateFromFile() {

        Instant dateFromFile = null;
        String fileName = appProp.getOriginFolder()+"updatetime.txt";

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            String sDate = stream.findFirst().orElse("");
            log.info("Date on file:"+sDate);
            dateFromFile = Instant.parse(sDate);
            log.info("Date on file after parse:"+dateFromFile.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dateFromFile;
    }


}
