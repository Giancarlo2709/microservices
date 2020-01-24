package pe.cajapaita.microservices.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTimeElapsedFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(PostTimeElapsedFilter.class);
	
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
		log.info("Entrando a post filter");
		
		Long startTime  = (Long) request.getAttribute("startTime");
		
		Long endTime = System.currentTimeMillis();
		Long timeElapsed = endTime - startTime;
		
		log.info(String.format("Tiempo transcurrido en segundos %s seg.", timeElapsed.doubleValue()/ 1000.00));
		log.info(String.format("Tiempo transcurrido en milisegundos %s ms.", timeElapsed));
		return null;
	}

	/*
	 * Pre, Antes de que se resuelva la ruta
	 */
	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
