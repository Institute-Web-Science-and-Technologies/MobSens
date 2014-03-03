package MobileSensors.EventClassifiers;

import java.util.ArrayList;

import MobileSensors.Window;
import MobileSensors.Events.Event;

/**
 * 
 * Event Classifier
 * 
 * @author henny, thomas, max
 * 
 */
public interface EventClassifier {

	public ArrayList<Event> getEvents(Window win);

}