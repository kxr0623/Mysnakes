package SnakeLogic;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * the direction of snake is right at first,
 * the color, length, start position is assigned by the parent class.
 */
public class Snake {
   private Direction direction;
   private ArrayList<Position> snakebody ;
   private Color snakeColor;
   private int length;
   private Boolean alive;
   private Position head;
   public Snake(Color snakeColor, int length,Position head) {
      this.direction = Direction.RIGHT;
      this.snakeColor = snakeColor;
      this.length = length;
      this.alive = true;
      this.head = head;
      snakebody=new ArrayList<Position>();
      for(int i=0;i<length;i++)//set the initialize length of snake
         snakebody.add(head);
   }

   public Direction getDirection() {
      return direction;
   }

   public int getLength() {
      return length;
   }

   public Boolean getAlive() {
      return alive;
   }

   public Position getHead() {
      return head;
   }

   public Color getColor() {
      return snakeColor;
   }

   public ArrayList<Position> getSnakebody() {
      return snakebody;
   }

   //make the snake longer, add the head
   public void move_grow(){
      if(alive ==true){
         length++;
         forward(head.translate(direction.getRow(),direction.getcol()));
      }
   }
   //move according to the direction, add head and remove tail.
   public void move(){
      if(alive ==true){
         forward(head.translate(direction.getRow(),direction.getcol()));
         snakebody.remove(0);
      }
   }
   //change the direction of snake to d;
   public void changeDirection(Direction d){
      direction=d;
   }
   //move forward and and make the snake longer
   private void forward(Position point){
         if(snakebody.contains(point))
            alive=false;
         snakebody.add(point);
         head=point;
   }
   //set the state of snake .
   public void setAlive(Boolean flag){
      alive=flag;
   }


   public static void main(String[] args){
      Position start=new Position(25,25);
      Snake s=new Snake(Color.GREEN,1,start);
      boolean testing = false;
      assert(testing = true);
      if(testing){
         s.test();
         System.out.println("Test pass!!!");
      }
      else {
         System.err.println(" Use java -ea Snake for testing.");
         System.exit(1);
      }
   }
   //test Snake FROM position (25,25): GO RIGHT, GO UP, eat the food (26,24)
   private void test(){
      Food food=new Food(50,50);
      food.setPosition(26,24);
      Board board=new Board(100,100,2,this);
      assert (this.getLength()==1);
      assert (this.getAlive()==true);
      assert (this.getHead().equals(new Position(25,25)));
      assert (this.getColor().equals(Color.GREEN));
      assert (this.getDirection().equals(Direction.RIGHT));

      this.move();
      for(int i=0;i<this.getSnakebody().size();i++) {
         assert (this.getSnakebody().get(i).getRow() ==26);
         assert (this.getSnakebody().get(i).getCol()==25);
      }

      this.changeDirection(Direction.UP);
      assert (this.getDirection().equals(Direction.UP));
      this.move();
      for(int i=0;i<this.getSnakebody().size();i++) {
         assert (this.getSnakebody().get(i).getRow() ==26);
         assert (this.getSnakebody().get(i).getCol()==24);
      }

      if (food.getPosition().equals(this.getHead())) {
         this.move_grow();
         food.setPosition(26,23);
      } else {
         this.move();
      }
      for(int i=0;i<this.getSnakebody().size();i++) {
         assert (this.getSnakebody().get(i).getRow() ==26);
         assert (this.getSnakebody().get(i).getCol()==(24-i));
      }
      assert (this.getLength()==2);
   }
}
