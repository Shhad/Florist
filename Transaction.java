package test_programAplikacja;

import java.util.Date;
import java.util.ArrayList;

//Klasa definiujaca transakcje jako element
public class Transaction {
	Date data; 
	ArrayList<Flower> listaKwiatkow;
	String imieNazwisko;
	String adres;
	double cena;
	boolean czyZrealizowane;
	
	public Transaction() {
		cena=0;
		listaKwiatkow= new ArrayList<Flower>();
		czyZrealizowane=false;
		data=new Date();
	}
	void setDate() {
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
