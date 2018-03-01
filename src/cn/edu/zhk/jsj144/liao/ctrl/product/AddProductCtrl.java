package cn.edu.zhk.jsj144.liao.ctrl.product;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.edu.zhk.jsj141.yin.util.BeanMapUtil;
import cn.edu.zhk.jsj144.liao.entity.product.Product;
import cn.edu.zhk.jsj144.liao.service.product.ProductService;

@MultipartConfig
public class AddProductCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public AddProductCtrl() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		
		// 创建工具
		FileItemFactory factory = new DiskFileItemFactory();
		// 创建解析器对象
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(10* 80 * 1024);//设置单个上传的文件上限为800KB
		// 解析request得到List<FileItem>
		List<FileItem> fileItemList = null;
		try {
			fileItemList = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			// 如果出现这个异常，说明单个文件超出了800KB
			e.printStackTrace();
			error("上传的文件超出了800KB", request, response);
			return;
		}
		
		// 把List<FileItem>封装到product对象中
		Map<String,Object> map = new HashMap<String,Object>();
		for(FileItem fileItem : fileItemList) {
			if(fileItem.isFormField()) {//如果是普通表单字段
				if(fileItem.getFieldName().equals("price") || fileItem.getFieldName().equals("currPrice")) {
					map.put(fileItem.getFieldName(), Float.parseFloat(fileItem.getString("UTF-8")));
				} else if(fileItem.getFieldName().equals("productNum")) {
					map.put(fileItem.getFieldName(), Integer.parseInt(fileItem.getString("UTF-8")));
				} else {
					map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
				}
			}
		}
		Product product = new Product();
		BeanMapUtil.mapToBean(map, product);//把Map中数据封装到product对象中
		
  //  *** 保存上传的大图片

		// 获取文件名
		FileItem fileItem = fileItemList.get(1);//获取大图
		String filename = fileItem.getName();
		// 截取文件名，因为部分浏览器上传的绝对路径
		int index = filename.lastIndexOf("\\");
		if(index != -1) {
			filename = filename.substring(index + 1);
		}
		// 给文件名添加uuid前缀，避免文件同名现象
		filename = UUID.randomUUID().toString() + "_" + filename;
		// 校验文件名称的扩展名
		if(!filename.toLowerCase().endsWith(".jpg")) {
			error("上传的图片扩展名必须是JPG", request, response);
			return;
		}
		// 校验图片的尺寸
		// 保存上传的图片，把图片new成图片对象：Image、Icon、ImageIcon、BufferedImage、ImageIO
		
		//获取真实路径
		String savepath = this.getServletContext().getRealPath("/Back_Shop/ma_product/product_img");
		
		//创建目标文件
		File destFile = new File(savepath, filename);
		
		//保存文件
		try {
			fileItem.write(destFile);//它会把临时文件重定向到指定的路径，再删除临时文件
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 校验尺寸
		//   使用文件路径创建ImageIcon
		ImageIcon icon = new ImageIcon(destFile.getAbsolutePath());
		//   通过ImageIcon得到Image对象
		Image image = icon.getImage();
		//   获取宽高来进行校验
		if(image.getWidth(null) > 350 || image.getHeight(null) > 350) {
			error("您上传的图片尺寸超出了350*350！", request, response);
			destFile.delete();//删除图片
			return;
		}
		
		// 把图片的路径设置给product对象
		product.setImage_w("/Horizon/Back_Shop/ma_product/product_img/" + filename);

  //  *** 保存上传的小图片
		
		// 获取文件名
		fileItem = fileItemList.get(2);//获取小图
		filename = fileItem.getName();
		// 截取文件名，因为部分浏览器上传的绝对路径
		index = filename.lastIndexOf("\\");
		if(index != -1) {
			filename = filename.substring(index + 1);
		}
		// 给文件名添加uuid前缀，避免文件同名现象
		filename = UUID.randomUUID().toString() + "_" + filename;
		// 校验文件名称的扩展名
		if(!filename.toLowerCase().endsWith(".jpg")) {
			error("上传的图片扩展名必须是JPG", request, response);
			return;
		}
		// 校验图片的尺寸
		// 保存上传的图片，把图片new成图片对象：Image、Icon、ImageIcon、BufferedImage、ImageIO

		// 获取真实路径
//		savepath = this.getServletContext().getRealPath("/product_img");
		
		// 创建目标文件
		destFile = new File(savepath, filename);
		// 保存文件
		try {
			fileItem.write(destFile);//它会把临时文件重定向到指定的路径，再删除临时文件
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// 校验尺寸
		//  使用文件路径创建ImageIcon
		icon = new ImageIcon(destFile.getAbsolutePath());
		//  通过ImageIcon得到Image对象
		image = icon.getImage();
		//  获取宽高来进行校验
		if(image.getWidth(null) > 350 || image.getHeight(null) > 350) {
			error("您上传的图片尺寸超出了350*350！", request, response);
			destFile.delete();//删除图片
			return;
		}
		
		// 把图片的路径设置给product对象
		product.setImage_b("/Horizon/Back_Shop/ma_product/product_img/" + filename);

  // *** 调用service保存商品信息
		
		product.setProductid(UUID.randomUUID().toString());
		ProductService productService = new ProductService();
		try {
			productService.addProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			error("数据提交异常", request, response);
			e.printStackTrace();
			return;
		}
		
		// 保存成功信息并响应输出
		PrintWriter out = response.getWriter();
		out.println("添加商品成功！");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * 保存错误信息并响应输出
	 * @param msg
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void error(String msg, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("Error: " + msg);
		out.flush();
		out.close();
	}
}
