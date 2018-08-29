package com.jd.maintain.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ValidateCodeServlet
 */
@WebServlet("/maintain/ValidateCodeServlet")
public class ValidateCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidateCodeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String createValidateCode() {
		String vcStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		String validateCode = "";
		for (int i = 0; i < 4; i++) {
			int randomNum = random.nextInt(vcStr.length());
			validateCode += vcStr.charAt(randomNum);
		}
		return validateCode;
	}

	public void drawValidateCode(BufferedImage image, String validateCode) {
		Graphics g = image.getGraphics();
		g.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		g.setColor(new Color(255, 255, 255));
		for (int i = 0; i < validateCode.length(); i++) {
			String vc = String.valueOf(validateCode.charAt(i));
			Random random = new Random();
			int randomNum = random.nextInt(5);
			g.drawString(vc, 12 * (i + 1) + randomNum, 25 + randomNum);
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 画出背景图
		response.setContentType("image/jpg");
		String path = request.getRealPath("/images/") + "vc.jpg";
		System.out.println("path=" + path);
		File vcFile = new File(path);
		System.out.println("文件大小：" + vcFile.length());
		BufferedImage image = ImageIO.read(vcFile);
		// 生成验证码
		String validateCode = createValidateCode();
		// 画出验证码
		drawValidateCode(image, validateCode);
		// 保存验证码到Session中
		HttpSession session = request.getSession();
		session.setAttribute("validateCodeInSession", validateCode);
		// 输出背景图和验证码
		OutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
