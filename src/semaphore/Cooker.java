package semaphore;

import java.util.concurrent.Semaphore;

public class Cooker implements Runnable
{
	private Pot pot;
	private Semaphore semaphoreAwake;
	private Semaphore semaphoreRefill;
	
	public Cooker(Pot pot, Semaphore semaphoreAwake, Semaphore semaphoreRefill)
	{
		this.pot = pot;
		this.semaphoreAwake = semaphoreAwake;
		this.semaphoreRefill = semaphoreRefill;
	}

	public void run()
	{
		while (true)
		{
			try
			{
				semaphoreAwake.acquire();
				
				pot.refill();
				
				semaphoreRefill.release();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
