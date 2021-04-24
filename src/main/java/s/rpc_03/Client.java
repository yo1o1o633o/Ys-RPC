package s.rpc_03;

import s.service.UserService;

public class Client {
    public static void main(String[] args) {
        UserService proxy = Stub.getStub();
        proxy.findById(1);
    }
}
