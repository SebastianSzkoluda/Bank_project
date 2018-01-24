package bank_core;

import java.sql.Date;

public class Przelewy {
	int przelew_id;
	int numerNIK1;
	int numerNIK2;
	float kwota;
	Date data;
	boolean zatwierdzone;
	int f_bankier_id ;
	String f_numerRachunku ;
	int f_kierownik_id ;
	public Przelewy(int przelew_id, int numerNIK1, int numerNIK2, float kwota, Date data, boolean zatwierdzone,
			int f_bankier_id, String f_numerRachunku2, int f_kierownik_id) {
		super();
		this.przelew_id = przelew_id;
		this.numerNIK1 = numerNIK1;
		this.numerNIK2 = numerNIK2;
		this.kwota = kwota;
		this.data = data;
		this.zatwierdzone = zatwierdzone;
		this.f_bankier_id = f_bankier_id;
		this.f_numerRachunku = f_numerRachunku2;
		this.f_kierownik_id = f_kierownik_id;
	}
	public int getPrzelew_id() {
		return przelew_id;
	}
	public void setPrzelew_id(int przelew_id) {
		this.przelew_id = przelew_id;
	}
	public int getNumerNIK1() {
		return numerNIK1;
	}
	public void setNumerNIK1(int numerNIK1) {
		this.numerNIK1 = numerNIK1;
	}
	public int getNumerNIK2() {
		return numerNIK2;
	}
	public void setNumerNIK2(int numerNIK2) {
		this.numerNIK2 = numerNIK2;
	}
	public float getKwota() {
		return kwota;
	}
	public void setKwota(float kwota) {
		this.kwota = kwota;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public boolean isZatwierdzone() {
		return zatwierdzone;
	}
	public void setZatwierdzone(boolean zatwierdzone) {
		this.zatwierdzone = zatwierdzone;
	}
	public int getF_bankier_id() {
		return f_bankier_id;
	}
	public void setF_bankier_id(int f_bankier_id) {
		this.f_bankier_id = f_bankier_id;
	}
	public String getF_numerRachunku() {
		return f_numerRachunku;
	}
	public void setF_numerRachunku(String f_numerRachunku) {
		this.f_numerRachunku = f_numerRachunku;
	}
	public int getF_kierownik_id() {
		return f_kierownik_id;
	}
	public void setF_kierownik_id(int f_kierownik_id) {
		this.f_kierownik_id = f_kierownik_id;
	}
	
	@Override
	public String toString() {
		return String
				.format("Przelew [przelew_id=%s, numerNIK1=%s, numerNIK2=%s, kwota=%s, data=%s,zatwierdzone=%s,f_bankier_id=%s,f_numerRachunku=%s,f_kierownik_id=%s]\n",
						przelew_id, numerNIK1, numerNIK2, kwota, data, zatwierdzone, f_bankier_id, f_numerRachunku, f_kierownik_id);
	}
}
