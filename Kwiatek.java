package prog_lab2;

public class Kwiatek extends Object {
	String nazwa;
	int IloscNaStanie;
	double cena;

	void setNazwa(String naz) {
		nazwa=naz;
	}
	void setIlosc(int il) {
		IloscNaStanie=il;
	}
	void setCena(double cen) {
		cena=cen;
	}
	String getNazwa() {
		return nazwa;
	}
	int getIlosc() {
		return IloscNaStanie;
	} 
	double getCena() {
		return cena;
	}
	void wyswietl() {
		System.out.println("Nazwa: "+nazwa);
		System.out.println("Ilosc obecna: "+IloscNaStanie);
	}
}
