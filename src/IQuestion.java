public interface IQuestion {

   boolean verify(String input);

   void increaseScore();

   String print();

   int getPoints();

}
