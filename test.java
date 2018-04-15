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

public class GUIServer {
	public static void main(String args[]){
		MyServerFrame frame = new MyServerFrame();
	}
}

class MyServerFrame extends JFrame{
	Label label;
	JTextArea textArea;
	JTextArea textsend;
	JButton button2,button3,button4;
	JScrollPane jsPane;
	JScrollPane jsSendPane;
	ServerSocket serverSocket;
	Socket socket;
	InputStream readInputStream;
	OutputStream writeOutputStream;
	String str;

	
	MyServerFrame(){
		Init();								//初始化控件
		this.setBounds(700, 200, 260, 470);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			serverSocket = new ServerSocket(9091);
			socket = serverSocket.accept();
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
	void Init(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());//采用流式布局
		
		label = new Label("聊天对话框");
		textArea = new JTextArea(16, 20);
		jsPane = new JScrollPane(textArea);
		textsend = new JTextArea(3,20);
		jsSendPane = new JScrollPane(textsend);
		button2 = new JButton("发送");
		button3 = new JButton("关闭");
		button4 = new JButton("更新消息");
		
		panel.add(label);
		panel.add(jsPane);
		panel.add(jsSendPane);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		
		add(panel);
		

		
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				str = textsend.getText();
				try {
					writeOutputStream.write(str.getBytes());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textArea.append("我说："+str+"\r\n");
				
				
			
			}
		});
	
			
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					serverSocket.close();
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
					textArea.append("他说："+str+"\r\n");
				}
			}
		});
		
	}
	
	
}


