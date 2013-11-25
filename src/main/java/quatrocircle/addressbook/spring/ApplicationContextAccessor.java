package quatrocircle.addressbook.spring;

import javax.inject.Singleton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Singleton
@Lazy(value = false)
public class ApplicationContextAccessor implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
}