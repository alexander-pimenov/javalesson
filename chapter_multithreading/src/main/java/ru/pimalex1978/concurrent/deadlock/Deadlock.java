package ru.pimalex1978.concurrent.deadlock;

public class Deadlock {
    static class Friend {
        private final String name;

        Friend(String name) {
            this.name = name;
        }

        String getName() {
            return this.name;
        }

        synchronized void bow(Friend bower) {
            System.out.format("%s: %s" + "  has bowed to me!%n", this.name, bower.getName());
            bower.bowBack(this);
        }

        synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s"
                            + " has bowed back to me!%n",
                    this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alphonse =
                new Friend("Alphonse");
        final Friend gaston =
                new Friend("Gaston");
        new Thread(new Runnable() {
            @Override
            public void run() {
                 System.out.println("Thread 1"); //
                alphonse.bow(gaston);
//                 System.out.println("Thread 1: gaston bowed to alphonse"); //
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                  System.out.println("Thread 2"); //
                gaston.bow(alphonse);
                  System.out.println("Thread 2: gaston waiting alphonse bowed"); //
            }
        }).start();
    }
}
