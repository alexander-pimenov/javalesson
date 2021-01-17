package ru.pimalex1978.concurrent.pools.example1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MainApp {
    class Job implements Runnable {
        private int jobId;

        public Job(int jobId) {
            this.jobId = jobId;
        }

        @Override
        public void run() {
            try {
                System.out.println(this + Thread.currentThread().getName());
                Thread.sleep(100);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Job{"
                    + "jobId=" + jobId
                    + '}';
        }
    }

    @Test
    public void whenJobsAddedThenExecuteByThreadPool() {
        ThreadPool pool = new ThreadPool();
        List<Runnable> jobs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            jobs.add(new Job(i));
        }
        try {
            for (Runnable val : jobs) {
                pool.work(val);
            }
            pool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

