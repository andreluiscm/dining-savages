package monitor;

public class Monitor
{
	private Pot pot;
	private Auxiliar condAwake;
	private Auxiliar condRefill;
	
	public Monitor(Pot pot)
	{
		this.pot = pot;
		condAwake = new Auxiliar();
		condRefill = new Auxiliar();
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
			
			synchronized (condAwake)
			{
				condAwake.notify();
			}
			
			synchronized (condRefill)
			{
				condRefill.wait();
			}
		}
		
		synchronized (pot)
		{
			pot.pickFood(savage.getId());
		}
	}
	
	public void refill(Cooker cooker) throws InterruptedException
	{
		synchronized (condAwake)
		{
			condAwake.wait();
		}
		
		synchronized (pot)
		{
			pot.refill();
		}
		
		synchronized (condRefill)
		{
			condRefill.notifyAll();
		}
	}
}
