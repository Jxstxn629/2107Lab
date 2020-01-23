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
import java.rmi.Naming;
import java.rmi.RemoteException;

import interfaces.CalculatorIn;

public class GUI extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();
	String arg1 = "";
	String command = "";
	String arg2 = "";
	String result = "";
	static CalculatorIn cal;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					cal = (CalculatorIn) Naming.lookup( "rmi://localhost:1099/StudentService"); 
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
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 279, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 25, 240, 33);
		contentPane.add(textArea);
		JButton btn_2 = new JButton("2");
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(command.equals(""))
				{
					arg1 = arg1 + "2";
				}
				else
				{
					arg2 = arg2 + "2";
				}
				textArea.setText(arg1+command+arg2+result);
			}
		});
		btn_2.setBounds(70, 69, 60, 60);
		contentPane.add(btn_2);
		
		JButton btn_1 = new JButton("1");
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(command.equals(""))
				{
					arg1 = arg1 + "1";
				}
				else
				{
					arg2 = arg2 + "1";
				}
				textArea.setText(arg1+command+arg2+result);
			}
		});
		btn_1.setBounds(10, 69, 60, 60);
		contentPane.add(btn_1);
		
		JButton btn_3 = new JButton("3");
		btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(command.equals(""))
				{
					arg1 = arg1 + "3";
				}
				else
				{
					arg2 = arg2 + "3";
				}
				textArea.setText(arg1+command+arg2+result);
			}
		});
		btn_3.setBounds(130, 69, 60, 60);
		contentPane.add(btn_3);
		
		JButton btn_add = new JButton("+");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				command = "+";
				textArea.setText(arg1+command+arg2+result);
			}
		});
		btn_add.setBounds(190, 69, 60, 60);
		contentPane.add(btn_add);
		
		JButton btn_4 = new JButton("4");
		btn_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(command.equals(""))
				{
					arg1 = arg1 + "4";
				}
				else
				{
					arg2 = arg2 + "4";
				}
				textArea.setText(arg1+command+arg2+result);
			}
		});
		btn_4.setBounds(10, 129, 60, 60);
		contentPane.add(btn_4);
		
		JButton btn_5 = new JButton("5");
		btn_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(command.equals(""))
				{
					arg1 = arg1 + "5";
				}
				else
				{
					arg2 = arg2 + "5";
				}
				textArea.setText(arg1+command+arg2+result);
			}
		});
		btn_5.setBounds(70, 129, 60, 60);
		contentPane.add(btn_5);
		
		JButton btn_6 = new JButton("6");
		btn_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(command.equals(""))
				{
					arg1 = arg1 + "6";
				}
				else
				{
					arg2 = arg2 + "6";
				}
				textArea.setText(arg1+command+arg2+result);
			}
		});
		btn_6.setBounds(130, 129, 60, 60);
		contentPane.add(btn_6);
		
		JButton btn_sub = new JButton("-");
		btn_sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				command = "-";
				textArea.setText(arg1+command+arg2+result);
			}
		});
		btn_sub.setBounds(190, 129, 60, 60);
		contentPane.add(btn_sub);
		
		JButton btn_7 = new JButton("7");
		btn_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(command.equals(""))
				{
					arg1 = arg1 + "7";
				}
				else
				{
					arg2 = arg2 + "7";
				}
				textArea.setText(arg1+command+arg2+result);
			}
		});
		btn_7.setBounds(10, 189, 60, 60);
		contentPane.add(btn_7);
		
		JButton btn_8 = new JButton("8");
		btn_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(command.equals(""))
				{
					arg1 = arg1 + "8";
				}
				else
				{
					arg2 = arg2 + "8";
				}
				textArea.setText(arg1+command+arg2+result);
			}
		});
		btn_8.setBounds(70, 189, 60, 60);
		contentPane.add(btn_8);
		
		JButton btn_9 = new JButton("9");
		btn_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(command.equals(""))
				{
					arg1 = arg1 + "9";
				}
				else
				{
					arg2 = arg2 + "9";
				}
				textArea.setText(arg1+command+arg2+result);
			}
		});
		btn_9.setBounds(130, 189, 60, 60);
		contentPane.add(btn_9);
		
		JButton btn_mul = new JButton("X");
		btn_mul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				command = "X";
				textArea.setText(arg1+command+arg2+result);
			}
		});
		btn_mul.setBounds(190, 189, 60, 60);
		contentPane.add(btn_mul);
		
		JButton btn_clear = new JButton("C");
		btn_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arg1 = "";
				arg2 = "";
				command = "";
				result = "";
				textArea.setText(arg1+command+arg2+result);
			}
		});
		btn_clear.setBounds(10, 249, 60, 60);
		contentPane.add(btn_clear);
		
		JButton btn_0 = new JButton("0");
		btn_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(command.equals(""))
				{
					arg1 = arg1 + "0";
				}
				else
				{
					arg2 = arg2 + "0";
				}
				textArea.setText(arg1+command+arg2+result);
			}
		});
		btn_0.setBounds(70, 249, 60, 60);
		contentPane.add(btn_0);
		
		JButton btn_equal = new JButton("=");
		btn_equal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arg1.equals("") || arg2.equals("") || command.equals(""))
				{
					arg1 = "";
					arg2 = "";
					command = "";
					result = "Error";
				}
				else
				{
					Double a = Double.parseDouble(arg1);
					Double b = Double.parseDouble(arg2);
					
					try
					{
						if(command.equals("+"))
						{
							result = "= " + cal.add(a, b);
						}
						else if(command.equals("-"))
						{
							result = "= " + cal.sub(a, b);
						}
						else if(command.equals("X"))
						{
							result = "= " + cal.mul(a, b);
						}
						else
						{
							if(b == 0)
							{
								arg1 = "";
								arg2 = "";
								command = "";
								result = "Error";
							}
							else
							{
								result = "= " + cal.div(a, b);
							}
							
						}
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
					}
				}
				textArea.setText(arg1+command+arg2+result);
				
				
				
			}
		});
		btn_equal.setBounds(130, 249, 60, 60);
		contentPane.add(btn_equal);
		
		JButton btn_div = new JButton("/");
		btn_div.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				command = "/";
				textArea.setText(arg1+command+arg2+result);
			}
		});
		btn_div.setBounds(190, 249, 60, 60);
		contentPane.add(btn_div);
		
		
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
