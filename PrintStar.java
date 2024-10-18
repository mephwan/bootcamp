public class PrintStar {
  public static void main(String[] args) {
    int row = 21;
    int mid = (row + 1) / 2;
    int absValue = 0;

    for (int i = 1; i <= row; i++){

      if ((mid - i) >= 0) {

        absValue = mid - i;
      } else {
        absValue = -1 * (mid - i);
      }

      for (int j = 1; j <= absValue; j++){

        System.out.print(" ");

      }

      for (int j = 1; j <= row - 2 * absValue; j++) {

        System.out.print("*");
      }

      for (int j = 1; j <= absValue; j++){

        System.out.print(" ");

      }

      System.out.println("");
    }
  }
}
