package s.rpc_02;

public class Client {
    public static void main(String[] args) {
        Stub stub = new Stub();
        stub.findById(1);
    }
}
