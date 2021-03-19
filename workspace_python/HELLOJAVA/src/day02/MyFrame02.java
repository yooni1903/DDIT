package day02;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class MyFrame02 extends JFrame {

	private JPanel contentPane;
	private JTextField tf;
//	int count = 1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame02 frame = new MyFrame02();
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
	public MyFrame02() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl = new JLabel("1");
		lbl.setBounds(76, 115, 57, 15);
		contentPane.add(lbl);
		
		JButton btn = new JButton("Increase");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				count++;
//				lbl.setText(count+"");
				String a = lbl.getText();
				int int_a = Integer.parseInt(a);
				int_a++;
				lbl.setText(int_a+"");
				
				String b = tf.getText();
				int int_b = Integer.parseInt(b);
				int_b++;
				tf.setText(int_b+"");
			}
		});
		btn.setBounds(178, 111, 97, 23);
		contentPane.add(btn);
		
		tf = new JTextField();
		tf.setText("2");
		tf.setBounds(44, 140, 116, 21);
		contentPane.add(tf);
		tf.setColumns(10);
	}
}
