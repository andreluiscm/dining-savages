package monitor;

public class Monitor
{
	private Pot pot;
	private Auxiliar monitorAwake;
	
	public Monitor(Pot pot)
	{
		this.pot = pot;
		monitorAwake = new Auxiliar();
	}
	
	public synchronized void pickFood(Savage savage) throws InterruptedException
	{
		while (pot.getQuantity() == 0)
		{
			synchronized (System.in)
			{
				System.out.println("- The pot is empty.\n"
						+ "- Savage " + savage.getId() + " woke up the cooker.\n");
			}
			
			synchronized (monitorAwake)
			{
				monitorAwake.notify();
			}
			
			this.wait();
		}
		
		synchronized (pot)
		{
			pot.pickFood(savage.getId());
		}
	}
	
	public void refill(Cooker cooker) throws InterruptedException
	{
		synchronized (monitorAwake)
		{
			monitorAwake.wait();
		}
		
		synchronized (pot)
		{
			pot.refill();
		}
		
		synchronized (this)
		{
			this.notifyAll();
		}
	}
}
