package designPaterns.mediator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginButton extends JButton implements Component {
    private Mediator mediator;

    public LoginButton() {
        super("Login In");
    }

    @Override
    protected void fireActionPerformed(ActionEvent event) {
        mediator.login();
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public String getName() {
        return "LoginButton";
    }
}
