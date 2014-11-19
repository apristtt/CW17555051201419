package com.example.cw17555051201419;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.PublicKey;

public class JsonHttp {

	public static String makeHttpRequest(String url){
		String result = "";
		
		try {
			URL u = new URL(url);
			//	Connect
			HttpURLConnection con = (HttpURLConnection) u.openConnection();
			//	Read
			result = readStream(con.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public static String readStream(InputStream in){
		BufferedReader reader = null; //	Collect InputStream in
		StringBuilder sb = new StringBuilder();	//	Collect readed data from DB
		try {
			//	collect "in" to reader for use method readLine()  
			reader = new BufferedReader(new InputStreamReader(in));
			String line;
			//	read per row until end of data to read
			while ((line = reader.readLine()) != null){
				sb.append(line+"\n");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) { // if reader has data
				try {
					reader.close();	// close reader
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return sb.toString();
	}
}
