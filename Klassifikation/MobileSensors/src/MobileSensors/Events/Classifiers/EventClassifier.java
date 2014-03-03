package MobileSensors.Events.Classifiers;

import java.util.ArrayList;

import MobileSensors.Events.Event;
import MobileSensors.Sensors.SensorCollection;
import weka.classifiers.Classifier;
import weka.core.SerializationHelper;

public abstract class EventClassifier {

	abstract public ArrayList<Event> classify (SensorCollection scWindow);
	
	protected Classifier classifier;
	
	public EventClassifier (String modelFile) throws Exception {
		
		this.classifier = (Classifier) SerializationHelper.read(modelFile);
		
	}
	
}