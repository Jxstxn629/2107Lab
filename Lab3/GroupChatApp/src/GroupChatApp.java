import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class GroupChatApp extends JFrame {

	private JPanel contentPane;
	private JTextField txtGroupIp;
	private JTextField textField;
	MulticastSocket multicastSocket = null;
	InetAddress multicastGroup = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupChatApp frame = new GroupChatApp();
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
	public GroupChatApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name:");
		lblNewLabel.setBounds(10, 10, 70, 13);
		contentPane.add(lblNewLabel);
		
		String username = "";
		JTextField lblUserValue = new JTextField();
		lblUserValue.setBounds(90, 10, 131, 13);
		contentPane.add(lblUserValue);
		
		JLabel lblNewLabel_1 = new JLabel("Group IP");
		lblNewLabel_1.setBounds(10, 33, 55, 13);
		contentPane.add(lblNewLabel_1);
		
		txtGroupIp = new JTextField();
		txtGroupIp.setBounds(85, 30, 96, 19);
		contentPane.add(txtGroupIp);
		txtGroupIp.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 73, 426, 135);
		contentPane.add(textArea);
		
		JButton btnLeave = new JButton("Leave");
		btnLeave.setEnabled(false);
		
		
		JButton btnSend = new JButton("Send");
		JButton btnJoin = new JButton("Join");
		JButton btnUpdate = new JButton("Update");
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String msg = lblUserValue.getText() + ": is leaving";
					byte[] buf = msg.getBytes();
					DatagramPacket dgpSend = new DatagramPacket(buf, buf.length, multicastGroup, 6789);
					multicastSocket.send(dgpSend);
					multicastSocket.leaveGroup(multicastGroup);
					btnJoin.setEnabled(true);
					btnSend.setEnabled(false);
					btnLeave.setEnabled(false);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		
		btnLeave.setBounds(324, 29, 85, 21);
		contentPane.add(btnLeave);
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String msg = textField.getText();
					msg = lblUserValue.getText() + ": " + msg;
					byte[] buf = msg.getBytes();
					DatagramPacket dgpSend = new DatagramPacket(buf, buf.length, multicastGroup, 6789);
					multicastSocket.send(dgpSend);
				}catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSend.setEnabled(false);
		btnSend.setBounds(351, 224, 85, 21);
		contentPane.add(btnSend);
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					multicastGroup = InetAddress.getByName(txtGroupIp.getText());
					multicastSocket = new MulticastSocket(6789);
					multicastSocket.joinGroup(multicastGroup);
					String message = lblUserValue.getText() + " joined";
					byte[] buf = message.getBytes();
					DatagramPacket dgpConnected = new DatagramPacket(buf, buf.length, multicastGroup, 6789);
					multicastSocket.send(dgpConnected);
					
					new Thread(new Runnable() {
						@Override
						public void run() {
							byte buf1[] = new byte[1000];
							DatagramPacket dgpReceived = new DatagramPacket(buf1, buf1.length);
							while (true) {
								try {
									multicastSocket.receive(dgpReceived);
									byte[] receivedData = dgpReceived.getData();
									int length = dgpReceived.getLength();
									String msg = new String(receivedData, 0, length);
									textArea.append(msg + "\n");
								}
								catch (IOException ex) {
									ex.printStackTrace();
								}
							}
						}
					}).start();
					btnJoin.setEnabled(false);
					btnLeave.setEnabled(true);
					btnSend.setEnabled(true);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnJoin.setBounds(208, 29, 85, 21);
		contentPane.add(btnJoin);
		
		btnUpdate.setBounds(208, 10, 85, 21);
		contentPane.add(btnUpdate);
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Message:");
		lblNewLabel_2.setBounds(10, 228, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(65, 225, 270, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
	}
}
