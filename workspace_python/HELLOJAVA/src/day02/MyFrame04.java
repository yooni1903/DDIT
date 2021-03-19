package day02;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyFrame04 extends JFrame {

	private JPanel contentPane;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame04 frame = new MyFrame04();
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
	public MyFrame04() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tf1 = new JTextField();
		tf1.setBounds(12, 112, 60, 21);
		contentPane.add(tf1);
		tf1.setColumns(10);
		
		JLabel lbl = new JLabel("\uC5D0\uC11C");
		lbl.setBounds(74, 115, 30, 15);
		contentPane.add(lbl);
		
		tf2 = new JTextField();
		tf2.setBounds(102, 112, 60, 21);
		contentPane.add(tf2);
		tf2.setColumns(10);
		
		JButton btn = new JButton("\uAE4C\uC9C0\uC758 \uD569\uC740");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String t1 = tf1.getText();
				String t2 = tf2.getText();
				int int_t1 = Integer.parseInt(t1);
				int int_t2 = Integer.parseInt(t2);
				
				int sum = 0;
				for(int i = int_t1; i <= int_t2; i++) {
					sum += i;
				}
				
				tf3.setText(sum + "");
			}
		});
		btn.setBounds(174, 111, 106, 23);
		contentPane.add(btn);
		
		tf3 = new JTextField();
		tf3.setBounds(292, 112, 116, 21);
		contentPane.add(tf3);
		tf3.setColumns(10);
	}

}
