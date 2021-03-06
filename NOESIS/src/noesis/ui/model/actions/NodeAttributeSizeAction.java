package noesis.ui.model.actions;

import ikor.model.ui.Application;
import ikor.model.ui.Editor;
import ikor.model.ui.Selector;
import ikor.util.indexer.Indexer;

import noesis.Attribute;
import noesis.AttributeNetwork;
import noesis.ui.model.NetworkFigure;


public class NodeAttributeSizeAction extends NodeAttributeAction 
{
	private Application     application;
	private NetworkFigure   figure;
	private Selector        attributes;
	private Editor<Boolean> logScale;
	
	public static final int DEFAULT_INDEX_SIZE = 16;

	public NodeAttributeSizeAction (Application application, NetworkFigure figure, Selector attributes, Editor<Boolean> logScale)
	{
		this.application = application;
		this.figure = figure;
		this.attributes = attributes;
		this.logScale = logScale;
	}
		
	
	@Override
	public void run() 
	{
		AttributeNetwork network = (AttributeNetwork) application.get("network");
		
		if (network!=null) {
			
			String id = getSelectedAttributeID(attributes);
			
			if (id==null) {
				
				application.message("Please, choose an attribute to adjust node sizes.");
				
			} else {
				
				Attribute attribute = network.getNodeAttribute(id);
				
				Indexer<Integer> indexer = createIndexer(attribute, DEFAULT_INDEX_SIZE, isLogarithmicScale(logScale) );

				figure.getRenderer().getNodeRenderer().setSizeIndexer(indexer);

				figure.refresh();
			}
		}
	}			
	
}	
