package map;

import Primary.Camera;
import Primary.Picture;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Map extends MouseAdapter implements KeyListener {
    private final int PIXEL = 64;
    private final int PIXEL_DESIGN = 20;
    private final int WIDTH_MAP_SPRITE = 24;
    private final int HEIGHT_MAP_SPRITE = 10;
    private BufferedImage bufferedImage;
    private Image[][] tileMapImage;
    private File fileSave;
    private boolean isDesign = true;

    private int mapWidth, mapHeight;
    public int[][] dataMap;
    private Camera camera;
    private Rectangle[][] rectangles;// design
    private int xTileSet = 0, yTileSet = 0;

    public static void main(String[] args) {
        Map map = new Map();
    }

    public Map() {
        fileSave = new File("D:/WorkspaceEclipse/TANK/src/main/java/asset/savemap.txt");

        bufferedImage = Picture.loadImage("/tilemap.png");
        System.out.println("width: " + bufferedImage.getWidth() + " ; height: " + bufferedImage.getHeight());
        tileMapImage = new BufferedImage[WIDTH_MAP_SPRITE][HEIGHT_MAP_SPRITE];

        for (int i = 0; i < WIDTH_MAP_SPRITE; i++)
            for (int j = 0; j < HEIGHT_MAP_SPRITE; j++)
                tileMapImage[i][j] = bufferedImage.getSubimage(i * PIXEL, j * PIXEL, PIXEL, PIXEL);

        rectangles = new Rectangle[WIDTH_MAP_SPRITE][HEIGHT_MAP_SPRITE];

        for (int i = 0; i < WIDTH_MAP_SPRITE; i++)
            for (int j = 0; j < HEIGHT_MAP_SPRITE; j++)
                rectangles[i][j] = new Rectangle(i * PIXEL_DESIGN, j * PIXEL_DESIGN, PIXEL_DESIGN, PIXEL_DESIGN);

        loadMapFromData();
    }

    public Map(Camera camera) {
        this();
        this.camera = camera;
    }

    public void loadMapFromData() {
        try {
            int i = 0;
            Scanner sc = new Scanner(fileSave);

            String line = sc.nextLine();

            while (sc.hasNextLine()) {
                if (line.startsWith("// size:")) {
                    line = sc.nextLine();
                    String[] split = line.split(",");

                    System.out.println("width:" + split[0] + "; height:" + split[1]);

                    mapWidth = Integer.parseInt(split[0]);
                    mapHeight = Integer.parseInt(split[1]);

                    dataMap = new int[mapWidth][mapHeight];

                    line = sc.nextLine();
                    continue;
                }

                String[] split = line.split("-");

                for (int j = 0; j < mapWidth; j++) {
//                    dataMap[j][i] = Integer.parseInt(split[j]);
                    dataMap[j][i] = Integer.parseInt(split[j]);

                    System.out.print(dataMap[j][i] + "-");
                }
                System.out.println();

                line = sc.nextLine();
                i++;
            }

            sc.close();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void saveMap(File filename) {
        try {
            System.out.println("run savemap");
            FileWriter fw = new FileWriter(filename);

            fw.write("// size: \n");
            fw.write(mapWidth + "," + mapHeight + "\n");

            for (int i = 0; i < mapWidth; i++) {
                for (int j = 0; j < mapHeight; j++) {
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
        int ii = 1, jj = 1;
//        if (camera.positionX >= mapWidth * PIXEL - camera.width + 10)
//            jj = 0;
//        if (camera.positionY >= mapHeight * PIXEL - camera.height + 20)
//            ii = 0;

        try {
            for (int i = camera.positionY / PIXEL; i < camera.positionY / PIXEL + camera.height / PIXEL + ii; i++)
                for (int j = camera.positionX / PIXEL; j < camera.positionX / PIXEL + camera.width / PIXEL + jj; j++)
                    if (i > -1 && j > -1 && j<mapWidth && i< mapHeight)
                        g.drawImage(getImage(dataMap[j][i]), j * PIXEL - camera.positionX, i * PIXEL - camera.positionY, PIXEL, PIXEL, null);
        } catch (ArrayIndexOutOfBoundsException ai) {
            ai.printStackTrace();
//            System.out.println("error");
        }
        if (isDesign) {
            drawDesign(g);
        }
    }

    public void drawDesign(Graphics g) {
        g.drawImage(bufferedImage,0,0,WIDTH_MAP_SPRITE*PIXEL_DESIGN,HEIGHT_MAP_SPRITE*PIXEL_DESIGN,null);
        for (int i = 0;i<WIDTH_MAP_SPRITE;i++)
            for (int j=0;j<HEIGHT_MAP_SPRITE;j++)
                g.drawRect(i*PIXEL_DESIGN,j*PIXEL_DESIGN,PIXEL_DESIGN,PIXEL_DESIGN);
    }

    public Image getImage(int index) {
        return tileMapImage[index / 100][index % 100];
    }

//    public void renderDesign() {
//            for(int i=0;i<10;i++)
//                for(int j=0;j<24;j++)
//                {
//                    tiletype[j+i*24].setRect(camera.x+j*16, camera.y+i*16, 1, 1);
//                    renderSprite(sprits.getsprite(j, i), camera.x+j*16, camera.y+i*16, 1, 1);
//                }
//    }


    @Override
    public void mousePressed(MouseEvent e) {
        // set Tile Type
        int x = e.getX() / PIXEL_DESIGN;
        int y = e.getY() / PIXEL_DESIGN;

        if (x < WIDTH_MAP_SPRITE && y < HEIGHT_MAP_SPRITE) {
            System.out.println("set tile set");
            xTileSet = x;
            yTileSet = y;
            return;
        }

        // set map
        x = (e.getX() + camera.positionX) / PIXEL;
        y = (e.getY() + camera.positionY) / PIXEL;
        if (isDesign) {
            System.out.println("set map");
            dataMap[x][y] = xTileSet*100+yTileSet;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        switch (k) {
            case KeyEvent.VK_D: {
                isDesign = !isDesign;
                break;
            }
            case KeyEvent.VK_S: {
                saveMap(fileSave);
                break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
