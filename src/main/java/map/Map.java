package map;

import centre.Camera;
import centre.EnvironmentVariable;
import centre.ImageManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static centre.EnvironmentVariable.NUMBER_SPRITE_HEIGHT;
import static centre.EnvironmentVariable.NUMBER_SPRITE_WIDTH;

public class Map {
    protected final int PIXEL = EnvironmentVariable.PIXEL;
    protected final int NUMBER_SPRITE_X = NUMBER_SPRITE_WIDTH;
    protected final int NUMBER_SPRITE_Y = NUMBER_SPRITE_HEIGHT;

    protected final BufferedImage mapImage;
    protected final Image[][] mapImageDetached;
    protected final File fileSaveDataMap;

    protected int numberTileX, numberTileY;
    protected int[][] dataMap;
    protected boolean[][] isMove;
    protected Camera camera;

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Map(Camera camera) {
        this();
        this.camera = camera;
    }

    protected Map() { // map = null
        fileSaveDataMap = new File(EnvironmentVariable.PATHRESOUREASSET + "/dataMap.txt");

        mapImage = ImageManager.mapImage;


        System.out.println("width: " + (mapImage != null ? mapImage.getWidth() : "null") +
                "; height: " + (mapImage != null ? mapImage.getHeight() : "null"));

        mapImageDetached = new BufferedImage[NUMBER_SPRITE_X][NUMBER_SPRITE_Y];

        for (int i = 0; i < NUMBER_SPRITE_X; i++)
            for (int j = 0; j < NUMBER_SPRITE_Y; j++)
                mapImageDetached[i][j] = mapImage != null ?
                        mapImage.getSubimage(i * PIXEL, j * PIXEL, PIXEL, PIXEL) : null;

        loadMapFromData();
    }

    public void loadMapFromData() {
        try {
            int i = 0;
            Scanner sc = new Scanner(fileSaveDataMap);

            String line = sc.nextLine();

            while (sc.hasNextLine()) {
                if (line.startsWith("// size:")) {
                    line = sc.nextLine();
                    String[] split = line.split(",");

                    System.out.println("width:" + split[0] + "; height:" + split[1]);

                    numberTileX = Integer.parseInt(split[0]);
                    numberTileY = Integer.parseInt(split[1]);

                    dataMap = new int[numberTileX][numberTileY];
                    isMove = new boolean[numberTileX][numberTileY];

                    line = sc.nextLine();
                    continue;
                }

                String[] split = line.split("-");

                for (int j = 0; j < numberTileX; j++) {
//                    dataMap[j][i] = Integer.parseInt(split[j]);
                    dataMap[j][i] = Integer.parseInt(split[j]);
                    isMove[j][i] = dataMap[j][i] == 101 || dataMap[j][i] == 602;
                }

                line = sc.nextLine();
                i++;
            }

            sc.close();
        } catch (IOException | NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void saveMap(File filename) {
        try {
            System.out.println("run save map");
            FileWriter fw = new FileWriter(filename);

            fw.write("// size: \n");
            fw.write(numberTileX + "," + numberTileY + "\n");

            for (int i = 0; i < numberTileX; i++) {
                for (int j = 0; j < numberTileY; j++) {
                    fw.write(dataMap[j][i] + "-");
                }
                fw.write("\n");
            }
            fw.write("// end");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawMap(Graphics g) {
        try {
            for (int i = camera.positionY / PIXEL; i < camera.positionY / PIXEL + camera.height / PIXEL + 2; i++)
                for (int j = camera.positionX / PIXEL; j < camera.positionX / PIXEL + camera.width / PIXEL + 2; j++)
                    if (i > -1 && j > -1 && j < numberTileX && i < numberTileY)
                        g.drawImage(getImage(dataMap[j][i]), j * PIXEL - camera.positionX, i * PIXEL - camera.positionY, PIXEL, PIXEL, null);
        } catch (ArrayIndexOutOfBoundsException ai) {
            ai.printStackTrace();
        }
    }

    public Image getImage(int index) {
        return mapImageDetached[index / 100][index % 100];
    }

    public boolean canMove(int x, int y) {
        try {
            if (x % PIXEL == 0 && y % PIXEL == 0)
                return isMove[x / PIXEL][y / PIXEL];
            else {
                return isMove[x / PIXEL][y / PIXEL]
                        && isMove[x / PIXEL + 1][y / PIXEL]
                        && isMove[x / PIXEL][y / PIXEL + 1]
                        && isMove[x / PIXEL + 1][y / PIXEL + 1];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

}
