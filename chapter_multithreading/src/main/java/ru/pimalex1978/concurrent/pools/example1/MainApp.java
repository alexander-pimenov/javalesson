package ru.pimalex1978.concurrent.pools.example1;

import java.util.ArrayList;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
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

    static class Job implements Runnable {
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
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Job{"
                    + "jobId=" + jobId
                    + '}';
        }
    }
}