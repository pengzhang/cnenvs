package tasks;

import play.inject.BeanSource;
import play.inject.Injector;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class DependencyInjectionJob extends Job implements BeanSource{
	
	public void doJob() {
		Injector.inject(this);
	}
	
	@Override
	public <T> T getBeanOfType(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}
