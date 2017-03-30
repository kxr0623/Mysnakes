package SnakeLogic;

import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Created by 52704 on 2017/3/26.
 */
public class Food {
   Position position;
   Color color;
   private int maxrows, maxcols;

   public Food(int maxrows, int maxcols) {
      this.maxrows = maxrows;
      this.maxcols = maxcols;
      setColor(Color.RED);
      //setPosition();
   }
   Color setColor(Color c){
      color=c ;
      return color;
   }
// give the food a random position in the board
   void setPosition(){
      Random random =new Random();
      position = new Position(random.nextInt(maxrows),random.nextInt(maxcols));

   }
   void setPosition(int row,int col){
      position = new Position(row,col);

   }

   public Position getPosition() {
      return position;
   }

   void test(){
     assert (Color.RED.equals(this.color));
   }
   public static void main(String[] args){
      Food food=new Food(10,10);
      boolean testing = false;
      assert(testing = true);
      if(testing){
         food.test();
         System.out.println("tests pass!");
      }
      else {
         System.err.println(" Use java -ea Food for testing.");
         System.exit(1);
      }
   }
}
