package com.maiyajf.base.security;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import com.octo.captcha.image.ImageCaptcha;

/**
 * @ClassName: GimpyImageCaptcha
 * @Description: 
 * @author: yunlei.hua
 * @date: 2015年12月17日 上午9:43:55
 */
@SuppressWarnings("serial")
public class GimpyImageCaptcha extends ImageCaptcha implements Serializable {

	private String response;
	 
	public GimpyImageCaptcha(String question, BufferedImage challenge, String response) {
        super(question, challenge);
        this.response = response;
    }
 
    /**
     * Validation routine from the CAPTCHA interface. this methods verify if the response is not null and a String and
     * then compares the given response to the internal string.
     *
     * @return true if the given response equals the internal response, false otherwise.
     */
    public final Boolean validateResponse(final Object response) {
        return (null != response && response instanceof String)
                ? validateResponse((String) response) : Boolean.FALSE;
    }
 
    /**
     * Very simple validation routine that compares the given response to the internal string.
     *
     * @return true if the given response equals the internal response, false otherwise.
     */
    private final Boolean validateResponse(final String response) {
        // 主要改的这里，不区分大小写
        return new Boolean(response.toLowerCase().equals(this.response.toLowerCase()));
    }
 
}

