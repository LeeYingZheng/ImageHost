package com.websystique.springmvc.controller;

import com.websystique.springmvc.model.UserDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class TSA{

    private static String LOC;

    @Value("${path.location}")
    private void setLoc(String privateLoc) {
        TSA.LOC = privateLoc;
    }

    public static void runTSA(UserDocument document) throws IOException {

        //get tsq
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd "+LOC+document.getId()+ "/ && " +
                "openssl ts -query -data "+document.getName()+ " -no_nonce -sha512 -out file.tsq");

        Process p = builder.start();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder build = new StringBuilder();
        String line = null;
        while ( (line = reader.readLine()) != null) {
            build.append(line);
            build.append(System.getProperty("line.separator"));
        }
        String result = builder.toString();
        System.out.println(result); //Apparently if you use ProcessBuilder.start in Java to start an external process
        // you have to consume its stdout/stderr, otherwise the external process hangs.
        // http://stackoverflow.com/questions/18505446/running-jar-exe-process-waitfor-never-return

        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //get tsr
        builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd "+LOC+document.getId()+"/ && " +
                "curl -H \"Content-Type: application/timestamp-query\" --data-binary \"@file.tsq\" "
                + "https://freetsa.org/tsr > file.tsr");

        p = builder.start();

        reader =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        build = new StringBuilder();
        line = null;
        while ( (line = reader.readLine()) != null) {
            build.append(line);
            build.append(System.getProperty("line.separator"));
        }
        result = builder.toString();
        System.out.println(result); //Apparently if you use ProcessBuilder.start in Java to start an external process
        // you have to consume its stdout/stderr, otherwise the external process hangs.
        // http://stackoverflow.com/questions/18505446/running-jar-exe-process-waitfor-never-return

        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void runTestTSA(UserDocument document) throws IOException {
        //get tsq
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd "+LOC+ "/ && " +
                "openssl ts -query -data 108/test1.jpg -no_nonce -sha512 -out 108/file.tsq");

        Process p = builder.start();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder build = new StringBuilder();
        String line = null;
        while ( (line = reader.readLine()) != null) {
            build.append(line);
            build.append(System.getProperty("line.separator"));
        }
        String result = builder.toString();
        System.out.println(result); //Apparently if you use ProcessBuilder.start in Java to start an external process
        // you have to consume its stdout/stderr, otherwise the external process hangs.
        // http://stackoverflow.com/questions/18505446/running-jar-exe-process-waitfor-never-return

        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //get tsr
        builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd "+LOC+ "/ && " +
                "curl -H \"Content-Type: application/timestamp-query\" --data-binary \"@108/file.tsq\" " +
                "https://freetsa.org/tsr > 108/file.tsr");

        p = builder.start();

        reader =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        build = new StringBuilder();
        line = null;
        while ( (line = reader.readLine()) != null) {
            build.append(line);
            build.append(System.getProperty("line.separator"));
        }
        result = builder.toString();
        System.out.println(result); //Apparently if you use ProcessBuilder.start in Java to start an external process
        // you have to consume its stdout/stderr, otherwise the external process hangs.
        // http://stackoverflow.com/questions/18505446/running-jar-exe-process-waitfor-never-return

        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
