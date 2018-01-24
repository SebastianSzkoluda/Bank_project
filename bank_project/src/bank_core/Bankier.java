package bank_core;

public class Bankier {

	private int bankier_id;
	private String haslo;
	private String imie;
	private String nazwisko;
	public Bankier(int bankier_id, String haslo, String imie, String nazwisko) {
		super();
		this.bankier_id = bankier_id;
		this.haslo = haslo;
		this.imie = imie;
		this.nazwisko = nazwisko;
	}
	public int getBankier_id() {
		return bankier_id;
	}
	public void setBankier_id(int bankier_id) {
		this.bankier_id = bankier_id;
	}
	public String getHaslo() {
		return haslo;
	}
	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	
	@Override
	public String toString() {
		return String
				.format("Bankier [bankier_id=%s, haslo=%s, imie=%s, nazwisko=%s]",
						bankier_id, haslo, imie, nazwisko);
	}
}
