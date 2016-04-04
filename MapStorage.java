package sample;

import javafx.scene.image.ImageView;

import java.util.ArrayList;


public class MapStorage {
    private SpriteLoadder textureLoad;
    private static final int blockSize = 32;
    private static final int spriteGroundX = 2;
    private static final int spriteGroundY = 0;
    private static final int spriteWaterX = 15;
    private static final int spriteWaterY = 12;
    private static final int spriteBrickX = 7;
    private static final int spriteBrickY = 0;
    private static final int spriteBlankX = 8;
    private static final int spriteBlankY = 10;
    private static final int spriteGrassX = 3;
    private static final int spriteGrassY = 0;
    private int layerLenght;
    private int firstLayerX;
    private int secondLayerX;
    private int thirdLayerX;
    private int fourthLayerX;
    private int fifthLayerX;
    private int globalLn;
    private int firstLayerY;
    private int secondLayerY;
    private int thirdLayerY;
    private int fourthLayerY;
    private int fifthLayerY;
    private int waterTime;
    private boolean waterNow;
    private int globalWaterTime;

    public MapStorage() {
        textureLoad = new SpriteLoadder();
    }

    public void createConstant(int gameResX, int gameResY) {
        int screenResX;
        int screenResY;
        screenResX = gameResX;
        screenResY = gameResY - 29;
        layerLenght = (screenResX / 32) * 4;
        firstLayerX = layerLenght;
        secondLayerX = firstLayerX + layerLenght;
        thirdLayerX = secondLayerX + layerLenght;
        fourthLayerX = thirdLayerX + layerLenght;
        fifthLayerX = fourthLayerX + layerLenght;
        globalLn = layerLenght * 5;
        firstLayerY = screenResY - 32;
        secondLayerY = screenResY - 32 * 2;
        thirdLayerY = screenResY - 32 * 3;
        fourthLayerY = screenResY - 32 * 4;
        fifthLayerY = screenResY - 32 * 7;
        waterTime = 0;
        waterNow = false;
        globalWaterTime = 0;
    }


    public ArrayList<ImageView> map() {
        ArrayList<ImageView> mapReturn = new ArrayList<>();
        for (int i = 0; i < globalLn; i = i + 1) {
            if (i < firstLayerX) {
                mapReturn.add(textureLoad.getLoadMapText(spriteGroundX, spriteGroundY));
                mapReturn.get(i).setX(i * blockSize);
                mapReturn.get(i).setY(firstLayerY);
                mapReturn.get(i).setId("1");
            }
            if ((i >= firstLayerX) && (i < secondLayerX)) {
                if (waterNow) {
                    mapReturn.add(textureLoad.getLoadMapText(spriteWaterX, spriteWaterY));
                    mapReturn.get(i).setX((i - firstLayerX) * blockSize);
                    mapReturn.get(i).setY(secondLayerY);
                    if (waterTime >= 8) {
                        waterTime = 0;
                        waterNow = false;
                    } else
                        waterTime++;
                } else {
                    if (((Math.random() + 17) < (Math.random() + 17)) && (waterTime > 4) && (
                        waterTime < 8) && waterNow == false) {
                        mapReturn.add(textureLoad.getLoadMapText(spriteWaterX, spriteWaterY));
                        mapReturn.get(i).setX((i - firstLayerX) * blockSize);
                        mapReturn.get(i).setY(secondLayerY);
                        mapReturn.get(i).setId("2");
                        waterNow = true;
                        if (waterTime >= 8)
                            waterTime = 0;
                        else
                            waterTime++;
                    } else {
                        mapReturn.add(textureLoad.getLoadMapText(spriteGroundX, spriteGroundY));
                        mapReturn.get(i).setX((i - firstLayerX) * blockSize);
                        mapReturn.get(i).setY(secondLayerY);
                        mapReturn.get(i).setId("1");
                        if (waterTime >= 8)
                            waterTime = 0;
                        else
                            waterTime++;
                    }
                }
            }
            if (i == secondLayerX) {
                waterNow = false;
                waterTime = 0;
            }
            if ((i >= secondLayerX) && i < (thirdLayerX)) {
                if (waterNow) {
                    mapReturn.add(textureLoad.getLoadMapText(spriteWaterX, spriteWaterY));
                    mapReturn.get(i).setX((i - secondLayerX) * blockSize);
                    mapReturn.get(i).setY(thirdLayerY);
                    mapReturn.get(i).setId("3");
                    if (waterTime >= 15) {
                        waterNow = false;
                        waterTime = 0;
                    } else {
                        waterNow = true;
                        waterTime++;
                    }
                } else {
                    if ((Math.random() + 37 > Math.random() + 37) && (waterTime > 11) && (waterNow
                        == false)) {
                        mapReturn.add(textureLoad.getLoadMapText(spriteWaterX, spriteWaterY));
                        mapReturn.get(i).setX((i - secondLayerX) * blockSize);
                        mapReturn.get(i).setY(thirdLayerY);
                        mapReturn.get(i).setId("3");
                        waterNow = true;
                        waterTime++;
                    } else {
                        mapReturn.add(textureLoad.getLoadMapText(spriteGroundX, spriteGroundY));
                        mapReturn.get(i).setX((i - secondLayerX) * blockSize);
                        mapReturn.get(i).setY(thirdLayerY);
                        mapReturn.get(i).setId("1");
                        waterNow = false;
                        waterTime++;
                    }
                }
            }
            if (i == thirdLayerX) {
                waterNow = false;
                waterTime = 0;
                globalWaterTime = 0;
            }
            if ((i >= thirdLayerX) && (i < fourthLayerX)) {
                if (mapReturn.get(i - layerLenght).getId().contentEquals("3")) {
                    mapReturn.add(textureLoad.getLoadMapText(spriteWaterX, spriteWaterY));
                    mapReturn.get(i).setX((i - thirdLayerX) * blockSize);
                    mapReturn.get(i).setY(fourthLayerY);
                    mapReturn.get(i).setId("3");
                    waterTime++;
                    globalWaterTime++;
                } else {
                    if (waterNow) {
                        mapReturn.add(textureLoad.getLoadMapText(spriteWaterX, spriteWaterY));
                        mapReturn.get(i).setX((i - thirdLayerX) * blockSize);
                        mapReturn.get(i).setY(fourthLayerY);
                        mapReturn.get(i).setId("2");
                        if (waterTime < 9) {
                            waterTime++;
                            waterNow = true;
                        } else {
                            waterNow = false;
                            waterTime = 0;
                        }
                    } else {
                        if (mapReturn.get(i - layerLenght - 1).getId().contains("3")) {
                            waterNow = false;
                            waterTime = 0;
                        }
                        if ((Math.random() + 25 < Math.random() + 25) && (waterNow == false) && (
                            waterTime > 6)) {
                            mapReturn.add(textureLoad.getLoadMapText(spriteWaterX, spriteWaterY));
                            mapReturn.get(i).setX((i - thirdLayerX) * blockSize);
                            mapReturn.get(i).setY(fourthLayerY);
                            mapReturn.get(i).setId("2");
                            waterNow = true;
                            waterTime++;
                        } else {
                            mapReturn.add(textureLoad.getLoadMapText(spriteGrassX, spriteGrassY));
                            mapReturn.get(i).setX((i - thirdLayerX) * blockSize);
                            mapReturn.get(i).setY(fourthLayerY);
                            mapReturn.get(i).setId("1");
                            waterNow = false;
                            waterTime++;
                        }
                    }
                }
            }
            if ((i >= fourthLayerX) && (i < fifthLayerX)) {
                if (mapReturn.get(i - layerLenght * 2).getId().contains("3")) {
                    mapReturn.add(textureLoad.getLoadMapText(spriteBrickX, spriteBrickY));
                    mapReturn.get(i).setX((i - fourthLayerX) * blockSize);
                    mapReturn.get(i).setY(fifthLayerY);
                    mapReturn.get(i).setId("5");
                } else {
                    mapReturn.add(textureLoad.getLoadMapText(spriteBlankX, spriteBlankY));
                    mapReturn.get(i).setX((i - fourthLayerX) * blockSize);
                    mapReturn.get(i).setY(fifthLayerY);
                    mapReturn.get(i).setId("5");
                }
            }
        }
        return mapReturn;
    }
}
