package map;

import origin.Camera;
import primary.EnvironmentConfig;
import origin.Picture;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static primary.EnvironmentConfig.*;

public class EditMapTool extends MouseAdapter implements KeyListener {
    private final int PIXEL = EnvironmentConfig.PIXEL;
    private final int PIXEL_DESIGN = EnvironmentConfig.PIXEL_DESIGN;
    private final int NUMBER_SPRITE_X = NUMBER_SPRITE_WIDTH;
    private final int NUMBER_SPRITE_Y = NUMBER_SPRITE_HEIGHT;

    private final BufferedImage mapImage;
    private final Image[][] mapImageDetached;
    private final File fileSaveDataMap;
    private boolean isDesign = true;

    private int numberTileX, numberTileY;
    private int[][] dataMap;
    private Camera camera;
    private int xSetTile = 0, ySetTile = 0;

    public EditMapTool(Camera camera) {
        this();
        this.camera = camera;
    }

    public EditMapTool() {
        fileSaveDataMap = new File("D:/WorkspaceEclipse/TANK/src/main/java/asset/dataMap.txt");

        mapImage = Picture.loadImage("/tilemap.png");
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

                    line = sc.nextLine();
                    continue;
                }

                String[] split = line.split("-");

                for (int j = 0; j < numberTileX; j++) {
//                    dataMap[j][i] = Integer.parseInt(split[j]);
                    dataMap[j][i] = Integer.parseInt(split[j]);

                    System.out.print(dataMap[j][i] + "-");
                }
                System.out.println();

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
        if (isDesign) {
            drawDesign(g);
        }
    }

    public void drawDesign(Graphics g) {
        g.drawImage(mapImage, 0, 0, NUMBER_SPRITE_X * PIXEL_DESIGN, NUMBER_SPRITE_Y * PIXEL_DESIGN, null);
        for (int i = 0; i < NUMBER_SPRITE_X; i++)
            for (int j = 0; j < NUMBER_SPRITE_Y; j++)
                g.drawRect(i * PIXEL_DESIGN, j * PIXEL_DESIGN, PIXEL_DESIGN, PIXEL_DESIGN);
    }

    public Image getImage(int index) {
        return mapImageDetached[index / 100][index % 100];
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // set Tile Type
        int x = e.getX() / PIXEL_DESIGN;
        int y = e.getY() / PIXEL_DESIGN;

        if (x < NUMBER_SPRITE_X && y < NUMBER_SPRITE_Y) {
            System.out.println("set tile set");
            xSetTile = x;
            ySetTile = y;
            return;
        }

        // set map
        x = (e.getX() + camera.positionX) / PIXEL;
        y = (e.getY() + camera.positionY) / PIXEL;
        if (isDesign) {
            System.out.println("set map");
            dataMap[x][y] = xSetTile * 100 + ySetTile;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        switch (k) {
            case KeyEvent.VK_D -> isDesign = !isDesign;
            case KeyEvent.VK_S -> saveMap(fileSaveDataMap);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
