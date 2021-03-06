package ikor.model.graphics.io;

import ikor.model.graphics.Drawing;
import ikor.model.graphics.swing.JDrawingComponent;

import java.io.IOException;
import java.io.OutputStream;

public class JPGDrawingWriter extends DrawingWriter 
{
	public JPGDrawingWriter (Drawing drawing) 
	{
		super(drawing);
	}

	@Override
	public void write(OutputStream writer) 
		throws IOException
	{
		JDrawingComponent component = new JDrawingComponent(drawing);

		component.setSize( drawing.getWidth(), drawing.getHeight() );
		component.repaint();
		component.save(writer, "jpg");
	}

}
