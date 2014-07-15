import java.util.Scanner;

/*���� �� �� ��������� ����������� ��������������� ���������, ����������� �����, ����� �������� 
 * � ����������� � ����������� ������� ������ �������� ����� � ����� ��������, � ����� ������� 
 * ���������� � ��������� ������ ��� �������� ����� ����, �� ���������� ��������� ������� 
 * ���������� ��������� ���������� [��������� ��������� "(()(()))" - ����������, 
 * � "()(" � "())(" - ���].
 * ����� ����� ���������� ��������� ���������, ���������� N ������������� � N ������������� 
 * ������. N �������� � ����������. N ��������������� ����� �����.*/

public class Brackets {
	
	//Catalan's numbers describe the number of correct bracketed expressions
	public static int Catalan(int n) {
		int i, sum;
		if (n <= 0)
			return 1; 		 
		sum = 0;
		for (i = 0;i < n;i++) {
		   sum += Catalan(i)*Catalan((n-1)-i);
		}
		return sum;		 
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int i = 0;
        System.out.print("Enter the number of brackets: ");
        if(sc.hasNextInt()) {
          i = sc.nextInt();
          if(i % 2 != 0)
        	  System.out.println("No such expressions");
          else {
      		  System.out.println("The number of correct bracketed expressions: " + Catalan(i/2));
          }
        } else {
          System.out.println("You entered is not an Integer!!!");
        }

	}

}
