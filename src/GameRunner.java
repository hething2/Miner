import java.net.Socket;
import java.util.HashMap;

import org.quickconnectfamily.json.JSONException;
import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;


public class GameRunner implements Runnable {
	private Socket mSocket;
	public GameRunner(Socket socket) {
		this.mSocket = socket;
	}

	@Override
	public void run() {
		try {
			JSONInputStream in = new JSONInputStream(this.mSocket.getInputStream());
			JSONOutputStream out = new JSONOutputStream(this.mSocket.getOutputStream());
			CommandBean command = null;
			do {
				HashMap<String, Object> input = (HashMap<String, Object>) in.readObject();
				CommandBean command = ApplicationController.getInstance().handle(input);
				if (command != null) {
					out.writeObject(command);
				}
			}
			while(command == null || !command.getType().equals("End"));
		} catch(Exception e) {
			
		} finally {
			this.mSocket.close();
		}
	}
}
