package semaphore;

import java.util.concurrent.Semaphore;

public class Main
{
	public static void main (String[] args) throws Exception
	{
		System.out.println("DINING SAVAGES - SEMAPHORE SOLUTION\n");
		
		int numberOfSavages = 10;
		int sizeOfPot = 3;

		Semaphore semaphoreMutex = new Semaphore(1);
		Semaphore semaphoreAwake = new Semaphore(0);
		Semaphore semaphoreRefill = new Semaphore(0);

		Pot pot = new Pot(sizeOfPot);

		Thread cooker = new Thread(new Cooker(pot, semaphoreAwake, semaphoreRefill));
		cooker.start();

		for (int i = 1; i <= numberOfSavages; i++)
		{
			Thread savage = new Thread(new Savage(i, pot, semaphoreMutex, semaphoreAwake, semaphoreRefill));
			savage.start();
		}
	}
}
