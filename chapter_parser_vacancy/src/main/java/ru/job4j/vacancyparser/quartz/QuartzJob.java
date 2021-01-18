package ru.job4j.vacancyparser.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ru.job4j.vacancyparser.database.DataBase;
import ru.job4j.vacancyparser.parser.HtmlParser;

public class QuartzJob implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(QuartzJob.class.getName());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOG.info("Parser is run");
        DataBase dataBase = new DataBase();
        dataBase.init();
        HtmlParser parser = new HtmlParser();
        parser.updateParse();
        dataBase.addVacancies(parser.getVacansySet());
    }
}
