package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Controller {

    private ArrayList<ImageView> mainMap;
    private Scene mainGame;
    private Pane rootPane;
    private MapStorage mapCreator;
    private int sizeX;
    private int sizeY;
    private AnimationTimer timer;
    private PerspectiveCamera gameCamera;
    private int directWalk;
    private boolean cameraGo;
    private static final int walkSpeed = 5;

    public Controller() {
        rootPane = new Pane();
        mainGame = new Scene(rootPane);
        mapCreator = new MapStorage();
        gameCamera = new PerspectiveCamera(false);
        gameCamera.setFieldOfView(350);
        mainGame.setCamera(gameCamera);
        directWalk = 0;
        cameraGo = false;
    }

    public void sizeSet(int height, int width) {
        sizeX = width;
        sizeY = height;
    }

    private void mapGenerator() {
        mapCreator.createConstant(sizeX, sizeY);
        mainMap = mapCreator.map();
        for (ImageView i : mainMap) {
            rootPane.getChildren().add(i);
        }
    }

    public void sceneController(Stage globalStage) {
        mapGenerator();
        globalStage.setScene(mainGame);
        mainGame.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.UP) {
                    directWalk = 1;
                } else if (event.getCode() == KeyCode.DOWN) {
                    directWalk = 2;
                } else if (event.getCode() == KeyCode.RIGHT) {
                    directWalk = 3;
                } else if (event.getCode() == KeyCode.LEFT) {
                    directWalk = 4;
                }
            }
        });
        mainGame.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent event) {
                //@formatter:off
                        if ((event.getCode() == KeyCode.UP)
                            || (event.getCode() == KeyCode.RIGHT)
                            || (event.getCode() == KeyCode.LEFT)
                            || (event.getCode() == KeyCode.DOWN)){
                            directWalk = 0;
                        }
                        //@formatter:on
            }
        });
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                switch (directWalk) {
                    case 0:
                        System.out.println("STOP");
                        break;
                    case 1:
                        cameraMove(directWalk);
                        break;
                    case 2:
                        //gameCamera.setVisible(true);
                        cameraMove(directWalk);
                        System.out.println("DOWN");
                        break;
                    case 3:
                        cameraMove(directWalk);
                        System.out.println("RIGHT");
                        break;
                    case 4:
                        cameraMove(directWalk);
                        break;
                }
            }
        };
        timer.start();
        globalStage.show();
    }

    private void cameraMove(int direction) {
        if (direction == 1) {
            gameCamera.setTranslateY(gameCamera.getTranslateY() + walkSpeed);
        } else if (direction == 2) {
            gameCamera.setTranslateY(gameCamera.getTranslateY() - walkSpeed);
        } else if (direction == 3) {
            gameCamera.setTranslateX(gameCamera.getTranslateX() + walkSpeed);
        } else if (direction == 4) {
            gameCamera.setTranslateX(gameCamera.getTranslateX() - walkSpeed);
        }
    }

}

