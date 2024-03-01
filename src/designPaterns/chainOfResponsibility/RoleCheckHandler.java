package designPaterns.chainOfResponsibility;

public class RoleCheckHandler extends  Handler{
    @Override
    public boolean handle(String username, String password) {
        if ("admin_username".equals(username)) {

        }
        System.out.println("Loading Default Page...");
        return handleNext(username, password);
    }
}
