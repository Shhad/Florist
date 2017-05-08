package test_programAplikacja;

//Klasa definiujaca obiekt klienta
public class Client {

	private String nameSurname;
	private String adress;
	private String phoneN;
	private int enteringN; //zmienna definiujaca ilosc zalogowan do systemu(potrzebne w  wyliczaniu bonusow 
			       // i rabatow lojalnosciowych)
	public Client(){
		
	}
	
	public Client(String nS, String a, String p){
		nameSurname=nS;
		adress=a;
		phoneN=p;
		enteringN=1;
	}
	
	public String getNameSurname(){
		return nameSurname;
	}
	
	public String getAdress() {
		return adress;
	}
}
