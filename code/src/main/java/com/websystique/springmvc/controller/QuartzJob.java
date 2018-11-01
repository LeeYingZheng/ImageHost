package com.websystique.springmvc.controller;

import com.websystique.springmvc.model.Browser;
import com.websystique.springmvc.model.UserDocument;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by admin on 25/2/2016.
 */

public class QuartzJob implements Job{

    public void execute(JobExecutionContext cntxt) throws JobExecutionException {

        try {
            //insert the code here that needs to be performed at the intervals scheduled using CRON trigger
            //like making a Webservice call or running a report etc.
            System.out.println("Running the code in the execute method");

            JobDataMap jdMap = cntxt.getJobDetail().getJobDataMap();
            UserDocument doc = (UserDocument) jdMap.get("docObject");
            Browser browser = (Browser) jdMap.get("browserObject");

            Quartz.runMethod(doc,browser);

        }
        catch (Exception ex) {
            System.out.println(
                    "Exception occured in execute method " +ex);
        }

    }


}
