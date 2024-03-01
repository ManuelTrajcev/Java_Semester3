package designPaterns.chainOfResponsibility;

public class AuthService {
    private Handler handler;

    public AuthService(Handler handler) {
        this.handler = handler;
    }

    public boolean logIn(String email, String password) {
        if (handler.handle(email, password)) {
            System.out.println("Authorization was successful!");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Database database = new Database();
        Handler handler = new UserExistsHandler(database)
                .setNextHandler(new ValidPasswordHandler(database))
                .setNextHandler(new RoleCheckHandler());
        AuthService service = new AuthService(handler);
        service.logIn("username", "password");
    }
}
