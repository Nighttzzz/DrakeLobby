package br.com.drakenetwork.hub.servers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class wither {


	private int online = 0;
	private int max = 0;
	private boolean status = false;
	private String address  = "144.217.11.142";
	private int port = 25591;
	private String similaraddress = "";
	private int similarport = 0;
	boolean similar = false;
	
	public wither() {
		try {
			@SuppressWarnings("resource")
			Socket s1 = new Socket(this.address, this.port);
			
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
			
			this.similaraddress = s1.getLocalAddress().getHostAddress();
			this.similarport = s1.getLocalPort();
			this.status = true;
		} catch (Exception ex) {}
	}

	public int getPlayers() {
		return this.online;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public boolean isSimilar(String ip, int porta) {
		if (ip.equals(this.similaraddress) && porta == this.similarport) {
			similar = true;
		} else {
			similar = false;
		}
		return this.similar;
	}
	
	public int getMaxPlayers() {
		return this.max;
	}
}
