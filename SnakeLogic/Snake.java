package SnakeLogic;


import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by 52704 on 2017/3/26.
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
   private Board board;

   public Snake(Color snakeColor, int length,Position head) {
      this.direction = Direction.RIGHT;
      this.snakeColor = snakeColor;
      this.length = length;
      this.alive = true;
      this.head = head;
      this.board=board;
      snakebody=new ArrayList<Position>();
      snakebody.add(head);
      System.out.println("0 head:"+this.getSnakebody().indexOf(head));
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
         int tailindex=this.getSnakebody().indexOf(head)-length;
         System.out.println("head:"+this.getSnakebody().indexOf(head));
         System.out.println("tail:"+tailindex);
         snakebody.remove(0);
      }
   }
   public void changeDirection(Direction d){
      direction=d;
   }
   //move forward and and make the snake longer
   private void forward(Position point){
         if(snakebody.contains(point))
            alive=false;
         snakebody.add(point);
         System.out.println("index of point:"+this.getSnakebody().indexOf(point));
         head=point;
   }
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
   void test(){
      //todo:test Snake
      Food food=new Food(50,50);
      food.setPosition(26,24);
      board=new Board(100,100,2,this);

      //this.forward(new Position(26,25));//go right.
      this.move();
      for(int i=0;i<this.getSnakebody().size();i++)
         System.out.println("right:"+this.getSnakebody().get(i).getRow()+","+this.getSnakebody().get(i).getCol());
      direction=Direction.UP;
      this.changeDirection(direction);;//go up
      this.move();
      for(int i=0;i<this.getSnakebody().size();i++)
         System.out.println("up:"+this.getSnakebody().get(i).getRow()+","+this.getSnakebody().get(i).getCol());

      //eat the food (26,24)
      if (food.getPosition().equals(this.getHead())) {
         this.move_grow();
         food.setPosition(26,23);
      } else {
         this.move();
      }
      for(int i=0;i<this.getSnakebody().size();i++)
         System.out.println("after eat(26,24):"+this.getSnakebody().get(i).getRow()+","+this.getSnakebody().get(i).getCol());
      assert (this.getLength()==2); System.out.println(this.length);

//eat the food(26,23)
      if (food.getPosition().equals(this.getHead())) {
         this.move_grow();
         food.setPosition(25,22);
      } else {
         this.move();
      }
      for(int i=0;i<this.getSnakebody().size();i++)
         System.out.println("after eat(26,23):"+this.getSnakebody().get(i).getRow()+","+this.getSnakebody().get(i).getCol());
      assert (this.getLength()==3); System.out.println(this.length);

      //move left
      direction=Direction.LEFT;
      if (food.getPosition().equals(this.getHead())) {
         this.move_grow();
         food.setPosition(25,22);
      } else {
         this.move();
      }
      for(int i=0;i<this.getSnakebody().size();i++)
         System.out.println("after move left:"+this.getSnakebody().get(i).getRow()+","+this.getSnakebody().get(i).getCol());
      assert (this.getLength()==3); System.out.println(this.length);

      //eat (25,22)
      if (food.getPosition().equals(this.getHead())) {
         this.move_grow();
         food.setPosition(25,24);
      } else {
         this.move();
      }
      for(int i=0;i<this.getSnakebody().size();i++)
         System.out.println("after eat(25,22):"+this.getSnakebody().get(i).getRow()+","+this.getSnakebody().get(i).getCol());
      assert (this.getLength()==4); System.out.println(this.length);


   }
}
