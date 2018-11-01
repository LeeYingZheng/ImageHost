package com.websystique.springmvc.controller;

import com.websystique.springmvc.model.Browser;
import com.websystique.springmvc.model.UserDocument;
import org.apache.commons.io.FilenameUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;

/**
 * Created by admin on 25/2/2016.
 */

@Component
public class Quartz{

    private static String LOC;
    private static String LOC2;
    private static String SPEED;

    @Value("${path.location}")
    private void setLoc(String privateLoc) {
        Quartz.LOC = privateLoc;
    }

    @Value("${path.location2}")
    private void setLoc2(String privateLoc2) {
        Quartz.LOC2 = privateLoc2;
    }

    @Value("${test.speed}")
    private void setSpeed(String privateSpeed) {
        Quartz.SPEED = privateSpeed;
    }



    public static void scheduleJob(UserDocument doc, Browser browser) {
        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler scheduler = sf.getScheduler();

            JobKey jobKey = new JobKey(doc.getId().toString(), doc.getId().toString());
            if(doc.getCron().equals(""))
            {
                scheduler.deleteJob(jobKey);
            }
            else
            {
                scheduler.deleteJob(jobKey); //delete old trigger, if any, and the corresponding user information

                JobDetail job = newJob(QuartzJob.class)
                        .withIdentity(doc.getId().toString(),doc.getId().toString())
                        .build();

                Trigger trigger = TriggerBuilder
                        .newTrigger()
                        .withIdentity(doc.getId().toString(), doc.getId().toString())
                        .withSchedule(
                                CronScheduleBuilder.cronSchedule(doc.getCron()))
                        .build();

                job.getJobDataMap().put("docObject", doc);
                job.getJobDataMap().put("browserObject", browser);

                //Listener attached to jobKey
                scheduler.getListenerManager().addJobListener(
                        new QuartzListener(), KeyMatcher.keyEquals(jobKey)
                );


                scheduler.start();
                scheduler.scheduleJob(job, trigger);

            }


        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }


    public static void runMethod(UserDocument document,Browser browser) throws IOException, ParseException {

        String tempName = FilenameUtils.removeExtension(document.getName());
        String timestamp = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());

        if (tempName.equals(document.getName())){
            tempName += "Result";
        }
        tempName += "-" + timestamp;

        System.out.println(document);
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd "+LOC2+" && " +
                "java -jar selenese-runner.jar " + SPEED + browser.getWebdriver() +
                " --screenshot-on-fail " + LOC2 + "\\Files\\"+ document.getId() + "\\" + tempName +
                " --html-result "+ LOC2 + "\\Files\\" + document.getId() +"\\"+ tempName
                + " " + LOC2 + "\\Files\\"+ document.getId() +"\\"+ document.getName());

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

        File dir = new File(LOC + document.getId() +"/"+ tempName + "/");
        boolean checkFail = true;
        for (File file : dir.listFiles()) {
            if (file.getName().endsWith((".png"))) {
                checkFail = false;
            }

        }
        if(dir.listFiles().length < 2){
            checkFail = false;
        }
        System.out.println(checkFail);
        boolean isEmpty = true;
        if(dir.listFiles().length == 0){
            isEmpty = false; //empty means something is wrong with code. Look at the bloody code
        }

        System.out.println(isEmpty);
            try {
                Email.generateAndSendEmail(document,timestamp,checkFail,isEmpty);
            } catch (MessagingException e) {
                e.printStackTrace();
            }



    }

    public static void runTestMethod(Browser browser) throws IOException {

        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd "+LOC2+" && " +
                "java -jar selenese-runner.jar" + browser.getWebdriver() +
                " --screenshot-all " + LOC2 + "\\Files\\testScript\\ "  +
                LOC2 + "\\Files\\testScript.html");

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
    }


}
