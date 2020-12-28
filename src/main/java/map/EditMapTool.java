package map;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static centre.EnvironmentVariable.*;

public class EditMapTool extends Map implements MouseListener, KeyListener {
    private boolean isDesign = false;
    private boolean changePositionMap = false;
    private int positionMapMeshX = 0, positionMapMeshY = 0;

    private int xSetTile = 0, ySetTile = 0;

    public EditMapTool() {
        super();
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
        super.drawMap(g);
        if (isDesign) {
            drawDesign(g);
        }
    }

    public void update() {
        if (changePositionMap) {
            positionMapMeshX = 0;
            positionMapMeshY = 0;
        } else {
            positionMapMeshX = WIDTH - NUMBER_SPRITE_WIDTH * (PIXEL_DESIGN + 1);
            positionMapMeshY = HEIGHT - NUMBER_SPRITE_HEIGHT * (PIXEL_DESIGN + 4);
        }
    }

    public void drawDesign(Graphics g) {
        g.drawImage(mapImage, positionMapMeshX, positionMapMeshY, NUMBER_SPRITE_X * PIXEL_DESIGN, NUMBER_SPRITE_Y * PIXEL_DESIGN, null);
        for (int i = 0; i < NUMBER_SPRITE_X; i++)
            for (int j = 0; j < NUMBER_SPRITE_Y; j++)
                g.drawRect(positionMapMeshX + i * PIXEL_DESIGN, positionMapMeshY + j * PIXEL_DESIGN, PIXEL_DESIGN, PIXEL_DESIGN);
    }

    public Image getImage(int index) {
        return mapImageDetached[index / 100][index % 100];
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // set Tile Type
        int x = (e.getX() - positionMapMeshX) / PIXEL_DESIGN;
        int y = (e.getY() - positionMapMeshY) / PIXEL_DESIGN;

        if (x < NUMBER_SPRITE_X && x >= 0 && y < NUMBER_SPRITE_Y && y >= 0) {
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
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        switch (k) {
            case KeyEvent.VK_E -> isDesign = !isDesign;
            case KeyEvent.VK_C -> {
                changePositionMap = !changePositionMap;
                update();
            }
            case KeyEvent.VK_R -> saveMap(fileSaveDataMap);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


}
