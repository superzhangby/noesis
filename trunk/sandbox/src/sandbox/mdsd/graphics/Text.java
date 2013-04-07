package sandbox.mdsd.graphics;

public class Text extends DrawingElement 
{
	private String text;
	private int x;
	private int y;


	public Text (String text, Style style, int x, int y)
	{
		super(text,style);
		
		this.text = text;
		this.x = x;
		this.y = y;
	}
	

	public String getText() 
	{
		return text;
	}

	public void setText(String text) 
	{
		this.text = text;
	}
	

	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public int getY() 
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}


	public String toString ()
	{
		return String.format("t(%d,%d)@<%s>", x, y, text);
	}


	@Override
	public int getWidth() 
	{
		return 0;
	}

	@Override
	public int getHeight() 
	{
		return 0;
	}


	@Override
	public boolean containsPoint (int x, int y) 
	{
		return false;
	}
	
}
