package pe.cajapaita.microservices.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTimeElapsedFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(PreTimeElapsedFilter.class);
	
	/*
	 * Hacer validaciones, si retorna true, se ejecuta el metodo run
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/*
	 * Se resuelve la logica del filtro
	 */
	@Override
	public Object run() throws ZuulException {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString()));
		
		Long startTime  = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		
		
		return null;
	}

	/*
	 * Pre, Antes de que se resuelva la ruta
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
