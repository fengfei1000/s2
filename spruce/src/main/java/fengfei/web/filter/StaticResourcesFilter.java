package fengfei.web.filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class StaticResourcesFilter implements Filter {
	final static String UploadRootDir = "uploadRootDir";
	private String uploadDir;

	@Override
	public void init(FilterConfig config) throws ServletException {
		uploadDir = config.getInitParameter(UploadRootDir);

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		File file = new File(uploadDir + ""+req.getRequestURI());
		FileInputStream in = new FileInputStream("");
		OutputStream out = response.getOutputStream();

	}

	@Override
	public void destroy() {

	}

}
