import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;


public class CommandBean implements Serializable{
	private String type;

	public getType() { return this.type; }
	public setType(String type) ( this.type = type; }
}
