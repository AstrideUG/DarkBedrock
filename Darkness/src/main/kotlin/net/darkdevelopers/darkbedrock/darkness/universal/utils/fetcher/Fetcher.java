/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.universal.utils.fetcher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * Created by LartyHD on 13.02.2018  14:28.
 */
class Fetcher
{
	static String callURL(String URL)
	{
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn;
		InputStreamReader in = null;
		try
		{
			java.net.URL url = new URL(URL);
			urlConn = url.openConnection();
			if (urlConn != null)
			{
				urlConn.setReadTimeout(60 * 1000);
			}
			if (urlConn != null && urlConn.getInputStream() != null)
			{
				in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				int cp;
				while ((cp = bufferedReader.read()) != -1)
				{
					sb.append((char) cp);
				}
				bufferedReader.close();
			}
			Objects.requireNonNull(in).close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return sb.toString();
	}
}
