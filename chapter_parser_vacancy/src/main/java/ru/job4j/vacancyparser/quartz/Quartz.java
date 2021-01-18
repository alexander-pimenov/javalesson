package ru.job4j.vacancyparser.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class Quartz {
    private static final Logger LOG = LoggerFactory.getLogger(Quartz.class.getName());
//    private static final Logger LOG = LogManager.getLogger(Quartz.class.getName());


    public static void main(String[] args) {

        try (InputStream in = Quartz.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            String cronTime = config.getProperty("cron.time");

            // Запускаем Schedule Factory
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            // Извлекаем планировщик из schedule factory
            Scheduler scheduler = schedulerFactory.getScheduler();
            // Запускаем планировщик
            scheduler.start();

            //Запускаем JobDetail с именем задания и группой задания
            JobDetail parser = JobBuilder.newJob(QuartzJob.class)
                    .withIdentity("jobTask", "jobGroup")
                    .build();

            // Запускаем CronTrigger с его именем и именем группы
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity("jobTrigger", "jobGroup")
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronTime))
                    .forJob(parser)
                    .build();

            // Планируем задание с помощью JobDetail и Trigger
            scheduler.scheduleJob(parser, cronTrigger);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
