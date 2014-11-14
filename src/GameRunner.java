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
			String type = null;
			do {
				HashMap<String, Object> input = (HashMap<String, Object>) in.readObject();
				type = input.get("type");
				if( input != null) {
					ApplicationController.getInstance().handle(type, input);
				}
			}
			while(input != null && !input.equals("End"));
		} catch(Exception e) {
			
		} finally {
			this.mSocket.close();
		}
	}
}
