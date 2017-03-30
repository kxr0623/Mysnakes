package SnakeLogic;

/**
 * Created by 52704 on 2017/3/26.
 */
public enum  Direction {
   UP (0,-1),
   DOWN(0,1),
   LEFT(-1,0),
   RIGHT(1,0);
   private final int x,y;
   Direction(int x, int y) {
      this.x = x;
      this.y = y;
   }

   int getRow(){
      return x;
   }
   int getcol(){
      return  y;
   }


}
