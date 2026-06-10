public class Main1 {
    public static void main(String[] args) throws InterruptedException {
        Server1 server = new Server1();
        Client1 client = new Client1();
        Thread thread = new Thread(server);
        thread.start();
        thread.sleep(500);
        Thread thread1 = new Thread(client);
        thread1.start();
        thread.join();
        thread1.join();
    }
}
