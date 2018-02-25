package me.xiangxik.config.server;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class EncryptApplication {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://localhost:8899/encrypt");

		HttpEntity entity = new StringEntity("test", Consts.UTF_8);
		post.setEntity(entity);

		HttpResponse response = client.execute(post);
		System.out.println(EntityUtils.toString(response.getEntity()));
	}
}
