package s.rpc_04;

import s.service.UserService;

public class Client {
    public static void main(String[] args) {
        UserService proxy = Stub.getStub();
        proxy.findById(1);
        proxy.save();
    }
}
