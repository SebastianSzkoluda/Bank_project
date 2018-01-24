package bank_core;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Client {
	private int numerNIK;
	private String haslo;
	private String imie;
	private String nazwisko;
	private int wiek;
	private BigDecimal pesel;
	public Client(int numerNIK, String haslo, String imie, String nazwisko, int wiek, BigDecimal pesel) {
		super();
		this.numerNIK = numerNIK;
		this.haslo = haslo;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.wiek = wiek;
		this.pesel = pesel;
	}
	public int getNumerNIK() {
		return numerNIK;
	}
	public void setNumerNIK(int numerNIK) {
		this.numerNIK = numerNIK;
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
	public int getWiek() {
		return wiek;
	}
	public void setWiek(int wiek) {
		this.wiek = wiek;
	}
	
	public BigDecimal getPesel() {
		return pesel;
	}
	public void setPesel(BigDecimal pesel) {
		this.pesel = pesel;
	}
	@Override
	public String toString() {
		return String
				.format("Klient [numerNIK=%s, haslo=%s, imie=%s, nazwisko=%s, wiek=%s, pesel=%s ]\n",
						numerNIK, haslo, imie, nazwisko, wiek, pesel);
	}
}
