package main.util.errors;

public class GameError {
	Errors e;

	public GameError() {
		this.e = Errors.NO_ERROR;
	}

	public void setError(Errors e) {
		this.e = e;
	}

	public String toString() {
		return e.toString();
	}
}
