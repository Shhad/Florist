import java.util.Date;
import java.util.ArrayList;

public class Transakcja {
	Date data; //Nasz klucz transkacji
	ArrayList<Kwiatek> listaKwiatkow;
	String imieNazwisko;
	String adres;
	double cena;
	boolean czyZrealizowane;
	
	public Transakcja() {
		cena=0;
		listaKwiatkow= new ArrayList<Kwiatek>();
		czyZrealizowane=false;
		data=new Date();//zapisuje czas w ktorym zostal wywolany w milisek
	}
	void setData() {
		data= new Date();
	}
	void setImieNazwisko(String in) {
		imieNazwisko=in;
	}
	void setAdres(String ad) {
		adres=ad;
	}
	void wysweitl() {
		System.out.println("Klient: "+imieNazwisko);
		System.out.println("Adres klienta: "+adres);
		System.out.println("Data zlozenia zamowienia: "+data);
		if(czyZrealizowane) {
			System.out.println("Zamowienie potwierdzone.");
		}
		else System.out.println("Zamowienie nie potwierdzone.");
	}
	
}
