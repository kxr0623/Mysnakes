package SnakeLogic;

/**
 * Created by 52704 on 2017/3/26.
 */
public class Position {
   private int row;
   private int  col;

   public Position(int row,int col) {
      this.row = row;
      this.col =col;
   }

   public int getRow() {
      return row;
   }

   public int getCol() {
      return col;
   }
   //change a position to another one
   public Position translate(int dx, int dy){
      return new Position(row + dx, col + dy);
   }
   // check the food point is occupied by snake's body or not.
   public boolean occupied(Position snakebody){
      return (row==snakebody.row) && (col==snakebody.col);
   }
   // check the point is in the board
   public boolean inboard(int maxr, int maxc){
      return (row<maxr && row>=0) &&(col<maxc && col>=0);
   }
   @Override
   public boolean equals(Object obj) {
      if (obj == null)
         return false;
      if (!(obj instanceof Position))
         return false;
      Position other = (Position) obj;
      if (row != other.row)
         return false;
      if (col != other.col)
         return false;
      return true;
   }

   private void tests(){
      Position testP =new Position(1,1);
      Position testP2=new Position(1,3);
      assert (occupied(testP)) ;
      assert (!occupied(testP2));
      assert (inboard(2,2));
      assert (!inboard(-1,1));
   }

   public static void main(String[] args){
      Position from=new Position(1,1);
      boolean testing = false;
      assert(testing = true);
      if(testing){
         from.tests();
         System.out.println("Test pass!!!");
      }
      else {
         System.err.println(" Use java -ea Position for testing.");
         System.exit(1);
      }
   }

}
