package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		setBackground(Color.BLUE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 279, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_2 = new JButton("2");
		btn_2.setBounds(70, 69, 60, 60);
		contentPane.add(btn_2);
		
		JButton btn_1 = new JButton("1");
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_1.setBounds(10, 69, 60, 60);
		contentPane.add(btn_1);
		
		JButton btn_3 = new JButton("3");
		btn_3.setBounds(130, 69, 60, 60);
		contentPane.add(btn_3);
		
		JButton btn_add = new JButton("+");
		btn_add.setBounds(190, 69, 60, 60);
		contentPane.add(btn_add);
		
		JButton btn_4 = new JButton("4");
		btn_4.setBounds(10, 129, 60, 60);
		contentPane.add(btn_4);
		
		JButton btn_5 = new JButton("5");
		btn_5.setBounds(70, 129, 60, 60);
		contentPane.add(btn_5);
		
		JButton btn_6 = new JButton("6");
		btn_6.setBounds(130, 129, 60, 60);
		contentPane.add(btn_6);
		
		JButton btn_sub = new JButton("-");
		btn_sub.setBounds(190, 129, 60, 60);
		contentPane.add(btn_sub);
		
		JButton btn_7 = new JButton("7");
		btn_7.setBounds(10, 189, 60, 60);
		contentPane.add(btn_7);
		
		JButton btn_8 = new JButton("8");
		btn_8.setBounds(70, 189, 60, 60);
		contentPane.add(btn_8);
		
		JButton btn_9 = new JButton("9");
		btn_9.setBounds(130, 189, 60, 60);
		contentPane.add(btn_9);
		
		JButton btn_mul = new JButton("X");
		btn_mul.setBounds(190, 189, 60, 60);
		contentPane.add(btn_mul);
		
		JButton btn_clear = new JButton("C");
		btn_clear.setBounds(10, 249, 60, 60);
		contentPane.add(btn_clear);
		
		JButton btn_0 = new JButton("0");
		btn_0.setBounds(70, 249, 60, 60);
		contentPane.add(btn_0);
		
		JButton btn_equal = new JButton("=");
		btn_equal.setBounds(130, 249, 60, 60);
		contentPane.add(btn_equal);
		
		JButton btn_div = new JButton("/");
		btn_div.setBounds(190, 249, 60, 60);
		contentPane.add(btn_div);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 25, 240, 33);
		contentPane.add(textArea);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
