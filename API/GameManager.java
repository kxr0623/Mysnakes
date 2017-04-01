package API;

import SnakeGUI.Controller;
import SnakeLogic.Board;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

/**
 * Created by 52704 on 2017/3/29.
 */
public class GameManager implements Runnable{
   public static final int FRAME_RATE = 20;
   private Controller controller;
   private final Board board;
   private final GraphicsContext context;
   private float interval;
   private boolean paused;
   private boolean keyIsPressed;
   public GameManager(final Board board, final GraphicsContext context,Controller controller) {
      this.board = board;
      this.context = context;
      interval = 10000.0f / FRAME_RATE; // 1000 ms in a second
      paused = false;
      keyIsPressed = false;
      this. controller=controller;
   }

   public void run() {
      while (!paused) {
         // Time the update and paint calls
         float time = System.currentTimeMillis();

         paused= !board.updateState();//snake go outside the board:f
         controller.paint(board, context);

         if (!board.getSnake().getAlive()) {
            pause();
            controller.painfeedbake("your snake is dead!!!.");
            break;
         }

         time = System.currentTimeMillis() - time;

         // Adjust the timing correctly
         if (time < interval) {
            try {
               Thread.sleep((long) (interval - time));
            } catch (InterruptedException ignore) {
               System.err.println(ignore);
            }
         }
         keyIsPressed = false;
      }
   }

   public void stop() {
      paused = true;
   }

   public boolean isKeyPressed() {
      return keyIsPressed;
   }

   public void setKeyPressed() {
      keyIsPressed = true;
   }

   public void resume() {
      paused = false;
      //running=true;
      keyIsPressed=false;
   }

   public void pause() {
      paused = true;
   }

   public boolean isPaused() {
      return paused;
   }
}
