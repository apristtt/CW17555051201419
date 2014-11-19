package com.example.cw17555051201419;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class InsertDeleteTask extends AsyncTask<Void, Void, String> {

	Context mContext;
	String mUrl;
	
	public InsertDeleteTask(Context context, String url, List<NameValuePair> params){
		super();
		mContext = context;
		mUrl = url;
		if(params != null){ // if list have data(name, date)
			String paramString = URLEncodedUtils.format(params, "utf-8");
			mUrl += "?"+paramString;
		}
	}
	
	@Override
	protected String doInBackground(Void... params) {
		// TODO Auto-generated method stub
		String jsonString = JsonHttp.makeHttpRequest(mUrl);
		return jsonString;
	}

	@Override
	protected void onPostExecute(String jsonString) {
		// TODO Auto-generated method stub
//		super.onPostExecute(result);
		try {
			JSONObject json = new JSONObject(jsonString);
			String msg = json.getString("message");
			Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
