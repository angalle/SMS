package univ.sm.connect;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class GCMConnection {
	String regid="";
	
	public GCMConnection(String regid) {
		this.regid = regid;
	}
	public void HttpPostConnect(String url) {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost();
			URI uri = URI.create(url);
			request.setURI(uri);
			
			List<NameValuePair> postData = new ArrayList<NameValuePair>(1);
			postData.add(new BasicNameValuePair("regid", regid));
			request.setEntity(new UrlEncodedFormEntity(postData));			
			HttpResponse response = client.execute(request);
			// respose�� �����Ͱ� ����
			 String responseString = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
		} catch (Exception e) {
			Log.e("internet not", "connecting fail");
		}
	}
}
