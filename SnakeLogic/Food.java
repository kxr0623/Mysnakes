package SnakeLogic;

import javafx.scene.paint.Color;

import java.util.Random;

/**
 * this class is used to handle the attributes and actions of food.
 * Including set the position, colour of food.
 *
 */
public class Food {
   Position position;
   Color color;
   private int maxrows, maxcols;

   public Food(int maxrows, int maxcols) {
      this.maxrows = maxrows;
      this.maxcols = maxcols;
      setColor(Color.RED);
      setPosition();
   }
   private Color setColor(Color c){
      color=c ;
      return color;
   }
   public Color getColor(){
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

   private void test(){
     assert (Color.RED.equals(this.color));
     setPosition(24,25);
     assert (getPosition().equals(new Position(24,25)));
   }
   public static void main(String[] args){
      Food food=new Food(10,10);
      boolean testing = false;
      assert(testing = true);
      if(testing){
         food.test();
         System.out.println("Tests pass!");
      }
      else {
         System.err.println(" Use java -ea Food for testing.");
         System.exit(1);
      }
   }
}
