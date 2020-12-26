package origin.controller;

import origin.EnvironmentConfig;
import origin.controller.MainController;

public class ThreadGame extends Thread{
    private MainController mainController;

    public ThreadGame(MainController mainController) {
        this.mainController = mainController;
        this.start();
    }

    @Override
    public void run() {
        while(true){
            long startTime = System.currentTimeMillis();
            // update
            mainController.update();
            long endTime = System.currentTimeMillis();

            long timeComplete = endTime -startTime;

            try {
                sleep(EnvironmentConfig.DELAYPROCESS-timeComplete);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            // draw
            mainController.repaint();
        }

    }
}
