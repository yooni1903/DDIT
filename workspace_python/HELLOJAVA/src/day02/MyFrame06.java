package day02;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyFrame06 extends JFrame {

	private JPanel contentPane;
	private JTextField tfDan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame06 frame = new MyFrame06();
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
	public MyFrame06() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea ta = new JTextArea();
		ta.setBounds(12, 10, 243, 241);
		contentPane.add(ta);
		
		tfDan = new JTextField();
		tfDan.setBounds(271, 12, 116, 21);
		contentPane.add(tfDan);
		tfDan.setColumns(10);
		
		JButton btn = new JButton("\uCD9C\uB825");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String dan = tfDan.getText();
				int int_dan = Integer.parseInt(dan);
				
				String mul = "";
				for(int i = 1; i <= 9; i++) {
					mul += dan +" * " + i + " = " + int_dan * i + "\n";
				}
				ta.setText(mul);
				
			}
		});
		btn.setBounds(281, 43, 97, 23);
		contentPane.add(btn);
	}

}
