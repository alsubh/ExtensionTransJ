package util;

public class Money
{

	// Instance variable
	private long _cents;

	
	public Money()
	{ 
		_cents = 0;
	}

	public Money(int dollars)
	{ 
		_cents = 100L * dollars;
	}

	public Money(int dollars, long cents)
	{
		_cents = 100L * dollars + cents;
	}

	public void set(Money value)
	{ 
		_cents = value._cents;
	}

	static public Money add(Money first, Money second)
	{ 
		return new Money(0, first._cents + second._cents); 
	}

	static public Money subtract(Money minuend, Money subtrahend)
	{ 
		return new Money(0, minuend._cents - subtrahend._cents); 
	}

	public Money add(Money other)
	{ 
		_cents += other._cents; 
		return this;
	}

	public Money subtract(Money other)
	{ 
		_cents -= other._cents; 
		return this;
	}

	public int dollars()
	{ 
		return (int) _cents / 100; 
	}

	public int cents()
	{ 
		return (int) _cents % 100;
	}

	public boolean equals(Money other)
	{ 
		return _cents == other._cents;
	}

	public boolean less(Money other)
	{ 
		return _cents < other._cents;
	}
}

