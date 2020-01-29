import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.awt.event.ActionEvent;

public class GroupChatApp extends JFrame {

	private JPanel contentPane;
	private JTextField UsertextField;
	private JTextField GrouptextField;
	private JTextField JointextField;
	private JTextField textField;
	MulticastSocket mainMulticastSocket = null;
	MulticastSocket GroupmulticastSocket = null;
	InetAddress mainMulticastAddr = null;
	InetAddress GroupmulticastAddr = null;
	HashMap<String, Boolean> AddressList = new HashMap<String, Boolean>();
	HashMap<String, String> GroupList = new HashMap<String, String>();
	String username = "Default User";

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
		setBounds(100, 100, 617, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 100, 583, 264);
		contentPane.add(textArea);
		
		//populate AddressList with addresses to use
		generator();
		
		//Connect to main socket on launch. Start new infinite thread to listen for message
		try {
			mainMulticastSocket = new MulticastSocket(5555);
			mainMulticastAddr = InetAddress.getByName("228.5.5.5");
			mainMulticastSocket.joinGroup(mainMulticastAddr);
			String message = username + " is online";
			byte[] buf = message.getBytes();
			DatagramPacket dgpConnected = new DatagramPacket(buf, buf.length, mainMulticastAddr, 5555);
			mainMulticastSocket.send(dgpConnected);
			new Thread(new Runnable() {
				@Override
				public void run() {
					byte buf1[] = new byte[1000];
					DatagramPacket dgpReceived = new DatagramPacket(buf1, buf1.length);
					while (true) {
						try {
							mainMulticastSocket.receive(dgpReceived);
							byte[] receivedData = dgpReceived.getData();
							int length = dgpReceived.getLength();
							String msg = new String(receivedData, 0, length);
							if(msg.contains("GroupRequest"))
							{
								String groupname = msg.substring(13);
								System.out.println("Finding Group: " + groupname);
								String groupaddr = GroupList.get(groupname);
								if(groupaddr != null)
								{
									String message = "GroupFound " + groupname + "->" + groupaddr;
									byte[] buf = message.getBytes();
									DatagramPacket dgpConnected = new DatagramPacket(buf, buf.length, mainMulticastAddr, 5555);
									mainMulticastSocket.send(dgpConnected);
								}
								
							}
							else if(msg.contains("GroupFound"))
							{
								String response = msg.substring(11);
								String[] arr = response.split("->",2);
								System.out.println("Found Group " + arr[0] + " at " + arr[1]);
								
								if(!GroupList.containsKey(arr[0]))
								{
									System.out.println("Do i enter here?");
									GroupList.put(arr[0],arr[1]);
								}
							}
							else
							{
								textArea.append(msg + "\n");
							}
							
						}
						catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}
			}).start();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		
		
		
		JLabel lblNewLabel = new JLabel("User Name:");
		lblNewLabel.setBounds(10, 10, 100, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Create Group:");
		lblNewLabel_1.setBounds(10, 40, 100, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Join Group:");
		lblNewLabel_2.setBounds(10, 70, 100, 20);
		contentPane.add(lblNewLabel_2);
		
		UsertextField = new JTextField();
		UsertextField.setBounds(100, 11, 100, 20);
		UsertextField.setText(username);
		contentPane.add(UsertextField);
		UsertextField.setColumns(10);
		
		GrouptextField = new JTextField();
		GrouptextField.setBounds(100, 40, 100, 20);
		contentPane.add(GrouptextField);
		GrouptextField.setColumns(10);
		
		JointextField = new JTextField();
		JointextField.setBounds(100, 70, 100, 20);
		contentPane.add(JointextField);
		JointextField.setColumns(10);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("Message:");
		lblNewLabel_3.setBounds(10, 382, 100, 20);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(100, 383, 380, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		JButton btnCreate = new JButton("Create");
		JButton btnJoin = new JButton("Join");
		JButton btnSend = new JButton("Send");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = UsertextField.getText();
			}
		});
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String groupname = GrouptextField.getText();
				String groupaddr = "";
				Set set = AddressList.entrySet();
			    Iterator iterator = set.iterator();
			    while(iterator.hasNext()) {
			         Map.Entry mentry = (Map.Entry)iterator.next();
			         Boolean available = (Boolean) mentry.getValue();
			         if(available)
			         {
			        	groupaddr = (String) mentry.getKey();
			        	break;
			         }
			    }
			    GroupList.put(groupname, groupaddr);
			    try {
					GroupmulticastAddr = InetAddress.getByName(groupaddr);
					GroupmulticastSocket = new MulticastSocket(6789);
					GroupmulticastSocket.joinGroup(GroupmulticastAddr);
					String message = username + " joined";
					byte[] buf = message.getBytes();
					DatagramPacket dgpConnected = new DatagramPacket(buf, buf.length, GroupmulticastAddr, 6789);
					GroupmulticastSocket.send(dgpConnected);
					
					new Thread(new Runnable() {
						@Override
						public void run() {
							byte buf1[] = new byte[1000];
							DatagramPacket dgpReceived = new DatagramPacket(buf1, buf1.length);
							while (true) {
								try {
									GroupmulticastSocket.receive(dgpReceived);
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
					btnSend.setEnabled(true);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			    
			}
		});
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String find = "GroupRequest " + JointextField.getText();
					byte[] buf1 = find.getBytes();
					DatagramPacket dgpConnected2 = new DatagramPacket(buf1, buf1.length, mainMulticastAddr, 5555);
					mainMulticastSocket.send(dgpConnected2);
					while(!GroupList.containsKey(JointextField.getText()));
					String groupname = GroupList.get(JointextField.getText());
					System.out.println(groupname);
					GroupmulticastAddr = InetAddress.getByName(groupname);
					GroupmulticastSocket = new MulticastSocket(6789);
					GroupmulticastSocket.joinGroup(GroupmulticastAddr);
					String message = username + " joined";
					byte[] buf = message.getBytes();
					DatagramPacket dgpConnected = new DatagramPacket(buf, buf.length, GroupmulticastAddr, 6789);
					GroupmulticastSocket.send(dgpConnected);
					
					new Thread(new Runnable() {
						@Override
						public void run() {
							byte buf1[] = new byte[1000];
							DatagramPacket dgpReceived = new DatagramPacket(buf1, buf1.length);
							while (true) {
								try {
									GroupmulticastSocket.receive(dgpReceived);
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
					//btnJoin.setEnabled(false);
					//btnLeave.setEnabled(true);
					btnSend.setEnabled(true);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String msg = textField.getText();
					msg = username + ": " + msg;
					byte[] buf = msg.getBytes();
					DatagramPacket dgpSend = new DatagramPacket(buf, buf.length, GroupmulticastAddr, 6789);
					GroupmulticastSocket.send(dgpSend);
				}catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		btnUpdate.setBounds(230, 10, 85, 21);
		contentPane.add(btnUpdate);
		
		
		btnCreate.setBounds(230, 40, 85, 21);
		contentPane.add(btnCreate);
		
		
		btnJoin.setBounds(230, 70, 85, 21);
		contentPane.add(btnJoin);
		
		
		btnSend.setBounds(490, 382, 85, 21);
		contentPane.add(btnSend);
	}
	
	public void generator() {
		AddressList.put("225.0.0.1", true);
		AddressList.put("225.0.0.2", true);
		AddressList.put("225.0.0.3", true);
		AddressList.put("225.0.0.4", true);
		AddressList.put("225.0.0.5", true);
		AddressList.put("225.0.0.6", true);
		AddressList.put("225.0.0.7", true);
		AddressList.put("225.0.0.8", true);
		AddressList.put("225.0.0.9", true);
		
	}

}
