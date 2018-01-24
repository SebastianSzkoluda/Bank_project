package bank_core;

public class Konto {

	private String numerRachunku;
	private String nazwaRachunku;
	private String typRachunku;
	private float saldo;
	private int k_numerNIK;
	public Konto(String numerRachunku, String nazwaRachunku, String typRachunku, float saldo, int k_numerNIK) {
		super();
		this.numerRachunku = numerRachunku;
		this.nazwaRachunku = nazwaRachunku;
		this.typRachunku = typRachunku;
		this.saldo = saldo;
		this.k_numerNIK = k_numerNIK;
	}
	public String getNumerRachunku() {
		return numerRachunku;
	}
	public void setNumerRachunku(String numerRachunku) {
		this.numerRachunku = numerRachunku;
	}
	public String getNazwaRachunku() {
		return nazwaRachunku;
	}
	public void setNazwaRachunku(String nazwaRachunku) {
		this.nazwaRachunku = nazwaRachunku;
	}
	public String getTypRachunku() {
		return typRachunku;
	}
	public void setTypRachunku(String typRachunku) {
		this.typRachunku = typRachunku;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public int getK_numerNIK() {
		return k_numerNIK;
	}
	public void setK_numerNIK(int k_numerNIK) {
		this.k_numerNIK = k_numerNIK;
	}
	
	@Override
	public String toString() {
		return String
				.format("Konto [numerRachunku=%s, nazwaRachunku=%s, typRachunku=%s, saldo=%s, k_numerNIK=%s]\n",
						numerRachunku, nazwaRachunku, typRachunku, saldo, k_numerNIK);
	}
}
