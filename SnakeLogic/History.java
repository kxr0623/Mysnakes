package SnakeLogic;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class is used to read history scores from a txt file and save the new score to a file.
 */
public class History {
   private String path;

   public History(String path) {
      this.path = path;
   }
   //read file and put the content to ines[], sort the score records
  public String[] readfile(String filename) {
      List<String> words =new ArrayList<>();
      try {
         File file = new File(""+ filename);
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
               String line = in.nextLine();
               if (line.length() == 0) continue;
               words.add(line);
            }
            in.close();
      } catch (Exception e) { throw new Error(e); }
      String[] lines=new String[words.size()];
      lines= words.toArray(lines);
      lines=sortScores(lines);
      return lines;
   }
   //sort the records in the file according to the score
  private String [] sortScores(String [] lines){
     String[][] r=new String[lines.length][3];
     for(int i=0;i<lines.length;i++)
     {
        r[i] = lines[i].split("\\s+");
     }
     String [][] temp=new String[r.length][3];
     String ts=new String();
      if(r.length>=1) {
         for (int i = 0; i < r.length - 1; i++) {
            for (int j = 0; j < r.length - 1 - i; j++ ) {
               int score1 = Integer.parseInt(r[j][0]);
               int score2 = Integer.parseInt(r[j + 1][0]);
               if (score1 < score2) {
                  copyString(temp[j],r[j + 1]);
                  copyString(r[j+1] , r[j]);
                  copyString(r[j] , temp[j]);

                  ts=lines[j+1];
                  lines[j+1]=lines[j];
                  lines[j]=ts;
               }
            }
         }
      }
      return lines;
   }
  //copy string[] to other string []
   private void copyString(String[] ts, String[] s){
      for (int i=0;i<s.length;i++){
         ts[i]=s[i];
      }
   }
   //put the new score to a txt file
   public boolean writetofile(String username, String score,String filename){
      String[] content=readfile(filename);
      if(username.equals(null)||username.equals(""))
      {
         return false;
      }
      try {
         File file= new File(""+ filename);
         if (file.exists()){
            file.delete();
         }
         FileWriter writto = new FileWriter(file, true);
         writto.write("\r\n");
         //writto.write("SCORE  USERNAME    TIME  \n");
         for(int j=0;j<content.length;j++){
               writto.write(content[j]+"\r\n");
            //writto.write("\r\n");
         }
         String timeStamp = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss").format(Calendar.getInstance().getTime());
         String newS=score+"   "+username+"   "+timeStamp;
         writto.write(newS);
         writto.close();
      }catch (IOException e) {
         e.printStackTrace();
         return false;
      }
      return true;
   }
   public static void main(String[] args) {
      History history = new History(".");
      boolean testing = false;
      assert(testing = true);
      if (testing ) {
         history.test(args, history);
         System.out.println("TESTS PASS!");
      }
      else {
         System.err.println("please input \"java -ea history \"for testing.");
         System.exit(1);
      }
   }
   //test the read and write methods in this class
   private void test(String[] args ,History history){
      String test = "111   john   2017/04/19/17:45:08";
      assert (history.readfile("test_History.txt")[0].equals(test));
      assert (history.writetofile("john","111","test_History.txt"));
      assert (!history.writetofile("","111","test_History.txt"));
      assert (history.readfile("test_History.txt")[0].equals(test));
   }

}
