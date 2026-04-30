package com.coder.config;



import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.coder.model.Appointment;
import com.coder.model.Appointmentservice;
import com.coder.model.AppointmentserviceId;
import com.coder.model.Doctor;
import com.coder.model.DoctorSchedule;
import com.coder.model.Doctorservice;
import com.coder.model.DoctorserviceId;
import com.coder.model.Drug;
import com.coder.model.DrugType;
import com.coder.model.Owner;
import com.coder.model.PaymentType;
import com.coder.model.Pet;
import com.coder.model.PetDrug;
import com.coder.model.PetDrugJoin;
import com.coder.model.PetDrugJoinId;
import com.coder.model.PetLogin;
import com.coder.model.Rank;
import com.coder.model.Scheduleservice;
import com.coder.model.ScheduleserviceId;
import com.coder.model.service;
import com.coder.model.serviceType;
import com.coder.model.Species;
import com.coder.model.Vocher;






@Configuration//spring.xml
@ComponentScan(basePackages={"com.coder"}) //<context:component-scan
@PropertySources({ //resources/database.properties
		@PropertySource("classpath:application.properties")
})
@EnableTransactionManagement
public class SpringDSXmlConfig {
	@Autowired
	private Environment environment;	
	
	@Autowired
	@Bean(name="dataSource")
	public DataSource getDataSource(){
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName(
		this.environment.getProperty("jdbc.driverClassName"));
		ds.setUrl(this.environment.getProperty("jdbc.url"));
		ds.setUsername(this.environment.getProperty("jdbc.username"));
		ds.setPassword(this.environment.getProperty("jdbc.password"));
		return ds;
	}
	
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sf){
		HibernateTransactionManager tm=new HibernateTransactionManager(sf);
		return tm;
	}
	
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(){
		LocalSessionFactoryBuilder b=new LocalSessionFactoryBuilder(getDataSource());
		
		b.addAnnotatedClass(Appointmentservice.class);
		b.addAnnotatedClass(AppointmentserviceId.class);
		b.addAnnotatedClass(Appointment.class);
		b.addAnnotatedClass(Doctor.class);
		b.addAnnotatedClass(DoctorSchedule.class);
		b.addAnnotatedClass(Doctorservice.class);
		b.addAnnotatedClass(DoctorserviceId.class);
		b.addAnnotatedClass(Drug.class);
		b.addAnnotatedClass(DrugType.class);
		b.addAnnotatedClass(Owner.class);
		b.addAnnotatedClass(PaymentType.class);
		b.addAnnotatedClass(Pet.class);
		b.addAnnotatedClass(PetDrug.class);
		b.addAnnotatedClass(PetDrugJoin.class);
		b.addAnnotatedClass(PetDrugJoinId.class);
		b.addAnnotatedClass(PetLogin.class);
		b.addAnnotatedClass(Rank.class);
		b.addAnnotatedClass(Scheduleservice.class);
		b.addAnnotatedClass(ScheduleserviceId.class);
		b.addAnnotatedClass(DoctorSchedule.class);
		b.addAnnotatedClass(service.class);
		b.addAnnotatedClass(serviceType.class);
		b.addAnnotatedClass(Species.class);
		b.addAnnotatedClass(Vocher.class);
	
		return b.buildSessionFactory();
	}
}




