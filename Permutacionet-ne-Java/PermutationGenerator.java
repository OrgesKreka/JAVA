import java.util.ArrayList;

public class PermutationGenerator
{
	private String word;
	
	public PermutationGenerator( String aword )
	{
		word = aword;
	}
	
	
	public ArrayList<String> getPermutations()
	{
		ArrayList<String> permutations = new ArrayList<String>();
		
		///Stringu bosh ka vetem nje permutacion, veten
		if( word.length() == 0 )
		{
			permutations.add( word );
			return permutations;
		}
		
		
		/// kap karakteret nje nga nje
		for( int i = 0; i < word.length(); i++ )
		{
			String shorterWord = word.substring( 0, i ) + word.substring( i + 1 );
			
			///permutacionet e fjales me te shkurter
			PermutationGenerator shorterPermutationGenerator = new PermutationGenerator( shorterWord );
			
			ArrayList<String> shorterWordPermutations = shorterPermutationGenerator.getPermutations();
			
			for( String s : shorterWordPermutations )
			{
				permutations.add( word.charAt( i ) + s );
			}
			
			
		} ///FUND for
		
		return permutations;
	}
}
