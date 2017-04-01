package SnakeGUI;

import API.GameManager;
import SnakeLogic.Board;
import SnakeLogic.Position;
import SnakeLogic.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
   private static int SIZE=10;

   @FXML
   private ResourceBundle resources;
   @FXML
   private Pane rootPane;
   @FXML
   private ImageView bg_Imageview;

   @FXML
   private URL location;

   @FXML
   private Pane startPane;

   @FXML
   private Pane op_pane;
   @FXML
   private Pane runPane;
   @FXML
   private Canvas canvas;
   @FXML
   private ImageView mainbg_Imageview;
   @FXML
   private TextArea promptTxtarea;

   @FXML
   private Button start_btn;
   @FXML
   private Label score_lbl;

   @FXML
   private Button stop_btn;

   private Position p=new Position(30,30);
   private Snake snake;
   //private Food food=new Food(38,30);
   private Board board;
   GraphicsContext context;
  // GameManager gm;
  KeyEvent event;
   @FXML
   void invisible_startPane(ActionEvent event) {
      context=canvas.getGraphicsContext2D();
     // gm=new GameManager(board,context,this);
      startPane.setVisible(false);
      bg_Imageview.setVisible(false);
      mainbg_Imageview.setImage(new Image("resources/bg3.png"));
      mainbg_Imageview.setVisible(true);
      op_pane.toFront();
      op_pane.setVisible(true);
      snake=new Snake(Color.GREEN,1,p);
      board=new Board(760,600,SIZE,snake);
      canvas.toFront();
      rootPane.setFocusTraversable(true);

      Timeline timeline = new Timeline();
      timeline.setCycleCount(Timeline.INDEFINITE);
      timeline.getKeyFrames().add(
              new KeyFrame(Duration.millis(1000),
                      new EventHandler<ActionEvent>() {
                         // KeyFrame event handler
                         public void handle(ActionEvent event) {
                            //if the snake is dead or eat a food
                           // if (board.updateState()) {

                               paint(board,context);
                               board.updateState();
                               //apple.move(snake);
                           // }

                         }
                      }));
      timeline.playFromStart();
   }
   private void reset() {
       Position p=new Position(30,30);
       Snake snake=new Snake(Color.GREEN,1,p);
       Board board=new Board(760,600,SIZE,snake);
    //  gm = new GameManager(board,context,this);
      paint(board, context);
   }
   @FXML
   public KeyEvent keyPressedEvent(KeyEvent event) {

      System.out.print(event.getCode().toString());
     switch (event.getCode()) {
        case UP:
           board.getSnake().changeDirection(Direction.UP);

           break;
        case DOWN:
           board.getSnake().changeDirection(Direction.DOWN);

           break;
        case LEFT:
           board.getSnake().changeDirection(Direction.LEFT);

           break;
        case RIGHT:
           board.getSnake().changeDirection(Direction.RIGHT);

           break;
     }
     // paint(board,context);
     // gm.setKeyPressed();\
      return event;
   }

   @FXML
   void pauseGame(ActionEvent event) {
      String s;
      if("Stop".equals(stop_btn.getText())){
         stop_btn.setText("Continue");
         //todo: pause the game
      }
      else {
         stop_btn.setText("Stop");

         //todo:continue the game.
      }
   }

   public void paint(Board board, GraphicsContext gc) {
      gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
      // paint the Food
      gc.setFill(board.getFood().getColor());
      paintPoint(board.getFood().getPosition(), gc);

      // paint the snake
      snake = board.getSnake();
      gc.setFill(snake.getColor());
      snake.getSnakebody().forEach(p -> paintPoint(p, gc));

      //start again
     /* if (!snake.getAlive()) {
         gc.setFill(Snake.DEAD);
         paintPoint(snake.getHead(), gc);
      }
     */
     //pause
      if (!snake.getAlive()) {
         painfeedbake("your snake is dead!!!.");
         //todo: pause the game
      }

      // The score
      score_lbl.setText(""+1 * snake.getLength());

   }
   //画一个点
   private  void paintPoint(Position p, GraphicsContext gc) {
      gc.fillRect(p.getRow() * SIZE, p.getCol() * SIZE, SIZE, SIZE);
   }
   //返回重玩提示语
   public void painfeedbake(String s){
      promptTxtarea.setText(s);
   }



   @FXML
   void initialize() {
      assert mainbg_Imageview != null : "fx:id=\"mainbg_Imageview\" was not injected: check your FXML file 'sample.fxml'.";
      assert startPane != null : "fx:id=\"startPane\" was not injected: check your FXML file 'sample.fxml'.";
      assert bg_Imageview != null : "fx:id=\"bg_Imageview\" was not injected: check your FXML file 'sample.fxml'.";
      assert start_btn != null : "fx:id=\"start_btn\" was not injected: check your FXML file 'sample.fxml'.";
      assert op_pane != null : "fx:id=\"op_pane\" was not injected: check your FXML file 'sample.fxml'.";
      assert score_lbl != null : "fx:id=\"score_lbl\" was not injected: check your FXML file 'sample.fxml'.";
      assert stop_btn != null : "fx:id=\"stop_btn\" was not injected: check your FXML file 'sample.fxml'.";

   }
}
