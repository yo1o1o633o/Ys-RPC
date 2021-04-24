package s.rpc_05;

import s.service.UserService;

public class Client {
    public static void main(String[] args) {
        UserService proxy = Stub.getStub(UserService.class);
        proxy.findById(1);
        proxy.save();
    }
}
