package monitor;

public class Savage implements Runnable
{
	private int id;
	private Monitor monitor;
	
	public Savage(int id, Monitor monitor)
	{
		this.id = id;
		this.monitor = monitor;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void run()
	{
		while (true)
		{
			try
			{
				monitor.pickFood(this);
				
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
		synchronized (System.in)
		{
			System.out.println("Savage " + id + " ate the missionary.\n");
		}
		
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
