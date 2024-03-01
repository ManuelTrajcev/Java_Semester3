package designPaterns.observer;

public class EmailMsgListener implements EvenListener {
    private final String email;

    public EmailMsgListener(String email) {
        this.email = email;
    }

    @Override
    public void update(Event eventType) {

    }
}
