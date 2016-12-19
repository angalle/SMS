package univ.sm.connect;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;

import univ.sm.data.Shuttle;


public class Connection {
	String addr = "";
	StringBuffer sb = null;
	private int arryrows = 8; // ���
	private ArrayList<Shuttle> ShuttleArr = new ArrayList<Shuttle>();// ��ƲŬ����
																		// �����ͷ�
																		// �簡��.
	ArrayList<String> busarr = new ArrayList<String>(); // ���ͳݿ��� �迭�� �޾ƿ��� �κ�

	public Connection(String str) {
		addr = str;
	}

	public ArrayList<Shuttle> getBusArray() {
		return ShuttleArr;
	}

	public void HttpConnect() { // when method call, return
								// ArrayList<String>(->busStrArr) and return
								// String[][](-> busStrArr)
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			URI uri = URI.create(addr);
			request.setURI(uri);
			HttpResponse response = client.execute(request);
			// respose�� �����Ͱ� ����
			final int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				Log.i("what?", "Error");
				return;
			}
			
			BufferedReader in = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent(), "utf-8"));

			sb = new StringBuffer("");
			String line = "";
			String st = "";

			if ((line = in.readLine()) != null) {
				String html = "", vNum = "";
				html = line.toString();

				if (html.equals("error"))
					throw new Exception();// �ִµ� �ƹ� ���� ���� ��
				if (html.equals("false"))
					throw new Exception();// ���� ���� ��û �� ��

				int i = 0;
				while (html.length() > i) {
					if (html.charAt(i) != ',')
						st += html.charAt(i);
					else {
						busarr.add(st);
						st = "";
					}
					i++;
				}
			}
			busarr.add(st);
			ShuttleArr = busArrayMake(busarr.size(), busarr);

		} catch (Exception e) {
			Log.i("internet connecting fail", "connecting fail");
			Shuttle s = new Shuttle(); // 초기화는 그대로
			ShuttleArr.add(s);
		}
	}

	private ArrayList<Shuttle> busArrayMake(int r, ArrayList<String> list) // change)
	{
		String test = "";
		int cols, rows = r;// r-1 ���� ������ row�� 1�۱� ����.
		ArrayList<Shuttle> virtualArray = new ArrayList<Shuttle>();
		for (int y = 0; y < rows; y++) // ��
		{
			Shuttle s = new Shuttle();
			arryrows = 0;
			cols = list.get(y).length(); // �� ���� ũ�⺰�� �ʱ�ȭ
			for (int i = 0; i < cols; i++) // ��
			{
				try {
					// �ܾ� ���� sperator
					if (list.get(y).charAt(i) == ' ') {
						switch (arryrows) {
						case 0:
							s.No = test;
							break;
						case 1:
							s.b[0] = test;
							break;
						case 2:
							s.b[1] = test;
							break;
						case 3:
							s.b[2] = test;
							break;
						case 4:
							s.b[3] = test;
							break;
						case 5:
							s.b[4] = test;
							break;
						case 6:
							s.b[5] = test;
							break;
						}
						test = "";
						arryrows++;
						continue;
					}
					test += "" + list.get(y).charAt(i); // �ѱ��ھ� �־
				} catch (Exception e) {
					Log.i("tag", "" + arryrows + e);
				}
			}
			virtualArray.add(s);
		}
		return virtualArray;
	}

	public String HttpInfoConnect() { // when method call, return
		// ArrayList<String>(->busStrArr) and return
		// String[][](-> busStrArr)
		String info = "";
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			URI uri = URI.create(addr);
			request.setURI(uri);
			HttpResponse response = client.execute(request);
			// respose�� �����Ͱ� ����
			final int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				Log.i("what?", "Error");
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent(), "utf-8"));

			sb = new StringBuffer("");
			String line = "";
			String st = "";

			if ((line = in.readLine()) != null) {
				String html = "", vNum = "";
				html = line.toString().replace(",", "\n").replace(',', '\n');

				if (html.equals("error"))
					throw new Exception();// �ִµ� �ƹ� ���� ���� ��
				if (html.equals("false"))
					throw new Exception();// ���� ���� ��û �� ��
				info = html;
			}

		} catch (Exception e) {
			Log.i("internet connecting fail", "connecting fail");

		}
		return info;
	}
}
