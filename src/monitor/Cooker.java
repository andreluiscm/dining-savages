package monitor;

public class Cooker implements Runnable
{
	private Monitor monitor;
	
	public Cooker(Monitor monitor)
	{
		this.monitor = monitor;
	}
	
	public void run()
	{
		while (true)
		{
			try
			{
				monitor.refill(this);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
