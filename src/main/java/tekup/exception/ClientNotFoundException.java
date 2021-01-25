package tekup.exception;


public class ClientNotFoundException extends RuntimeException {

	public ClientNotFoundException() {
		super("Client not Found ! ");
	}
}