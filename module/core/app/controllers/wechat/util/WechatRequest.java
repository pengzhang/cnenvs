package controllers.wechat.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

public interface WechatRequest {
	public String sendPost(String api_url,Object xmlObj) throws UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException;
}
