package ICQ;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class GUIClient {
	public static void main(String args[]){
		MyClentFrame frame = new MyClentFrame();
	}
}

class MyClentFrame extends JFrame{
	JTextField textField;
	JTextArea textArea;
	JTextArea textsend;
	Label label;
	JButton button1,button2,button3,button4;
	JScrollPane jsPane;
	JScrollPane jsSendPane;
	Socket socket ;
	InputStream readInputStream;
	OutputStream writeOutputStream;
	String str;

	
	MyClentFrame(){
		Init();								//��ʼ���ؼ�
		this.setBounds(700, 200, 260, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	void Init(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());//������ʽ����
		
		label = new Label("����IP��ַ");
		textField = new JTextField(15);
		button1 = new JButton("ȷ��");
		textArea = new JTextArea(16, 20);
		jsPane = new JScrollPane(textArea);
		textsend = new JTextArea(3,20);
		jsSendPane = new JScrollPane(textsend);
		button2 = new JButton("����");
		button3 = new JButton("�ر�");
		button4 = new JButton("������Ϣ");
		
		panel.add(label);
		panel.add(textField);
		panel.add(button1);
		panel.add(jsPane);
		panel.add(jsSendPane);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		
		add(panel);
		
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String IP = textField.getText();
				try {
					socket = new Socket(IP,9091);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					readInputStream = socket.getInputStream();
					writeOutputStream = socket.getOutputStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		

		

		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String string = textsend.getText();
	
				try {
					writeOutputStream.write(string.getBytes());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textArea.append("��˵��"+string+"\r\n");
			}
		});

			
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		
		button4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				byte buf[] = new byte[1024];
				int len=0;
				try {
					len = readInputStream.read(buf);
				} catch (IOException er) {
					// TODO Auto-generated catch block
					er.printStackTrace();
				}
			    str = new String(buf,0,len);
			    
				if (!str.isEmpty()) {
					textArea.append("��˵��"+str+"\r\n");
				}
			}
		});
	}

}









