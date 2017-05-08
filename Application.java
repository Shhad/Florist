package test_programAplikacja;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;
import java.io.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;


//Zakladamy ze potrzebujemy 1 kwiatka na wystawe oraz moga wystapic wielokrotne kwiatki (np. roze czy tulipany)
//Klasa zarzadzajaca glownym oknem wyboru
public class Application {
	static JFrame ramka;
	static ArrayList<Flower> listaK;
	static ArrayList<Transaction> listaT;
	static boolean pozwolenie = false;
	private static boolean music=false;          //music file:-----------------
	static URL url = Application.class.getResource("music1.wav");
	static AudioClip clip = Applet.newAudioClip(url);
	private JLabel currentUser;
	private Client User;
	private JLabel currentAction;
	
	public static boolean getPlaying(){
		return music;
	}
	//zarzadzanie kontenerami
	public void dzialaj() {
		listaK = new ArrayList<Flower>();
		listaT = new ArrayList<Transaction>();
		User=null;
		currentUser=null;
		currentAction = null;
		
		ramka=new JFrame("Kwiaciarnia offline.");
		ramka.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ramka.setSize(1000,500);
		ramka.setLocationRelativeTo(null);
		ramka.setVisible(true);
		//ramka.setResizable(false);
		
		PanelBackground panelG = new PanelBackground();
		panelG.setLayout(new BorderLayout());
		
		Panel2 panelD = new Panel2();
		
		JButton music = new JButton("Muzyka");
		music.addActionListener(new MusicListener());
		
		JButton wyjscie = new JButton("Wyjdz");
		wyjscie.addActionListener(new WyjdzListener());
		panelD.add(wyjscie);
		panelD.add(music);
		panelG.add(BorderLayout.SOUTH,panelD);
		
		Panel2 panelGorny = new Panel2();
		panelGorny.setLayout(new BoxLayout(panelGorny, BoxLayout.Y_AXIS));
		
		JLabel etykieta1 = new JLabel("Witaj w naszej internetowej",JLabel.CENTER);
		etykieta1.setFont(new Font(etykieta1.getText(),Font.BOLD,30));
		JLabel etykieta2 = new JLabel("kwiaciarni!",JLabel.CENTER);
		etykieta2.setFont(new Font(etykieta2.getText(),Font.BOLD,30));
		
		panelGorny.add(etykieta1);
		panelGorny.add(etykieta2);
		etykieta1.setAlignmentX(panelGorny.CENTER_ALIGNMENT);
		etykieta2.setAlignmentX(panelGorny.CENTER_ALIGNMENT);
		
		panelG.add(BorderLayout.NORTH,panelGorny);
		
		Panel2 panelLoginu = new Panel2();
		JButton client = new JButton("Zaloguj sie");
		//client.addActionListener(new ZalogujListener());
		JButton sprzedawca = new JButton("Panel sprzedawcy");
		sprzedawca.addActionListener(new SprzedawcaListener());
		currentUser  = new JLabel("Zalogowany jako: goœæ");
		if(User!=null){
			currentUser.setText("Zalogowany jako: "+User.getNameSurname());
		}
		JLabel e5 = new JLabel("-----------------------------");
		e5.setFont(new Font(etykieta1.getText(),Font.BOLD,20));
		//JLabel e6 = new JLabel("-----------------------------");
		//e6.setFont(new Font(etykieta2.getText(),Font.BOLD,20));
		panelLoginu.setLayout(new BoxLayout(panelLoginu,BoxLayout.Y_AXIS));
		panelLoginu.add(client);
		panelLoginu.add(currentUser);
		panelLoginu.add(e5);
		panelLoginu.add(sprzedawca);
		client.setAlignmentX(panelLoginu.CENTER_ALIGNMENT);
		e5.setAlignmentX(panelLoginu.CENTER_ALIGNMENT);
		sprzedawca.setAlignmentX(panelLoginu.CENTER_ALIGNMENT);
		currentUser.setAlignmentX(panelLoginu.CENTER_ALIGNMENT);
		
		panelG.add(BorderLayout.EAST,panelLoginu);
		
		Panel2 panelAktualnosci = new Panel2();
		panelAktualnosci.setLayout(new BoxLayout(panelAktualnosci,BoxLayout.X_AXIS));
		JLabel currentAction= new JLabel("Aktualnosci:");
		currentAction.setFont(new Font(currentAction.getText(),Font.BOLD,30));
		panelAktualnosci.add(currentAction);
		currentAction.setAlignmentX(panelAktualnosci.CENTER_ALIGNMENT);
		
		panelG.add(BorderLayout.CENTER,panelAktualnosci);
		
		Panel2 panelWyboru = new Panel2();
		panelWyboru.setLayout(new BoxLayout(panelWyboru,BoxLayout.Y_AXIS));
		JButton zrobTrans = new JButton("Nowa transakcja");
		zrobTrans.addActionListener(new TransListener());
		JButton dodajK = new JButton("Ogladaj kwiatki");
		dodajK.addActionListener(new OgladajListener());
		JButton localization = new JButton("Lokalizacja");
		//localization.addActionListener(new LocalizationListener());
		JLabel etykieta3 = new JLabel("-----------------------------");
		etykieta3.setFont(new Font(etykieta1.getText(),Font.BOLD,20));
		JLabel etykieta4 = new JLabel("-----------------------------");
		etykieta4.setFont(new Font(etykieta2.getText(),Font.BOLD,20));
		panelWyboru.add(zrobTrans);
		panelWyboru.add(etykieta3);
		panelWyboru.add(dodajK);
		panelWyboru.add(etykieta4);
		panelWyboru.add(localization);
		zrobTrans.setAlignmentX(panelWyboru.CENTER_ALIGNMENT);
		dodajK.setAlignmentX(panelWyboru.CENTER_ALIGNMENT);
		sprzedawca.setAlignmentX(panelWyboru.CENTER_ALIGNMENT);
		etykieta3.setAlignmentX(panelWyboru.CENTER_ALIGNMENT);
		etykieta4.setAlignmentX(panelWyboru.CENTER_ALIGNMENT);
		localization.setAlignmentX(panelWyboru.CENTER_ALIGNMENT);
		
		panelG.add(BorderLayout.WEST,panelWyboru);
		
		ramka.getContentPane().add(BorderLayout.CENTER, panelG);
	}
	
	public void fromToK(ArrayList<Flower> arraylist, Flower[] tablica ) {
		for(int i=0;i<arraylist.size();i++) {
			tablica[i]=arraylist.get(i);
		}
	}
	//Klasa wewnetrzna zarzadzajaca opcja nowej transakcji
	public class DodajTransakcje {
		JFrame ramkaK;
		JPanel panel1;
		JPanel panel2;
		JPanel panel3;
		JPanel panelg;
		JTextField pole1;
		JTextField pole2;
		JTextField pole3;
		JList<Flower> lista;
		Transaction trans;
		
		DodajTransakcje() {
			trans = new Transaction();
			ramkaK = new JFrame("Nowa Transakcja");
			ramkaK.setVisible(true);
			ramkaK.setLocationRelativeTo(null);
			panel1 = new JPanel();
			panel2 = new JPanel();
			panel3 = new JPanel();
			panelg = new JPanel();
			JLabel ety1 = new JLabel("Imie:");
			JLabel ety2 = new JLabel("Nazwisko:");
			JLabel ety3 = new JLabel("Adres:");
			JLabel ety4 = new JLabel("Aby wyjsc z opcji dodawania transakcji zamknij okno.",JLabel.CENTER);
			pole1 = new JTextField(10);
			pole2 = new JTextField(10);
			pole3 = new JTextField(10);
			lista= new JList(listaK.toArray(new Flower[listaK.size()]));
			JScrollPane przewi = new JScrollPane(lista);
			przewi.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			przewi.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			lista.setVisibleRowCount(6);
			lista.addListSelectionListener( new ListaListener());
			lista.setCellRenderer(new ExampleListCellRenderer());
			JButton przyciskZapisz = new JButton("Zapisz");
			przyciskZapisz.addActionListener(new ZapiszTListener());
			panel1.add(ety1);
			panel1.add(pole1);
			panel2.add(ety2);
			panel2.add(pole2);
			panel3.add(ety3);
			panel3.add(pole3);
			panelg.setLayout(new BoxLayout(panelg, BoxLayout.Y_AXIS));
			panelg.add(panel1);
			panelg.add(panel2);
			panelg.add(panel3);
			panelg.add(przewi);
			panelg.add(ety4);
			ramkaK.getContentPane().add(BorderLayout.SOUTH, przyciskZapisz);
			ramkaK.getContentPane().add(BorderLayout.CENTER, panelg);
			ramkaK.pack();
			
		}
		//Informowanie uzytkownika o bledzie
		void blad(){
			JFrame blad = new JFrame("Error FRAME");
			JLabel etyB = new JLabel("Jedno z pol jest puste!");
			etyB.setFont(new Font(etyB.getText(),Font.BOLD,40));
			blad.getContentPane().add(BorderLayout.CENTER, etyB);
			blad.setLocationRelativeTo(null);
			blad.pack();
			blad.setVisible(true);
		}
		//Klasa wewnetrzna definiujaca zdarzenie zapisania transakcji
		public class ZapiszTListener implements ActionListener {
			public void actionPerformed(ActionEvent zdarzenie) {
				if(((pole1.getText()).equals("")) || ((pole2.getText()).equals("")) || ((pole3.getText()).equals(""))) {
					blad();
					System.out.println("Blad");
				}
				else {
					
					trans.setImieNazwisko((pole1.getText()+pole2.getText()));
					trans.setAdres(pole3.getText());
					
					try {
						listaT.add(trans);
						System.out.println("zapisalo sie");
					} catch (Exception e) {
						System.out.println("Nie zapisalo sie");
					}
					ramkaK.setVisible(false);
					ramkaK.dispose();
				}
			}
		}
		//Klasa wewnetrzna obslugujaca wyor elementow z listy
		public class ListaListener implements ListSelectionListener {
			JFrame ramkaIl;
			Flower opcja;
			Flower podtrzymywacz;
			JTextField poleIl;
			public void valueChanged(ListSelectionEvent zdarzenie) {
				if(!zdarzenie.getValueIsAdjusting()) {
					opcja =  lista.getSelectedValue();
					podtrzymywacz=lista.getSelectedValue();
					System.out.println("Wybrano: "+opcja.getName());
					ramkaIl = new JFrame("Ilosc");
					JLabel etykietaIl= new JLabel("Ilosc: ");
					poleIl = new JTextField(10);
					JButton przyciskIl = new JButton("Potwierdz");
					przyciskIl.addActionListener(new PotwierdzListener());
					ramkaIl.getContentPane().add(BorderLayout.WEST,etykietaIl);
					ramkaIl.getContentPane().add(BorderLayout.EAST,poleIl);
					ramkaIl.getContentPane().add(BorderLayout.SOUTH,przyciskIl);
					
					ramkaIl.pack();
					ramkaIl.setVisible(true);
				}
			}
			//klasa wewnetrzna definiujaca zatwierdzenie wybrania elementow
			class PotwierdzListener implements ActionListener {
				public void actionPerformed(ActionEvent zdarzenie) {
					if((poleIl.getText()).equals("") || Integer.parseInt((poleIl.getText()))<=0) {
						blad();
						System.out.println("Blad");
					}
					else {
						
						if(!((podtrzymywacz.number-(Integer.parseInt(poleIl.getText())))<=0)){
							trans.cena+=Integer.parseInt(poleIl.getText())*opcja.price;
							System.out.println("Do zaplaty: "+trans.cena);
							opcja=new Flower(podtrzymywacz.name,Integer.parseInt((poleIl.getText())),podtrzymywacz.price);
							System.out.println("Ilosc opcji: "+opcja.number);
							System.out.println("Ilosc podtrzymywacza: "+podtrzymywacz.number);
							podtrzymywacz.setNumber(podtrzymywacz.number-opcja.number);
							trans.listaKwiatkow.add(opcja);
							ramkaIl.setVisible(false);
							ramkaIl.dispose();
							podtrzymywacz=null;
							opcja=null;
						}
						else{
							System.out.println("Mamy: "+opcja.number);
							System.out.println("Probujesz kupic: "+poleIl.getText());
							JFrame blad = new JFrame("Error FRAME");
							JLabel etyB = new JLabel("Nie mamy tyle kwiatków!");
							etyB.setFont(new Font(etyB.getText(),Font.BOLD,40));
							blad.getContentPane().add(BorderLayout.CENTER, etyB);
							blad.setLocationRelativeTo(null);
							blad.pack();
							blad.setVisible(true);
						}
					}
				}
				void blad(){
					JFrame blad = new JFrame("Error FRAME");
					JLabel etyB = new JLabel("Jedno z pol jest puste lub wadliwe!");
					etyB.setFont(new Font(etyB.getText(),Font.BOLD,40));
					blad.getContentPane().add(BorderLayout.CENTER, etyB);
					blad.setLocationRelativeTo(null);
					blad.pack();
					blad.setVisible(true);
				}
			}
		}
		
	}
	//Klasa wewnetrzna zarzadzajaca opcja przegladania elementow
	public class Kwiatki {
		JFrame ramkaK;
		JList<Flower> lista;
		JTextArea area;
		
		Kwiatki() {
			ramkaK = new JFrame("Kwiatki");
			ramkaK.setVisible(true);
			ramkaK.setLocationRelativeTo(null);
			area = new JTextArea(10,10);
			JLabel ety1 = new JLabel("Nasze produkty:");
			Panel2 panel = new Panel2();
			panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
			JLabel ety4 = new JLabel("Aby wyjsc z opcji przegladania zamknij okno.",JLabel.CENTER);
			lista= new JList(listaK.toArray(new Flower[listaK.size()]));
			lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			lista.setVisibleRowCount(6);
			lista.addListSelectionListener( new ListaListener());
			lista.setCellRenderer(new ExampleListCellRenderer());
			JScrollPane przewi = new JScrollPane(lista);
			przewi.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			przewi.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel.add(ety1);
			panel.add(przewi);
			panel.add(new JLabel("Szczegó³y produktu:",JLabel.CENTER));
			panel.add(area);
			panel.add(ety4);
			ety1.setAlignmentX(panel.CENTER_ALIGNMENT);
			ety4.setAlignmentX(panel.CENTER_ALIGNMENT);
			ramkaK.getContentPane().add(BorderLayout.CENTER, panel);
			ramkaK.pack();
			
		}
		//Klasa wewnetrzna obslugujaca wybor elementu z listy
		public class ListaListener implements ListSelectionListener {
			Flower opcja;
			public void valueChanged(ListSelectionEvent zdarzenie) {
				if(!zdarzenie.getValueIsAdjusting()) {
					opcja =  lista.getSelectedValue();
					System.out.println("Wybrano: "+opcja.getName());
					area.setText("Kwiatek: "+opcja.getName()+"\n"+"Ilosc na stanie: "+opcja.getNumber()+"\n"+"Cena: "+opcja.getPrice());

				}
			}
		}
	}
	//Klasa wewnetrzna obslugujaca pokazanie elementow na liscie
	public class ExampleListCellRenderer extends DefaultListCellRenderer
	{
	    public Component getListCellRendererComponent(
	        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	    {
	       JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

	       Flower pomoc = (Flower) value;
	       label.setText(pomoc.name);

	       return label;

	    }
	}
	
	public class WyjdzListener implements ActionListener {               //Klasy definiujce zdarzenia przypisane
		public void actionPerformed(ActionEvent zdarzenie) {         //do przyciskow glownego menu
			System.exit(0);                                      //            |
		}                                                            //            V
	}                                                                    //

	private class MusicListener implements ActionListener {
		public void actionPerformed(ActionEvent action){
			try{music();}
			catch(Exception ex){ex.printStackTrace();}
		}
	}
	
	private class ZalogujListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			new ZalogujPanel();
		}
	}
	
	public class OgladajListener implements ActionListener {
		public void actionPerformed(ActionEvent zdarzenie) {
			new Kwiatki();
		}
	}
	
	public class TransListener implements ActionListener {
		public void actionPerformed(ActionEvent zdarzenie) {
			new DodajTransakcje();
		}
	}
	
	
	public class SprzedawcaListener implements ActionListener {
		public void actionPerformed(ActionEvent zdarzenie) {
			new Salesman();
		}
	}
	
	public static void music() throws Exception{
		
		if(getPlaying()){
			System.out.println("Muzyka stop");
			Application.clip.stop();
			Application.music=false;
		} else {
			System.out.println("Muzyka czas start");
			Application.clip.play();
			Application.music=true;
		}
	}
		
}
	//Klasa definiujaca panel dolny okienka glownego
	class Panel2 extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			this.setOpaque(false);
		}
	}
	//Klasa definijaca tlo okienka glownego
	class PanelBackground extends JPanel {
		private BufferedImage image;

		public PanelBackground() {
			super();
			//source path of image on my disc
			File imageFile = new File("files\\background.jpg");
			try {
				image = ImageIO.read(imageFile);
				image.getScaledInstance(1000, 400, Image.SCALE_DEFAULT);
			} catch (IOException e) {
				System.err.println("Blad odczytu obrazka");
				e.printStackTrace();
			}
		}
		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(image, 0, 0, this);
		}
	}
	
	
	
