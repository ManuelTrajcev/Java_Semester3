package designPaterns.observer;

import designPaterns.command.Light;

import java.util.*;

public class NotificationService {
    private final Map<Event,List<EmailMsgListener>> customers;

    public NotificationService() {
        customers = new HashMap<>();
        Arrays.stream(Event.values()).forEach(event ->
                customers.put(event,new ArrayList<>()));
    }
    public void subscribe(Event eventType, EmailMsgListener listener) {
        customers.get(eventType).add(listener);
    }
    public void unsubscribe(Event eventType,EmailMsgListener listener) {
        customers.get(eventType).remove(listener);
    }
    public void notifyCustomer(Event eventType) {
        customers.get(eventType).forEach(listener ->
                listener.update(eventType));
    }
}
