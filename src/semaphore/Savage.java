package semaphore;

import java.util.concurrent.Semaphore;

public class Savage implements Runnable
{
	private int id;
	private Pot pot;
	private Semaphore semaphoreMutex;
	private Semaphore semaphoreAwake;
	private Semaphore semaphoreRefilled;

	public Savage(int id, Pot pot, Semaphore semaphoreMutex, Semaphore semaphoreAwake, Semaphore semaphoreRefilled)
	{
		this.id = id;
		this.pot = pot;
		this.semaphoreMutex = semaphoreMutex;
		this.semaphoreAwake = semaphoreAwake;
		this.semaphoreRefilled = semaphoreRefilled;
	}

	public void run()
	{
		while (true)
		{
			try
			{
				semaphoreMutex.acquire();

				if (pot.getQuantity() == 0)
				{
					System.out.println("- The pot is empty.\n"
							+ "- Savage " + id + " woke up the cooker.\n");
					
					semaphoreAwake.release();
					semaphoreRefilled.acquire();
				}

				pot.pickFood(id);
				
				semaphoreMutex.release();
				
				eat();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public void eat()
	{
		System.out.println("Savage " + id + " ate the missionary.\n");
		
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
