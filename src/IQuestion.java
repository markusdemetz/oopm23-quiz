public interface IQuestion {

   boolean verify(String input);

   void increaseScore();

   void print();

   int getPoints();

}
