package tekup.exception;

public class TableNotFoundException extends RuntimeException {

	public TableNotFoundException() {
		super("Table not found !");
	}

}
