/**
 * Copyright (c) 2005-2010 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: GMailEngine.java 1211 2010-09-10 16:20:45Z calvinxiu $
 */
package com.maiyajf.base.security;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageFilter;

import com.jhlabs.image.WaterFilter;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.BaffleTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;

/**
 * JCaptcha验证码图片生成引擎,仿照JCaptcha2.0编写类似GMail验证码的样式.
 * 
 * @author calvin
 */
public class GMailEngine extends ListImageCaptchaEngine {

	@Override
	protected void buildInitialFactories() {
		int minWordLength = 5;
		int maxWordLength = 6;
		int minFontSize = 22;
		int maxFontSize = 26;
		int fontSize = (minFontSize + maxFontSize) / 2;
		int imageWidth = 100;
		int imageHeight = 40;
		String acceptedChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

		//word generator
		WordGenerator wordgen = new RandomWordGenerator(acceptedChars);
				
		//word2image components
		RandomListColorGenerator colorGenerator = new RandomListColorGenerator(
			new Color[] { new Color(23, 170, 27), 
			new Color(220, 34, 11), 
			new Color(125, 193, 99), 
			new Color(225, 106, 38),
			new Color(23, 67, 172), 
			new Color(80, 188, 251) });
		BaffleTextDecorator baffleTextDecorator = new BaffleTextDecorator(2,Color.WHITE);//气泡干扰 
//		LineTextDecorator lineTextDecorator = new LineTextDecorator(1,Color.WHITE);//曲线干扰
		TextPaster randomPaster = new DecoratedRandomTextPaster(minWordLength, maxWordLength,
				colorGenerator, true, new TextDecorator[] {baffleTextDecorator});
		BackgroundGenerator background = new UniColorBackgroundGenerator(imageWidth, imageHeight, new Color(233, 233, 245));
//				new MyBackgroundGenerator(imageWidth, imageHeight, new Color(233, 233, 245));
		FontGenerator font = new RandomFontGenerator(minFontSize, maxFontSize, new Font[] {
				new Font("nyala", Font.BOLD, fontSize), new Font("Bell MT", Font.PLAIN, fontSize),
				new Font("Credit valley", Font.BOLD, fontSize) });

  
		//过滤器  
		WaterFilter water = new WaterFilter();  
		water.setAmplitude(2d);//振幅  
		water.setAntialias(true);//显示字会出现锯齿状,true就是平滑的  
		//water.setPhase(30d);//月亮的盈亏   
		water.setWavelength(30d);// 
		ImageDeformation postDef = new ImageDeformationByFilters(new ImageFilter[] {water});
		ImageDeformation backDef = new ImageDeformationByFilters(new ImageFilter[] {});
		ImageDeformation textDef = new ImageDeformationByFilters(new ImageFilter[] {});

		WordToImage word2image = new DeformedComposedWordToImage(font, background, randomPaster, backDef, textDef, postDef);
		addFactory(new GimpyCaptchaFactory(wordgen, word2image));
	}

}
