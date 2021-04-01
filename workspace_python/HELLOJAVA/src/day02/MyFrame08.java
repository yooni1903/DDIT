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

public class MyFrame08 extends JFrame {

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
					MyFrame08 frame = new MyFrame08();
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
	public MyFrame08() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCom = new JLabel("\uCEF4\uD4E8\uD130");
		lblCom.setBounds(56, 56, 57, 15);
		contentPane.add(lblCom);
		
		JLabel lblMine = new JLabel("\uB098");
		lblMine.setBounds(56, 108, 57, 15);
		contentPane.add(lblMine);
		
		tfCom = new JTextField();
		tfCom.setBounds(123, 53, 116, 21);
		contentPane.add(tfCom);
		tfCom.setColumns(10);
		
		tfMine = new JTextField();
		tfMine.setBounds(123, 105, 116, 21);
		contentPane.add(tfMine);
		tfMine.setColumns(10);
		
		JLabel lblResult = new JLabel("\uACB0\uACFC");
		lblResult.setBounds(56, 155, 57, 15);
		contentPane.add(lblResult);
		
		tfResult = new JTextField();
		tfResult.setBounds(123, 152, 116, 21);
		contentPane.add(tfResult);
		tfResult.setColumns(10);
		
		JButton btn = new JButton("\uB300\uACB0");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				double rnd = Math.random();
				
				String com = "";
				
				if(rnd > 0.66) {
					com = "가위";
				}else if(rnd > 0.33) {
					com = "바위";
				}else {
					com = "보";
				}
				
				String mine= tfMine.getText();
				String result = "";
				
				if(com.equals("가위") && mine.equals("바위") || com.equals("바위") && mine.equals("보") || com.equals("보") && mine.equals("가위")) {
					result = "이겼습니다!";
				}else if(com.equals(mine)) {
					result = "비겼습니다";
				}else {
					result = "졌습니다ㅜ";
				}
				
				tfCom.setText(com);
				tfResult.setText(result);
				
			}
		});
		btn.setBounds(56, 201, 183, 23);
		contentPane.add(btn);
	}

}
