public class RulareServer {
    public static void main(String[] args) throws InterruptedException {
        Thread threadServer = new Thread(() -> {
            try{
                Server.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Thread threadClient = new Thread(() -> {
            try {
                Client.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        threadServer.start();
        try {
            Thread.sleep(1000);
        }catch(Exception e){
            e.printStackTrace();
        }
        threadClient.start();
    }
}
