package co.grandcircus;

public enum checkedOut {

	ON_SHELF, CHECKED_OUT;

	@Override
	public String toString() {
		switch (this) {
		case ON_SHELF:
			return "On Shelf";
		case CHECKED_OUT:
			return "Checked Out";
		default:
			return null;
		}
	}
}