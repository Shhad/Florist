package prog_lab2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;



public class Appli {
	static JFrame ramka;
	static ArrayList<Kwiatek> listaK;
	static ArrayList<Transakcja> listaT;
	static boolean pozwolenie = false;
	
	public static void main(String [] args) {	
		
		Appli aplikacja = new Appli();
		aplikacja.dzialaj();
	}
	
	public void dzialaj() {
		listaK = new ArrayList<Kwiatek>();
		listaT = new ArrayList<Transakcja>();
		Kwiatek roza = new Kwiatek();
		roza.setNazwa("Ró¿a");
		roza.setIlosc(100);
		roza.setCena(8.00);
		listaK.add(roza);
		
		ramka=new JFrame("Kwiaciarnia online.");
		ramka.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ramka.setSize(300,300);
		ramka.setLocationRelativeTo(null);
		//ramka.pack();
		ramka.setVisible(true);
		
		JButton wyjscie = new JButton("Wyjdz");
		wyjscie.addActionListener(new WyjdzListener());
		ramka.getContentPane().add(BorderLayout.SOUTH,wyjscie);
		
		Panel1 panelGorny = new Panel1();
		panelGorny.setLayout(new BoxLayout(panelGorny, BoxLayout.Y_AXIS));
		
		
		JLabel etykieta1 = new JLabel("Witaj w naszej internetowej",JLabel.CENTER);
		etykieta1.setFont(new Font(etykieta1.getText(),Font.BOLD,20));
		JLabel etykieta2 = new JLabel("kwiaciarni!",JLabel.CENTER);
		etykieta2.setFont(new Font(etykieta2.getText(),Font.BOLD,20));
		
		panelGorny.add(etykieta1);
		panelGorny.add(etykieta2);
		etykieta1.setAlignmentX(panelGorny.CENTER_ALIGNMENT);
		etykieta2.setAlignmentX(panelGorny.CENTER_ALIGNMENT);
		
		ramka.getContentPane().add(BorderLayout.NORTH, panelGorny);
		
		Panel2 panelWyboru = new Panel2();
		panelWyboru.setLayout(new BoxLayout(panelWyboru,BoxLayout.Y_AXIS));
		JButton zrobTrans = new JButton("Nowa transakcja");
		zrobTrans.addActionListener(new TransListener());
		JButton dodajK = new JButton("Ogladaj kwiatki");
		dodajK.addActionListener(new OgladajListener());
		JButton sprzedawca = new JButton("Panel sprzedawcy");
		sprzedawca.addActionListener(new SprzedawcaListener());
		JLabel etykieta3 = new JLabel("-----------------------------");
		etykieta3.setFont(new Font(etykieta1.getText(),Font.BOLD,20));
		JLabel etykieta4 = new JLabel("-----------------------------");
		etykieta4.setFont(new Font(etykieta2.getText(),Font.BOLD,20));
		panelWyboru.add(zrobTrans);
		panelWyboru.add(etykieta3);
		panelWyboru.add(dodajK);
		panelWyboru.add(etykieta4);
		panelWyboru.add(sprzedawca);
		zrobTrans.setAlignmentX(panelWyboru.CENTER_ALIGNMENT);
		dodajK.setAlignmentX(panelWyboru.CENTER_ALIGNMENT);
		sprzedawca.setAlignmentX(panelWyboru.CENTER_ALIGNMENT);
		etykieta3.setAlignmentX(panelWyboru.CENTER_ALIGNMENT);
		etykieta4.setAlignmentX(panelWyboru.CENTER_ALIGNMENT);
		
		ramka.getContentPane().add(BorderLayout.CENTER, panelWyboru);
		

		//JButton wyjscie = new JButton("Wyjdz");
		//wyjscie.addActionListener(new WyjdzListener());
	}
	
	public void fromToK(ArrayList<Kwiatek> arraylist, Kwiatek[] tablica ) {
		for(int i=0;i<arraylist.size();i++) {
			tablica[i]=arraylist.get(i);
		}
	}
	
	public class DodajTransakcje {
		JFrame ramkaK;
		JPanel panel1;
		JPanel panel2;
		JPanel panel3;
		JPanel panelg;
		JTextField pole1;
		JTextField pole2;
		JTextField pole3;
		JList<Kwiatek> lista;
		Transakcja trans;
		
		DodajTransakcje() {
			trans = new Transakcja();
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
			lista= new JList(listaK.toArray(new Kwiatek[listaK.size()]));
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

		void blad(){
			JFrame blad = new JFrame("Error FRAME");
			JLabel etyB = new JLabel("Jedno z pol jest puste!");
			etyB.setFont(new Font(etyB.getText(),Font.BOLD,40));
			blad.getContentPane().add(BorderLayout.CENTER, etyB);
			blad.setLocationRelativeTo(null);
			blad.pack();
			blad.setVisible(true);
		}
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
		
		public class ListaListener implements ListSelectionListener {
			JFrame ramkaIl;
			Kwiatek opcja;
			JTextField poleIl;
			public void valueChanged(ListSelectionEvent zdarzenie) {
				if(!zdarzenie.getValueIsAdjusting()) {
					opcja =  lista.getSelectedValue();
					System.out.println("Wybrano: "+opcja.getNazwa());
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
			
			class PotwierdzListener implements ActionListener {
				public void actionPerformed(ActionEvent zdarzenie) {
					if((poleIl.getText()).equals("") || Integer.parseInt((poleIl.getText()))<=0) {
						blad();
						System.out.println("Blad");
					}
					else {
						trans.cena+=Integer.parseInt(poleIl.getText())*opcja.cena;
						System.out.println("Cena aktu: "+trans.cena);
						opcja.setIlosc(Integer.parseInt((poleIl.getText())));
						trans.listaKwiatkow.add(opcja);
						ramkaIl.setVisible(false);
						ramkaIl.dispose();
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
	
	public class Kwiatki {
		JFrame ramkaK;
		JList<Kwiatek> lista;
		JTextArea area;
		
		Kwiatki() {
			ramkaK = new JFrame("Kwiatki");
			ramkaK.setVisible(true);
			ramkaK.setLocationRelativeTo(null);
			area = new JTextArea(10,10);
			JLabel ety1 = new JLabel("Nasze produkty:");
			Panel1 panel = new Panel1();
			panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
			JLabel ety4 = new JLabel("Aby wyjsc z opcji przegladania zamknij okno.",JLabel.CENTER);
			lista= new JList(listaK.toArray(new Kwiatek[listaK.size()]));
			JScrollPane przewi = new JScrollPane(lista);
			przewi.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			przewi.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			lista.setVisibleRowCount(6);
			lista.addListSelectionListener( new ListaListener());
			lista.setCellRenderer(new ExampleListCellRenderer());
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
		
		public class ListaListener implements ListSelectionListener {
			Kwiatek opcja;
			public void valueChanged(ListSelectionEvent zdarzenie) {
				if(!zdarzenie.getValueIsAdjusting()) {
					opcja =  lista.getSelectedValue();
					System.out.println("Wybrano: "+opcja.getNazwa());
					area.setText("Kwiatek: "+opcja.getNazwa()+"\n"+"Ilosc na stanie: "+opcja.getIlosc()+"\n"+"Cena: "+opcja.getCena());

				}
			}
		}
	}
	public class ExampleListCellRenderer extends DefaultListCellRenderer
	{
	    public Component getListCellRendererComponent(
	        JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	    {
	        // DefaultListCellRenderer zwraca label
	        JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

	        // "value" is whatever object you put into the list, you can use it however you want here - IMPORTANT
	       Kwiatek pomoc = (Kwiatek) value;
	       label.setText(pomoc.nazwa);

	       return label;

	    }
	}
	
	public class WyjdzListener implements ActionListener {
		public void actionPerformed(ActionEvent zdarzenie) {
			System.exit(0);
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
			new Sprzedawca();
		}
	}
		
	}
	
	class Panel1 extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			GradientPaint gradient = new GradientPaint((this.getWidth()/2),this.getHeight(),new Color(206,21,212),(this.getWidth()/2),0,new Color(90,76,216));
			g2d.setPaint(gradient);
			g2d.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
		}
	}
	
	class Panel2 extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			GradientPaint gradient = new GradientPaint((this.getWidth()/2),this.getHeight(),new Color(239,255,0),(this.getWidth()/2),0,new Color(206,21,212));
			g2d.setPaint(gradient);
			g2d.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
	
		}
	}
