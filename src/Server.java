import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Server {
	private Executor thread_pool;
	private ServerSocket serv_sock;
	private Boolean running = false;
	
	static class ServerHolder {
		static final Server INSTANCE = new Server();
	}
	
	private Server() {
		try {
			serv_sock = new ServerSocket(1900);
			thread_pool = Executors.newCachedThreadPool();
		} catch (IOException e) {}
	}
	
	public Server getInstance() {
		return ServerHolder.INSTANCE;
	}
	
	public void start() {
		if (!running) {
			while(running) {
				try {
					Socket socket = this.serv_sock.accept();
					this.thread_pool.execute(new GameRunner(socket));
				} catch (IOException e) {
				}
			}
		}
	}
}
