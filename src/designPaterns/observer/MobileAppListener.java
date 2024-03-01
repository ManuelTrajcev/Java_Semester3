package designPaterns.observer;

public class MobileAppListener implements EvenListener{
    private final String username;

    public MobileAppListener(String username) {
        this.username = username;
    }

    @Override
    public void update(Event eventType) {

    }
}
