package com.ourchem.biz;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import com.ourchem.db.DBManager;

public class BizServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
          String oper = req.getParameter("oper");
          if("listdata".equals(oper)){
        	  listdata(req,resp);
          }else if("edit".equals(oper) || "add".equals(oper)){
        	  edit(req,resp);
          }else if("list".equals(oper)){
        	  list(req,resp);
          }else if("save".equals(oper)){
        	  save(req,resp);
          }else if("delete".equals(oper)){
        	  delete(req,resp);
          }
		 
	}
	
	private void list(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		 req.getRequestDispatcher("edit.jsp").forward(req, resp);
	}
	
	/**
	 * 读取列表数据
	 * @param req
	 * @param resp
	 */
	private void listdata(HttpServletRequest req, HttpServletResponse resp){
		 
		String sql = "select * from (select * from CMS_CONTENT order by id desc) where rownum<10";

		ResultSet result = DBManager.queryList(sql);

		try {
			List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();

			while (result.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", result.getString("TITLE"));
				map.put("dept", result.getString("CREATEBY_DEPTNA"));
				map.put("id", result.getString("ID"));
				list.add(map);
			}
			writeJsonToResponse(list,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param data
	 * @param response
	 * @throws Exception
	 */
	private void edit(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException{
		 req.getRequestDispatcher("edit.jsp").forward(req, resp);
	}

	
	private void save(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException{
		String title = req.getParameter("title");
		String dept = req.getParameter("dept");
		Integer id = req.getParameter("id") == null ? null : Integer.parseInt( req.getParameter("id"));
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("title", title);
		map.put("dept", dept);  
		DBManager.save("CMS_CONTENT", id, map);
		 req.getRequestDispatcher("success.jsp").forward(req, resp);
	}
	
	private void delete(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException{
		Integer id = req.getParameter("id") == null ? null : Integer.parseInt( req.getParameter("id"));
		DBManager.delete("CMS_CONTENT", id);
		 req.getRequestDispatcher("success.jsp").forward(req, resp);
	}
	 
	
	
	protected void writeJsonToResponse(Object data, HttpServletResponse response)
			throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonGenerator jsonGenerator = objectMapper.getJsonFactory()
				.createJsonGenerator(response.getOutputStream(),
						JsonEncoding.UTF8);
		jsonGenerator.writeObject(data);
	}

}
