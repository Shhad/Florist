package test_programAplikacja;

//main program
public class Program {
	private Application application;
	
	private Program(){
		application=new Application();
		application.dzialaj();
	}
	
	public static void main(String[] args){
		Program program = new Program();
	}
}
