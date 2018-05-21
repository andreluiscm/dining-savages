package monitor;

public class Main
{
	public static void main (String[] args) throws Exception
	{
		System.out.println("DINING SAVAGES - MONITOR SOLUTION\n");
		
		int numberOfSavages = 10;
		int sizeOfPot = 3;
		
		Pot pot = new Pot(sizeOfPot);
		
		Monitor monitor = new Monitor(pot);
		
		Thread cooker = new Thread(new Cooker(monitor));
		cooker.start();
		
		for (int i = 1; i <= numberOfSavages; i++)
		{
			Thread savage = new Thread(new Savage(i, monitor));
			savage.start();
		}
	}
}
