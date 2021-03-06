package noesis.io.graphics;

import java.awt.Color;

import ikor.model.graphics.Line;
import ikor.model.graphics.Style;

public class DefaultLinkRenderer extends LinkRenderer 
{
	public static final Color DEFAULT_COLOR = new Color(0x70, 0x70, 0x70, 0xFF);
	
	private Style style;
	
	public DefaultLinkRenderer ()
	{
		style = new Style ( DEFAULT_COLOR, DEFAULT_WIDTH);
	}
	
	public DefaultLinkRenderer (Style style)
	{
		this.style = style;
	}

	@Override
	public void setWidth (int width) 
	{
		if (width>=0) {
			super.setWidth(width);
			style.setWidth(width);
		}
	}
	
	@Override
	public void render(NetworkRenderer drawing, int source, int target) 
	{
		drawing.add( 
			new Line ( 
				drawing.getLinkId(source, target), 
				style,
				drawing.getX(source), drawing.getY(source), 
				drawing.getX(target), drawing.getY(target) 
			) );
	}

	@Override
	public void update(NetworkRenderer drawing, int source, int target) 
	{
		Line link = (Line) drawing.getDrawingElement( drawing.getLinkId(source, target) );
		
		if (link!=null) {			
			link.setStartX( drawing.getX(source) );
			link.setStartY( drawing.getY(source) );
			link.setEndX( drawing.getX(target) );
			link.setEndY( drawing.getY(target) );
		}

	}

	@Override
	public Style getStyle(int source, int target) 
	{
		return style;
	}


}
