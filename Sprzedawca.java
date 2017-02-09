package prog_lab2;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class Sprzedawca extends Object{
	JFrame ramka;
	JPasswordField haslo;
	JButton log;
	static final String HASLO="kwiaciarnia";
	
	Sprzedawca() {
		ramka=new JFrame("Panel sprzedawcy");
		ramka.validate();
		JLabel etykieta = new JLabel("Podaj haslo: ");
		haslo = new JPasswordField(10);
		log=new JButton("Zaloguj");
		log.addActionListener(new ZalogujListener());
		ramka.getContentPane().add(BorderLayout.WEST, etykieta);
		ramka.getContentPane().add(BorderLayout.EAST, haslo);
		ramka.getContentPane().add(BorderLayout.SOUTH, log);
		ramka.pack();
		ramka.setVisible(true);
	}
	
	class ZalogujListener implements ActionListener {
		public void actionPerformed(ActionEvent zdarzenie) {
			if(haslo.getText().equals(HASLO)) {
				new PanelOpcji();
				ramka.setVisible(false);
				ramka.dispose();
			}
			else {
				blad();
				System.out.println("Blad");
			}
		}
	}
	
	class PanelOpcji {
		JFrame ramkaO;
		PanelOpcji() {
			ramkaO=new JFrame("Opcje");
			ramkaO.setSize(200,200);
			ramkaO.setVisible(true);
			ramkaO.setLocationRelativeTo(null);
			PanelG panel = new PanelG();
			JButton dodac = new JButton("Dodaj kwiatka");
			dodac.addActionListener(new DodajListener());
			JButton wyswietl = new JButton("Zarzadzaj transakcjami");
			wyswietl.addActionListener(new ZarzadzajListener());
			panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
			panel.add(dodac);
			JLabel etykieta = new JLabel("----------------");
			etykieta.setFont(new Font(etykieta.getText(),Font.BOLD,20));
			panel.add(etykieta);
			panel.add(wyswietl);
			dodac.setAlignmentX(panel.CENTER_ALIGNMENT);
			etykieta.setAlignmentX(panel.CENTER_ALIGNMENT);
			wyswietl.setAlignmentX(panel.CENTER_ALIGNMENT);
			ramkaO.getContentPane().add(BorderLayout.CENTER, panel);
		}
		
		class DodajListener implements ActionListener {
			public void actionPerformed(ActionEvent zdarzenie) {
				new DodajKwiatka();
			}
		}
		
		class ZarzadzajListener implements ActionListener {
			public void actionPerformed(ActionEvent zdarzenie) {
				new PrzegladTransakcji();
			}
		}
		
		
	}
	
	class PrzegladTransakcji {
		JFrame ramkaP;
		JList<Transakcja> lista;
		PanelG panel;
		JTextField poleCzy;
		JButton potwierdz;
		Transakcja opcja;
		
		PrzegladTransakcji() {
			ramka.setVisible(false);
			ramka.dispose();
			ramkaP = new JFrame("Transakcje");
			ramkaP.setSize(300,300);
			ramka.setLocationRelativeTo(null);
			ramkaP.setVisible(true);
			potwierdz=new JButton("Zrealizuj");
			potwierdz.addActionListener(new ZrealizujListener());
			poleCzy = new JTextField(5);
			lista= new JList(Appli.listaT.toArray(new Transakcja[Appli.listaT.size()]));
			panel = new PanelG();
			panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
			lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			lista.setVisibleRowCount(8);
			lista.addListSelectionListener( new ListaListener());
			lista.setCellRenderer(new ExampleListCellRenderer());
			JScrollPane przewi = new JScrollPane(lista);
			przewi.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			przewi.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
			panel.add(przewi);
			przewi.setAlignmentX(panel.CENTER_ALIGNMENT);
			JLabel etykieta1 = new JLabel("Czy potiwerdzona:");
			panel.add(etykieta1);
			etykieta1.setAlignmentX(panel.CENTER_ALIGNMENT);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER));
			panel.add(poleCzy);
			poleCzy.setAlignmentX(panel.CENTER_ALIGNMENT);
			panel.add(potwierdz);
			potwierdz.setAlignmentX(panel.CENTER_ALIGNMENT);
			ramkaP.getContentPane().add(BorderLayout.CENTER, panel);
		}
		
	
		
		public class ListaListener implements ListSelectionListener {
			
			public void valueChanged(ListSelectionEvent zdarzenie) {
				if(!zdarzenie.getValueIsAdjusting()) {
					opcja =  lista.getSelectedValue();
					String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(opcja.data);
					System.out.println("Wybrano: "+String.format(date));
					if(opcja.czyZrealizowane){
						poleCzy.setText("TAK");
					}
					else{
						poleCzy.setText("NIE");
						
					}
				}
			}
			
		}
		public class ZrealizujListener implements ActionListener {
			public void actionPerformed(ActionEvent zdarzenie) {
				lista.getSelectedValue().czyZrealizowane=true;
				System.out.println("Transakcja zostala zrealizowana.");
				poleCzy.setText("TAK");
			}
		}
	
	}
	
	public class DodajKwiatka {
		
		JFrame ramkaK;
		JPanel panel1;
		JPanel panel2;
		JPanel panel3;
		JPanel panelg;
		JTextField pole1;
		JTextField pole2;
		JTextField pole3;
		
		DodajKwiatka() {
			ramkaK = new JFrame("Nowy Kwiatek");
			//ramkaT.setSize(300, 300);
			//ramkaK.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ramkaK.setVisible(true);
			ramkaK.setLocationRelativeTo(null);
			panel1 = new JPanel();
			panel2 = new JPanel();
			panel3 = new JPanel();
			panelg = new JPanel();
			JLabel ety1 = new JLabel("Podaj nazwe:");
			JLabel ety2 = new JLabel("Podaj cene za sztuke:");
			JLabel ety3 = new JLabel("Podaj ilosc:");
			pole1 = new JTextField(10);
			pole2 = new JTextField(10);
			pole3 = new JTextField(10);
			JButton przyciskZapisz = new JButton("Zapisz");
			przyciskZapisz.addActionListener(new ZapiszKListener());
			
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
			ramkaK.getContentPane().add(BorderLayout.SOUTH, przyciskZapisz);
			ramkaK.getContentPane().add(BorderLayout.CENTER, panelg);
			ramkaK.pack();
			
		}

		void blad(){
			JFrame blad = new JFrame("Error FRAME");
			JLabel etyB = new JLabel("Jedno z pol jest puste");
			JLabel etyB2 = new JLabel("lub cena jest <= zero!");
			etyB.setFont(new Font(etyB.getText(),Font.BOLD,40));
			etyB2.setFont(new Font(etyB2.getText(),Font.BOLD,40));
			blad.getContentPane().add(BorderLayout.CENTER, etyB);
			blad.getContentPane().add(BorderLayout.SOUTH, etyB2);
			blad.setLocationRelativeTo(null);
			blad.pack();
			blad.setVisible(true);
		}
		public class ZapiszKListener implements ActionListener {
			public void actionPerformed(ActionEvent zdarzenie) {
				if(((pole1.getText()).equals("")) || ((pole2.getText()).equals("")) || ((pole3.getText()).equals("")) || Double.parseDouble((pole2.getText()))<=0) {
					blad();
					System.out.println("Blad");
				}
				else {
					Kwiatek kwiatek = new Kwiatek();
					kwiatek.setNazwa(pole1.getText());
					kwiatek.setCena(Float.parseFloat(pole2.getText()));
					kwiatek.setIlosc(Integer.parseInt(pole3.getText()));
					try {
						Appli.listaK.add(kwiatek);
						System.out.println("zapisalo sie");
					} catch (Exception e) {
						System.out.println("Nie zapisalo sie");
					}
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
		       Transakcja pomoc = (Transakcja) value;
		       String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pomoc.data);
		       label.setText(date);

		       return label;

		    }
		}
	
	void blad(){
		JFrame blad = new JFrame("Error FRAME");
		JLabel etyB = new JLabel("Haslo jest nieprawidlowe!");
		etyB.setFont(new Font(etyB.getText(),Font.BOLD,40));
		blad.getContentPane().add(BorderLayout.CENTER, etyB);
		blad.setLocationRelativeTo(null);
		blad.pack();
		blad.setVisible(true);
	}
	
	class PanelG extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			GradientPaint gradient = new GradientPaint((this.getWidth()/2),this.getHeight(),new Color(124,207,238),(this.getWidth()/2),0,new Color(22,113,222));
			g2d.setPaint(gradient);
			g2d.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
		}
	}
}
