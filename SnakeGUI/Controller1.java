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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller1 {
   private static int SIZE=10;

   @FXML
   private ResourceBundle resources;
   @FXML
   private URL location;
   @FXML
   private Pane rootPane;
   @FXML
   private ImageView bg_Imageview;
   @FXML
   private Pane startPane;

   @FXML
   private Pane op_pane;

   @FXML
   private Canvas canvas;

   @FXML
   private Text prompt_txt;

   @FXML
   private Button start_btn;
   @FXML
   private Label score_lbl;

   @FXML
   private Button stop_btn;
   @FXML
   private Button restart_btn;

   private Position p=new Position(30,30);
   private Snake snake;
   //private Food food=new Food(38,30);
   private Board board;
   GraphicsContext context;
   Timeline timeline;
   @FXML
   void invisible_startPane(ActionEvent event) {
      startPane.setVisible(false);
      Image image=new Image("resources/bg3.png");
      //op_pane.toFront();
      context=canvas.getGraphicsContext2D();
      op_pane.setVisible(true);
      canvas.toFront();
      canvas.setFocusTraversable(true);
      context.drawImage(image,0,0);
      run();
   }
   private void run() {
      Position p=new Position(30,30);
      snake=new Snake(Color.GREEN,1,p);
      board=new Board(760,600,SIZE,snake);

      timeline = new Timeline();
      timeline.setCycleCount(Timeline.INDEFINITE);
      timeline.getKeyFrames().add(
              new KeyFrame(Duration.millis(100),
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
   @FXML
   void keyPressedEvent(KeyEvent event) {
      System.out.print(" "+event.getCode().toString());
      Direction direction=board.getSnake().getDirection();
      switch (event.getCode()) {
         case UP:
            if (direction != Direction.DOWN)
            board.getSnake().changeDirection(Direction.UP);
            break;
         case DOWN:
            if (direction != Direction.UP)
            board.getSnake().changeDirection(Direction.DOWN);
            break;
         case LEFT:
            if (direction != Direction.RIGHT)
            board.getSnake().changeDirection(Direction.LEFT);
            break;
         case RIGHT:
            if (direction != Direction.LEFT)
               board.getSnake().changeDirection(Direction.RIGHT);
            break;
         default:
            board.getSnake().changeDirection(Direction.RIGHT);
            break;
      }
   }
   @FXML
   void handleMouseEntered(MouseEvent event) {
      rootPane.requestFocus();
   }

   @FXML
   void pauseGame(ActionEvent event) {
      String s;
      if("Stop".equals(stop_btn.getText())){
         stop_btn.setText("Continue");
         if (timeline != null) {
            timeline.pause();
         }
         //todo: pause the game
      }
      else {
         stop_btn.setText("Stop");
         if (timeline != null ) {
            timeline.play();
         }
         //todo:continue the game.
      }
   }
   @FXML
   void restartGame(ActionEvent event) {
      restart_btn.setDisable(true);
      stop_btn.setDisable(false);
      run();

   }
   public void paint(Board board, GraphicsContext gc) {
      gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
      //paint the bakeground
      Image image=new Image("resources/bg3.png");
      context.drawImage(image,0,0);

      // paint the Food
      gc.setFill(board.getFood().getColor());
      paintPoint(board.getFood().getPosition(), gc);

      // paint the snake
      snake = board.getSnake();
      gc.setFill(snake.getColor());
      snake.getSnakebody().forEach(p -> paintPoint(p, gc));

      //pause
      if (!snake.getAlive()) {
         painfeedbake("your snake is dead!!!.");
         timeline.stop();
         stop_btn.setDisable(true);
         restart_btn.setDisable(false);
         //todo: restart the game
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
      prompt_txt.setText(s);
   }

   @FXML
   void initialize() {

      assert startPane != null : "fx:id=\"startPane\" was not injected: check your FXML file 'sample.fxml'.";
      assert bg_Imageview != null : "fx:id=\"bg_Imageview\" was not injected: check your FXML file 'sample.fxml'.";
      assert start_btn != null : "fx:id=\"start_btn\" was not injected: check your FXML file 'sample.fxml'.";
      assert op_pane != null : "fx:id=\"op_pane\" was not injected: check your FXML file 'sample.fxml'.";
      assert score_lbl != null : "fx:id=\"score_lbl\" was not injected: check your FXML file 'sample.fxml'.";
      assert stop_btn != null : "fx:id=\"stop_btn\" was not injected: check your FXML file 'sample.fxml'.";
      assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'snake.fxml'.";

   }
}
