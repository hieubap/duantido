package centre.controller;

import centre.EnvironmentVariable;

public class ThreadGame extends Thread {
    public final int TIME_PROCESS = EnvironmentVariable.DELAYPROCESS;
    private MainController mainController;

    public ThreadGame(MainController mainController) {
        this.mainController = mainController;
        this.start();
    }

    @Override
    public void run() {
//        Kernel;

        while (true) {
            long begin = System.currentTimeMillis();
            mainController.update();
            long endUpdate = System.currentTimeMillis();
            mainController.repaint();
            long endDraw = System.currentTimeMillis();

            long timeUpdate = endUpdate - begin;
            long timeDraw = endDraw - endUpdate;
            long totalTime = endDraw - begin;

            if (TIME_PROCESS - totalTime < 0) {
                System.out.println("time out, total time = " + totalTime);
            } else {
                try {
                    sleep(TIME_PROCESS - timeDraw);
                    System.out.println("start = " + endUpdate + " end = " + endDraw + " timeUpdate = " + timeUpdate + " timeDraw = " + timeDraw);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    continue;
                }
            }

        }
    }
}