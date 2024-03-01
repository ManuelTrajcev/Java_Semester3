package designPaterns.mediator;

public class Dialog implements Mediator {
    private TextBox userTextButton;
    private TextBox passTextButton;

    @Override
    public void login() {
        String username = userTextButton.getText();
        String password = passTextButton.getText();
    }
}
