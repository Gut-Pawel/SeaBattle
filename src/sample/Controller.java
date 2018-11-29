package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //
    @FXML
    private GridPane mainContainer;

    private final int col = 9;
    private final int row = 9;
    private boolean rotation = false;
    private int shipLenght = 0;
    private int i2;
    private int j2;

    private int yourPoints = 0;
    private int enemyPoints = 0;

    private boolean[][] position = new boolean[9][9];
    private boolean[][] enemyPosition = new boolean[9][9];
    private boolean[][] enemyShots = new boolean[9][9];


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mainContainer.setStyle("-fx-background-color: darkslategrey");
        mainContainer.setVgap(4);
        mainContainer.setHgap(4);


        Button[][] buttons = new Button[9][9];
        Button[][] enemyButtons = new Button[9][9];

        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f,0.4f));

        Text t = new Text();
        t.setEffect(ds);
        t.setX(29);
        t.setY(100);
        t.setText("0/20");
        t.setFill(Color.GOLD);
        t.setFont(Font.font(null, FontWeight.BOLD, 40));

        Text t2 = new Text();
        t2.setEffect(ds);
        t2.setX(29);
        t2.setY(100);
        t2.setText("20/20");
        t2.setFill(Color.RED);
        t2.setFont(Font.font(null, FontWeight.BOLD, 40));

        mainContainer.add(t , 4 , 12 ,10,5 );
        mainContainer.add(t2,16,12,10,5);

//HANDLER ENTERED MOUSE && CLICKED MOUSE EVENTS (YOUR SIDE)

        EventHandler<MouseEvent> mouseEnteredHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Object sourceObjcet = event.getSource();
                Button sourceButton = (Button) sourceObjcet;
                MouseButton button = event.getButton();

                String buttonID = sourceButton.getId();

                i2 = (buttonID.charAt(7) - 48);
                j2 = (buttonID.charAt(8) - 48);


                try {

//4-LENGTH SHIP HORIZONTAL
                    if (shipLenght == 0 && !rotation && (i2 + 3) < row && j2 < col && i2 < 7 &&
                            !buttons[i2][j2].isDisable() && !buttons[i2 + 1][j2].isDisable() &&
                            !buttons[i2 + 2][j2].isDisable() && !buttons[i2 + 3][j2].isDisable()) {

                        sourceButton.setStyle("-fx-background-color: greenyellow");
                        buttons[i2 + 1][j2].setStyle(("-fx-background-color: greenyellow"));
                        buttons[i2 + 2][j2].setStyle(("-fx-background-color: greenyellow"));
                        buttons[i2 + 3][j2].setStyle(("-fx-background-color: greenyellow"));


                        if (button == MouseButton.SECONDARY && j2 < 6 ) {
                            rotation = !rotation;

                            //sourceButton.setStyle("-fx-background-color: aqua");
                            buttons[i2 + 1][j2].setStyle(("-fx-background-color: aqua"));
                            buttons[i2 + 2][j2].setStyle(("-fx-background-color: aqua"));
                            buttons[i2 + 3][j2].setStyle(("-fx-background-color: aqua"));
                            buttons[i2][j2 + 1].setStyle(("-fx-background-color: greenyellow"));
                            buttons[i2][j2 + 2].setStyle(("-fx-background-color: greenyellow"));
                            buttons[i2][j2 + 3].setStyle(("-fx-background-color: greenyellow"));

                        } else if (button == MouseButton.PRIMARY) {
                            buttons[i2][j2].setDisable(true);
                            buttons[i2 + 1][j2].setDisable(true);
                            buttons[i2 + 2][j2].setDisable(true);
                            buttons[i2 + 3][j2].setDisable(true);

                            shipLenght++;
                            System.out.println(shipLenght);

                            yourPoints +=4;
                            t.setText(yourPoints+"/20");

                        }

                    }
//4-LENGTH SHIP VERTICAL
                    else if (shipLenght == 0 && rotation && (j2 + 3) < col && i2 < row && j2 < 7 &&
                            !buttons[i2][j2].isDisable() && !buttons[i2][j2 + 1].isDisable() &&
                            !buttons[i2][j2 + 2].isDisable() && !buttons[i2][j2 + 3].isDisable()) {

                        sourceButton.setStyle("-fx-background-color: greenyellow");
                        buttons[i2][j2 + 1].setStyle(("-fx-background-color: greenyellow"));
                        buttons[i2][j2 + 2].setStyle(("-fx-background-color: greenyellow"));
                        buttons[i2][j2 + 3].setStyle(("-fx-background-color: greenyellow"));

                        if (button == MouseButton.SECONDARY && i2 < 6 ) {
                            rotation = !rotation;

                            //sourceButton.setStyle("-fx-background-color: aqua");
                            buttons[i2][j2 + 1].setStyle(("-fx-background-color: aqua"));
                            buttons[i2][j2 + 2].setStyle(("-fx-background-color: aqua"));
                            buttons[i2][j2 + 3].setStyle(("-fx-background-color: aqua"));
                            buttons[i2 + 1][j2].setStyle(("-fx-background-color: greenyellow"));
                            buttons[i2 + 2][j2].setStyle(("-fx-background-color: greenyellow"));
                            buttons[i2 + 3][j2].setStyle(("-fx-background-color: greenyellow"));


                        } else if (button == MouseButton.PRIMARY) {
                            buttons[i2][j2].setDisable(true);
                            buttons[i2][j2 + 1].setDisable(true);
                            buttons[i2][j2 + 2].setDisable(true);
                            buttons[i2][j2 + 3].setDisable(true);

                            shipLenght++;
                            System.out.println(shipLenght);
                            yourPoints+=4;
                            t.setText(yourPoints+"/20");

                        }


                    }
//3-LENGTH SHIP HORIZONTAL
                    else if (1 <= shipLenght && shipLenght <= 2 && !rotation && (i2 + 2) < row && j2 < col && i2 < 8 &&
                            !buttons[i2][j2].isDisable() && !buttons[i2 + 1][j2].isDisable() &&
                            !buttons[i2 + 2][j2].isDisable()) {

                        sourceButton.setStyle("-fx-background-color: greenyellow");
                        buttons[i2 + 1][j2].setStyle(("-fx-background-color: greenyellow"));
                        buttons[i2 + 2][j2].setStyle(("-fx-background-color: greenyellow"));

                        if (button == MouseButton.SECONDARY && j2 < 7 ) {
                            rotation = !rotation;

                            //sourceButton.setStyle("-fx-background-color: aqua");
                            buttons[i2 + 1][j2].setStyle(("-fx-background-color: aqua"));
                            buttons[i2 + 2][j2].setStyle(("-fx-background-color: aqua"));
                            buttons[i2][j2 + 1].setStyle(("-fx-background-color: greenyellow"));
                            buttons[i2][j2 + 2].setStyle(("-fx-background-color: greenyellow"));

                        } else if (button == MouseButton.PRIMARY) {
                            buttons[i2][j2].setDisable(true);
                            buttons[i2 + 1][j2].setDisable(true);
                            buttons[i2 + 2][j2].setDisable(true);

                            shipLenght++;
                            yourPoints+=3;
                            t.setText(yourPoints+"/20");

                        }

                    }
//3-LENGTH SHIP VERTICAL
                    else if (shipLenght >= 1 && shipLenght <= 2 && rotation && (j2 + 2) < col && i2 < row && j2 < 8 &&
                            !buttons[i2][j2].isDisable() && !buttons[i2][j2 + 1].isDisable() &&
                            !buttons[i2][j2 + 2].isDisable()) {

                        sourceButton.setStyle("-fx-background-color: greenyellow");
                        buttons[i2][j2 + 1].setStyle(("-fx-background-color: greenyellow"));
                        buttons[i2][j2 + 2].setStyle(("-fx-background-color: greenyellow"));


                        if (button == MouseButton.SECONDARY && i2 < 7 ) {
                            rotation = !rotation;

                            buttons[i2][j2 + 1].setStyle(("-fx-background-color: aqua"));
                            buttons[i2][j2 + 2].setStyle(("-fx-background-color: aqua"));
                            buttons[i2 + 1][j2].setStyle(("-fx-background-color: greenyellow"));
                            buttons[i2 + 2][j2].setStyle(("-fx-background-color: greenyellow"));

                        } else if (button == MouseButton.PRIMARY) {
                            buttons[i2][j2].setDisable(true);
                            buttons[i2][j2 + 1].setDisable(true);
                            buttons[i2][j2 + 2].setDisable(true);

                            shipLenght++;
                            System.out.println(shipLenght);
                            yourPoints+=3;
                            t.setText(yourPoints+"/20");

                        }

                    }
 //2-LENGTH SHIP HORINONTAL
                    else if (3 <= shipLenght && shipLenght <= 5 && !rotation && (i2 + 1) < row && j2 < col && i2 < 9 &&
                            !buttons[i2][j2].isDisable() && !buttons[i2 + 1][j2].isDisable()) {

                        sourceButton.setStyle("-fx-background-color: greenyellow");
                        buttons[i2 + 1][j2].setStyle(("-fx-background-color: greenyellow"));

                        if (button == MouseButton.SECONDARY && j2 < 8 ) {
                            rotation = !rotation;

                            buttons[i2 + 1][j2].setStyle(("-fx-background-color: aqua"));
                            buttons[i2][j2 + 1].setStyle(("-fx-background-color: greenyellow"));

                        } else if (button == MouseButton.PRIMARY) {
                            buttons[i2][j2].setDisable(true);
                            buttons[i2 + 1][j2].setDisable(true);

                            shipLenght++;
                            yourPoints+=2;
                            t.setText(yourPoints+"/20");

                        }

                    }
//2-LENGTH SHIP VERTICAL
                    else if (shipLenght >= 3 && shipLenght <= 5 && rotation && (j2 + 1) < col && i2 < row && j2 < 9 &&
                            !buttons[i2][j2].isDisable() && !buttons[i2][j2 + 1].isDisable()) {

                        sourceButton.setStyle("-fx-background-color: greenyellow");
                        buttons[i2][j2 + 1].setStyle(("-fx-background-color: greenyellow"));

                        if (button == MouseButton.SECONDARY && i2 < 8 ) {
                            rotation = !rotation;

                            buttons[i2][j2 + 1].setStyle(("-fx-background-color: aqua"));
                            buttons[i2 + 1][j2].setStyle(("-fx-background-color: greenyellow"));

                        } else if (button == MouseButton.PRIMARY) {
                            buttons[i2][j2].setDisable(true);
                            buttons[i2][j2 + 1].setDisable(true);

                            shipLenght++;
                            yourPoints+=2;
                            t.setText(yourPoints+"/20");

                        }

                    }
//1-LENTH SHIP
                    else if (6 <= shipLenght && shipLenght <= 9 && (i2) < row && j2 < col &&
                            !buttons[i2][j2].isDisable()) {

                        sourceButton.setStyle("-fx-background-color: greenyellow");

                        if (button == MouseButton.PRIMARY) {
                            buttons[i2][j2].setDisable(true);

                            shipLenght++;
                            yourPoints++;
                            t.setText(yourPoints+"/20");


                        }

                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    e.getStackTrace();
                }
            }
        };

//HANDLER EXITED MOUSE EVENTS ( YOURSIDE )

        EventHandler<MouseEvent> mouseExitedHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Object sourceObjcet = event.getSource();
                Button sourceButton = (Button) sourceObjcet;

                //BACK TO

                //4-LENGTH SHIP EXITED
                if (shipLenght == 0 && i2 < 7 && !rotation && (i2 + 3) < row) {

                    sourceButton.setStyle("-fx-background-color: aqua");
                    buttons[i2 + 1][j2].setStyle(("-fx-background-color: aqua"));
                    buttons[i2 + 2][j2].setStyle(("-fx-background-color: aqua"));
                    buttons[i2 + 3][j2].setStyle(("-fx-background-color: aqua"));

                }
                else if (shipLenght == 0 && j2 < 7 && rotation && (j2 + 3) < col) {

                    sourceButton.setStyle("-fx-background-color: aqua");
                    buttons[i2][j2 + 1].setStyle(("-fx-background-color: aqua"));
                    buttons[i2][j2 + 2].setStyle(("-fx-background-color: aqua"));
                    buttons[i2][j2 + 3].setStyle(("-fx-background-color: aqua"));

                }
                //3-LENGTH SHIP EXITED
                else if (shipLenght >= 1 && shipLenght <= 2 && i2 < 8 && !rotation && (i2 + 2) < row) {
                    sourceButton.setStyle("-fx-background-color: aqua");
                    buttons[i2 + 1][j2].setStyle(("-fx-background-color: aqua"));
                    buttons[i2 + 2][j2].setStyle(("-fx-background-color: aqua"));
                }
                else if (shipLenght >= 1 && shipLenght <= 2 && j2 < 8 && rotation && (j2 + 2) < col) {
                    sourceButton.setStyle("-fx-background-color: aqua");
                    buttons[i2][j2 + 1].setStyle(("-fx-background-color: aqua"));
                    buttons[i2][j2 + 2].setStyle(("-fx-background-color: aqua"));
                }
                //2-LENGTH SHIP EXITED
                else if (shipLenght >= 3 && shipLenght <= 5 && i2 < 9 && !rotation && (i2 + 1) < row) {
                    sourceButton.setStyle("-fx-background-color: aqua");
                    buttons[i2 + 1][j2].setStyle(("-fx-background-color: aqua"));
                }
                else if (shipLenght >= 3 && shipLenght <= 5 && j2 < 9 && rotation && (j2 + 1) < col) {
                    sourceButton.setStyle("-fx-background-color: aqua");
                    buttons[i2][j2 + 1].setStyle(("-fx-background-color: aqua"));
                }
                //1-LENGTH SHIP EXITED
                else if (shipLenght >= 6 && shipLenght <= 9 && i2 < row && j2 < 10) {
                    sourceButton.setStyle("-fx-background-color: aqua");
                }

                else if (shipLenght == 10) {
                    System.out.println("wejscie do petli");
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < col; j++) {
                            if (buttons[i][j].isDisable()) {
                                buttons[i][j].setStyle("-fx-background-color:olive");
                                position[i][j]=true;
                            } else {
                                buttons[i][j].setDisable(true);
                            }
                            enemyButtons[i][j].setDisable(false);
                            enemyButtons[i][j].setStyle("-fx-background-color:grey");

                        }
                        System.out.println();
                    }

                    Random rand = new Random();

                    int rowLength , colLength;

                    //CREATE RANDOM POSITION ENEMY SHIPS
                    for(int i=0;i<=9;i++) {
                        rotation = Math.random() < 0.5 ;

                        rowLength = rand.nextInt(9);
                        colLength = rand.nextInt(9);

                        //RAND 4-LENGTH ENEMY SHIP
                        if( i==0 && !rotation ) {

                            rowLength = rand.nextInt(6);
                            colLength = rand.nextInt(9);

                            enemyPosition[rowLength][colLength] = true;
                            enemyPosition[rowLength + 1][colLength] = true;
                            enemyPosition[rowLength + 2][colLength] = true;
                            enemyPosition[rowLength + 3][colLength] = true;

                            enemyPoints+=4;

                        }

                        else if( i==0 && rotation ) {

                            rowLength = rand.nextInt(9);
                            colLength = rand.nextInt(6);

                            enemyPosition[rowLength][colLength] = true;
                            enemyPosition[rowLength][colLength + 1] = true;
                            enemyPosition[rowLength][colLength + 2] = true;
                            enemyPosition[rowLength][colLength + 3] = true;

                            enemyPoints+=4;

                        }
                        //RAND 3-LENGTH ENEMY SHIP
                        else if( i < 3 ) {


                            for(;rowLength<10;) {
                                if(!rotation && rowLength < 7 && !enemyPosition[rowLength][colLength] && !enemyPosition[rowLength + 1][colLength]
                                        && !enemyPosition[rowLength+2][colLength] ) {

                                    enemyPosition[rowLength][colLength] = true;
                                    enemyPosition[rowLength + 1][colLength] = true;
                                    enemyPosition[rowLength + 2][colLength] = true;

                                    rowLength = 11;
                                    enemyPoints+=3;

                                }


                                else if(rotation && colLength < 7 && !enemyPosition[rowLength][colLength] && !enemyPosition[rowLength][colLength + 1] &&
                                        !enemyPosition[rowLength][colLength + 2] ) {

                                    enemyPosition[rowLength][colLength] = true;
                                    enemyPosition[rowLength][colLength + 1] = true;
                                    enemyPosition[rowLength][colLength + 2] = true;

                                    rowLength = 11;
                                    enemyPoints+=3;

                                }

                                else{

                                    rowLength = rand.nextInt(9);
                                    colLength = rand.nextInt(9);

                                }
                            }


                        }

                        //RAND 2 -LENGTH ENEMY SHIP
                        else if(i < 6) {
                            for(;rowLength < 12;) {
                                if(!rotation && rowLength < 8 && !enemyPosition[rowLength][colLength] && !enemyPosition[rowLength + 1][colLength] ) {

                                    enemyPosition[rowLength][colLength] = true;
                                    enemyPosition[rowLength + 1][colLength] = true;

                                    rowLength = 13;
                                    enemyPoints+=2;

                                }

                                else if(rotation && colLength < 8 && !enemyPosition[rowLength][colLength] && !enemyPosition[rowLength][colLength + 1] ) {

                                    enemyPosition[rowLength][colLength] = true;
                                    enemyPosition[rowLength][colLength + 1] = true;

                                    rowLength = 13;
                                    enemyPoints+=2;

                                }

                                else{

                                    rowLength = rand.nextInt(9);
                                    colLength = rand.nextInt(9);

                                }
                            }
                        }
                        //RAND 1-LENGTH ENEMY SHIP
                        else{
                            for(;rowLength < 14;) {
                                if(!enemyPosition[rowLength][colLength]) {

                                    enemyPosition[rowLength][colLength] = true;
                                    enemyPoints++;
                                    rowLength = 15;

                                }
                                else{

                                    rowLength = rand.nextInt(9);
                                    colLength = rand.nextInt(9);
                                }
                            }
                        }

                    }

                    shipLenght++;

                }

            }
        };

        //HANDLER ENTERED MOUSE && CLICKED MOUSE EVENTS (ENEMY SIDE)

        EventHandler<MouseEvent> enemySideEntered = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Object sourceObjcet = event.getSource();
                Button sourceButton = (Button) sourceObjcet;
                MouseButton button = event.getButton();

                sourceButton.setStyle("-fx-background-color:lightgrey ");

                String buttonID = sourceButton.getId();

                i2 = (buttonID.charAt(7) - 48);
                j2 = (buttonID.charAt(8) - 48);

                if(button == MouseButton.PRIMARY) {
                    if(enemyPosition[i2][j2] == true ) {
                        enemyButtons[i2][j2].setStyle("-fx-background-color:red");
                        enemyButtons[i2][j2].setDisable(true);
                        enemyPoints--;
                        if(enemyPoints==0) {
                            t.setText("WIiiiiiIN");
                            t.setFont(Font.font(null, FontWeight.BOLD, 100));
                            t2.setVisible(false);

                        }
                        t2.setText(enemyPoints+"/20");
                    }
                    else{
                        enemyButtons[i2][j2].setStyle("-fx-background-color:black");
                        enemyButtons[i2][j2].setDisable(true);
                    }

                    Random rand = new Random();

                    int i3 , j3;

                    for(;;) {
                        i3 = rand.nextInt(9);
                        j3 = rand.nextInt(9);

                        if(!enemyShots[i3][j3]) {
                            if(position[i3][j3]) {
                                buttons[i3][j3].setStyle("-fx-background-color:red");
                                enemyShots[i3][j3] = true;
                                yourPoints--;
                                t.setText(yourPoints+"/20");
                                if(yourPoints==0) {
                                    t.setFont(Font.font(null, FontWeight.BOLD, 100));
                                    t2.setVisible(false);
                                    t.setText("GAME OVER");
                                }
                                break;
                            }
                            else {
                                buttons[i3][j3].setStyle("-fx-background-color:black");
                                enemyShots[i3][j3] = true;
                                break;
                            }
                        }

                    }

                }
            }
        };

        EventHandler<MouseEvent> enemySideExited = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Object sourceObjcet = event.getSource();
                Button sourceButton = (Button) sourceObjcet;
                if(!sourceButton.isDisable()) {
                    sourceButton.setStyle("-fx-background-color:grey");
                }
            }
        };

        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {

                buttons[i][j] = new Button();
                buttons[i][j].setId("buttons" + i + j);

                buttons[i][j].setMinSize(24, 24);
                buttons[i][j].setStyle("-fx-background-color: aqua");
                buttons[i][j].addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEnteredHandler);
                buttons[i][j].addEventHandler(MouseEvent.MOUSE_EXITED, mouseExitedHandler);
                buttons[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEnteredHandler);

                mainContainer.add(buttons[i][j], i, j + 1);

            }



        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++) {

                enemyButtons[i][j] = new Button();
                enemyButtons[i][j].setId("buttons" + i + j);

                enemyButtons[i][j].setMinSize(24, 24);
                enemyButtons[i][j].setStyle("-fx-background-color: black");
                enemyButtons[i][j].setDisable(true);
                enemyButtons[i][j].addEventHandler(MouseEvent.MOUSE_ENTERED, enemySideEntered);
                enemyButtons[i][j].addEventHandler(MouseEvent.MOUSE_EXITED, enemySideExited);
                enemyButtons[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, enemySideEntered);
                mainContainer.add(enemyButtons[i][j], i + 15, j + 1);

            }

    }
}
