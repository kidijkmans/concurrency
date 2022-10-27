package kd.threads;

import kd.semaphores.*;

/*
 * This class is repsonsible for creating a thread that prints the letter "z"
 */
public class ThreadZ extends Thread {

    BinarySemaphore y;
    BinarySemaphore z;
    Semaphore sumYZ; // counting semaphore

    public ThreadZ(BinarySemaphore y, BinarySemaphore z, Semaphore sumYZ) {
        this.y = y;
        this.z = z;
        this.sumYZ = sumYZ;
    }

    /**
     * Run method containing code which will be executed by the thread.
     */
    public void run() {
        while (true) {

            double random = Math.random() * 1000;
            try {
                sleep((long) random); // vary output sequence

                y.P(); // tell Y to wait

                sumYZ.P(); // both Y and Z have to wait until W has notified counting semaphore

                System.out.println('z'); // print z

                z.V(); // notify binary semaphore that Z has been printed

                sleep(10); // interleave

            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() +
                        " was interrupted out of sleep");
            }
        }
    }
}