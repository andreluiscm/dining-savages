package semaphore;

public class Pot
{
	private int position;
	private int capacity;
	private int quantity;
	private boolean[] hasFood;
	
	public Pot(int capacity)
	{
		position = 0;
		this.capacity = capacity;
		quantity = 0;
		hasFood = new boolean[capacity];
		
		for (int i = 0; i < capacity; i++)
			hasFood[i] = true;
	}

	public int getQuantity()
	{
		return quantity;
	}
	
	public void pickFood(int savageId)
	{
		hasFood[position] = false;
		quantity--;
		position++;
		
		System.out.println("Savage " + savageId + " took one missionary from the pot.\n");
	}

	public void refill()
	{		
		quantity = capacity;
		
		for (int i = 0; i < capacity; i++)
			hasFood[i] = true;

		position = 0;
		
		System.out.println("-- The cooker refilled the pot.\n"
				+ "-- There are " + quantity + " new missionary/missionaries beeing cooked.\n");
	}
}
