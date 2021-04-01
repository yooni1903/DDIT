package day02;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class MyFrame09 extends JFrame {

	private JPanel contentPane;
	private JTextField tf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame09 frame = new MyFrame09();
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
	public MyFrame09() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 328, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tf = new JTextField();
		tf.setHorizontalAlignment(SwingConstants.RIGHT);
		tf.setBounds(35, 30, 228, 21);
		contentPane.add(tf);
		tf.setColumns(10);
		
		JButton btn1 = new JButton("1");
		btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickNumber(e);
				insertNum(btn1);
			}
		});
		btn1.setBounds(35, 81, 65, 23);
		contentPane.add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				insertNum(btn2);
			}
		});
		btn2.setBounds(112, 81, 65, 23);
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				insertNum(btn3);
			}
		});
		btn3.setBounds(189, 81, 74, 23);
		contentPane.add(btn3);
		
		JButton btn4 = new JButton("4");
		btn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				insertNum(btn4);
			}
		});
		btn4.setBounds(35, 124, 65, 23);
		contentPane.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				insertNum(btn5);
			}
		});
		btn5.setBounds(112, 124, 65, 23);
		contentPane.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				insertNum(btn6);
			}
		});
		btn6.setBounds(189, 124, 74, 23);
		contentPane.add(btn6);
		
		JButton btn7 = new JButton("7");
		btn7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				insertNum(btn7);
			}
		});
		btn7.setBounds(35, 170, 65, 23);
		contentPane.add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				insertNum(btn8);
			}
		});
		btn8.setBounds(112, 170, 65, 23);
		contentPane.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				insertNum(btn9);
			}
		});
		btn9.setBounds(189, 170, 74, 23);
		contentPane.add(btn9);
		
		JButton btn0 = new JButton("0");
		btn0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				insertNum(btn0);
			}
		});
		btn0.setBounds(35, 218, 65, 23);
		contentPane.add(btn0);
		
		JButton btnCall = new JButton("Call");
		btnCall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String num = tf.getText();
				JOptionPane.showMessageDialog(null, num + "으로 발신중 ...");
				
			}
		});
		btnCall.setBounds(112, 218, 151, 23);
		contentPane.add(btnCall);
	}
	
	public void insertNum(JButton btn) {
		String num = tf.getText();
		num += btn.getText();
		tf.setText(num);
	}
	
	// 선생님 메서드
	public void clickNumber(MouseEvent e) {
		String old = tf.getText();
		JButton temp = (JButton) e.getSource();		// 이 이벤트가 실행된 오브젝트를 주는 메서드
		String sem = temp.getText();
		tf.setText(old+sem);
	}
	
	public void myalert() {
		String old = tf.getText();
		JOptionPane.showMessageDialog(null, "calling" + old);
	}
	
	

}
