package designPaterns.observer;

public class Store {
    private final NotificationService notificationService;

    public Store() {
        notificationService = new NotificationService();
    }

    public NotificationService getService() {
        return notificationService;
    }
}
