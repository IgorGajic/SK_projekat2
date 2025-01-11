package gui.fx.app.restclient.dto;

import java.sql.Date;

public class ClientCreateDto extends UserCreateDto{
	private int numberOfReservations;

	public int getNumberOfReservations() {
		return numberOfReservations;
	}

	public void setNumberOfReservations(int numberOfReservations) {
		this.numberOfReservations = numberOfReservations;
	}

}
