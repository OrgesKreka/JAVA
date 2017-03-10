import java.util.ArrayList;
import java.util.Scanner;

public class Test
{
	public static void main( String args[] )
	{
		
		String fjala_e_ngaterruar;
		Scanner in = new Scanner( System.in );
		
		System.out.println( "Jep fjalen: " );
		fjala_e_ngaterruar = in.next();
		
		PermutationGenerator generator = new PermutationGenerator( fjala_e_ngaterruar );
		
		ArrayList< String > permutations = generator.getPermutations();
		
		int i = 1;
		for( String s : permutations )
		{
			System.out.println( i + " : " + s );
			i++;
		}
		
	}
}
