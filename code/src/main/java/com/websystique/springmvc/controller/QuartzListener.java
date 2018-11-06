package com.websystique.springmvc.controller;

import com.websystique.springmvc.service.UserDocumentService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by admin on 25/2/2016.
 */


public class QuartzListener implements JobListener{
    @Autowired
    UserDocumentService userDocumentService;

    public static final String LISTENER_NAME = "Listener";

    public String getName() {
        return LISTENER_NAME; //must return a name
    }

    // Run this if job is about to be executed.
    public void jobToBeExecuted(JobExecutionContext context) {

        String jobName = context.getJobDetail().getKey().toString();
        System.out.println("jobToBeExecuted");
        System.out.println("Job : " + jobName + " is going to start...");

    }

    // No idea when will run this?
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("jobExecutionVetoed");
    }


    public void jobWasExecuted(JobExecutionContext context,
                               JobExecutionException jobException) {

        System.out.println("jobWasExecuted");

    }


}
