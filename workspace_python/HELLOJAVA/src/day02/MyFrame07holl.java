package day02;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyFrame07holl extends JFrame {

	private JPanel contentPane;
	private JTextField tfCom;
	private JTextField tfMine;
	private JTextField tfResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame07holl frame = new MyFrame07holl();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyFrame07holl() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCom = new JLabel("\uCEF4 : ");
		lblCom.setBounds(42, 69, 37, 15);
		contentPane.add(lblCom);
		
		JLabel lblMine = new JLabel("\uB098 : ");
		lblMine.setBounds(42, 104, 31, 15);
		contentPane.add(lblMine);
		
		JLabel lblResult = new JLabel("\uACB0\uACFC : ");
		lblResult.setBounds(42, 147, 37, 15);
		contentPane.add(lblResult);
		
		tfCom = new JTextField();
		tfCom.setBounds(124, 66, 116, 21);
		contentPane.add(tfCom);
		tfCom.setColumns(10);
		
		tfMine = new JTextField();
		tfMine.setBounds(124, 101, 116, 21);
		contentPane.add(tfMine);
		tfMine.setColumns(10);
		
		tfResult = new JTextField();
		tfResult.setBounds(124, 144, 116, 21);
		contentPane.add(tfResult);
		tfResult.setColumns(10);
		
		JButton btn = new JButton("\uC2E4\uD589");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				myexecute();
				
//				String com = "";
//				int rnd =  (int) Math.round(Math.random() * 1);
//				
//				if(rnd == 0) {
//					com = "È¦";
//				}else {
//					com = "Â¦";
//				}
//				
//				String mine = tfMine.getText();
//				String result = "";
//				
//				if(mine.equals(com)) {
//					result = "Á¤´äÀÔ´Ï´Ù!";
//				}else {
//					result = "Æ²·È½À´Ï´Ù¤Ì";
//				}
//				tfCom.setText(com);
//				tfResult.setText(result);
				
			}
		});
		btn.setBounds(42, 190, 198, 23);
		contentPane.add(btn);
	}
	
	public void myexecute() {
		String com = "";
		String mine = tfMine.getText();
		String result = "";
		
		double rnd = Math.random();
		
		if(rnd > 0.5) {
			com = "È¦";
		}else {
			com = "Â¦";
		}
		
		if(com.equals(mine)) {
			result = "ÀÌ±è";
		}else {
			result = "Áü";
		}
		
		tfCom.setText(com);
		tfResult.setText(result);
		
	}
	
	
	
}
