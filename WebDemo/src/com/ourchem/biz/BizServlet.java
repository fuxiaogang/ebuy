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

		String sql = "select * from CMS_CONTENT where rownum<5";

		ResultSet result = DBManager.queryList(sql);

		try {
			List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();

			while (result.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("title", result.getString("TITLE"));
				map.put("dept", result.getString("CREATEBY_DEPTNA"));
				list.add(map);
			}
			writeJsonToResponse(list,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
