package test_programAplikacja;

public class Flower extends Object {
	String name;
	int number;
	double price;
	
	Flower() {
		super();
	}
	Flower(String n,int nu,double p){
		name=n;
		number=nu;
		price=p;
	}
	void setName(String naz) {
		name=naz;
	}
	void setNumber(int il) {
		number=il;
	}
	void setPrice(double cen) {
		price=cen;
	}
	String getName() {
		return name;
	}
	int getNumber() {
		return number;
	} 
	double getPrice() {
		return price;
	}
	void wyswietl() {
		System.out.println("Nazwa: "+name);
		System.out.println("Ilosc obecna: "+number);
	}
}
