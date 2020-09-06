package br.com.drakenetwork.hub.servers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class lobby_5
{

	private int online = 0;
	private int max = 0;
	private boolean status = false;
	private String address  = "66.70.180.225";
	private int port = 25570;
	private String similaraddress = address;
	private int similarport = port;
	boolean similar = false;
	
	public lobby_5() {
		try {
			@SuppressWarnings("resource")
			Socket s1 = new Socket(address, port);
			
			DataOutputStream out1 = new DataOutputStream(s1.getOutputStream());
			DataInputStream inp1 = new DataInputStream(s1.getInputStream());
			
			out1.write(0xFE);
			
			int b1;
			StringBuffer str1 = new StringBuffer();
			while ((b1 = inp1.read()) != -1) 
			{
				if (b1 != 0 && b1 > 16 && b1 != 255 && b1 != 23 && b1 != 24) {
					str1.append((char)b1);
				}
			}
	
			String[] data1 = str1.toString().split("§");
			this.online = Integer.parseInt(data1[1]);
			this.max = Integer.parseInt(data1[2]);
			
			this.status = true;
		} catch (Exception ex) {}
	}

	public int getPlayers() {
		if (status == false) {
			return 0;
		} else {
			return this.online;
		}
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public boolean isSimilar(String ip, int porta) {
		if (this.similaraddress.equals(ip) && this.similarport == porta) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getMaxPlayers() {
		if (status == false) {
			return 0;
		} else {
			return this.max;
		}
	}
}
