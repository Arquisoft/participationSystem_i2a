package dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private Integer id;
	
	private String firstName;
	private String lastName;
	private Date birthdate;
	private String address;
	private String email;
	private String password;
	private String nationality;
	private int pollingStation;
	private String dni;

	public User() { }

	public User(String dni, String firstName, String lastName, Date birthdate, String address, String email,
			String nationality, int pollingStation) {
		super();
		this.dni = dni;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.address = address;
		this.email = email;
		this.nationality = nationality;
		this.pollingStation = pollingStation;
	}

	public String getFirstName() {
		return firstName;
	}

	public User setName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User setSurname(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public User setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public User setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getNationality() {
		return nationality;
	}

	public User setNationality(String nationality) {
		this.nationality = nationality;
		return this;
	}

	public Integer getId() {
		return id;
	}
	
	public User setId(Integer id) {
		this.id = id;
		return this;
	}
	
	public User setDni(String dni) {
		this.dni = dni;
		return this;
	}
	
	public String getDni() {
		return dni;
	}

	public int getPollingStation() {
		return pollingStation;
	}

	public User setPollingStation(int pollingStation) {
		this.pollingStation = pollingStation;
		return this;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + pollingStation;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (nationality == null) {
			if (other.nationality != null)
				return false;
		} else if (!nationality.equals(other.nationality))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pollingStation != other.pollingStation)
			return false;
		return true;
	}

	@Override
    public String toString() {
        String simpleDate = new SimpleDateFormat("dd/MM/yyyy").format(birthdate);

        return "Name: " + firstName + "; Surname: " + lastName + "; " +
                "Email: " + email + "; Birth date: " + simpleDate + "; " +
                "Address: " + address + "; Nationality: " +
                nationality + "; DNI: " + dni + "; Polling station: " + pollingStation;

    }

}
